package com.xinze.xinze.module.main.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.listener.DownloadListener;
import com.xinze.xinze.http.observer.BaseDownloadObserver;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.modle.AppUpdate;
import com.xinze.xinze.module.main.view.IMainView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainPresenterImp extends BasePresenterImpl<IMainView> implements IMainPresenter {


    /**
     * 清除线程需要用到的
     */
    private Disposable disposable;
    private File mFile;
    public MainPresenterImp(IMainView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
    }

    @Override
    public void checkUpdate(String appType, String fileType) {
        RetrofitFactory.getInstence().Api().checkUpdate(appType, fileType).compose(this.<BaseEntity<AppUpdate>>setThread()).subscribe(new BaseObserver<AppUpdate>() {
            @Override
            protected void onSuccees(BaseEntity<AppUpdate> t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        setData(t);
                        mPresenterView.checkUpdateSuccess();
                    } else {
                        mPresenterView.checkUpdateFailed();
                    }
                }


            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mPresenterView.checkUpdateFailed();
            }
        });
    }

    private void setData(BaseEntity<AppUpdate> t) {
        if (t != null) {
            AppUpdate data = t.getData();
            String downloadUrl = data.getDownloadurl();
            int isForce = data.getIsornojr();
            int versionNumber = data.getVersionnumber();
            String des = data.getUpgradedes();
            //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新
            if (getVersionCode() < versionNumber) {
                //弹出提示版本更新的对话框
                if (1 == isForce) {
                    mPresenterView.showForceDialogUpdate(des, downloadUrl);
                } else {
                    mPresenterView.showCommonDialogUpdate(des, downloadUrl);

                }

            }

        }
    }


    @Override
    public void downloadApk(String downloadUrl, final String destDir, final String fileName, DownloadListener mDownloadListener) {
        mPresenterView.onStartDownload();
        RetrofitFactory.getInstence(mDownloadListener).downService().downloadApk(downloadUrl)
                //请求网络 在调度者的io线程
                .subscribeOn(Schedulers.io())
                //指定线程保存文件
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Exception {
                        return saveFile(responseBody.byteStream(), destDir, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDownloadObserver<File>() {


                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    protected void onDownloadSuccess(File file) {
                        mDownloadListener.onFinishDownload(file);
                        mFile = file;
                        installApk(file);
                    }

                    @Override
                    protected void onDownloadError(Throwable e) {
                        mDownloadListener.onFail(e);
                    }
                });


    }

    /**
     * 将文件写入本地
     *
     * @param destFileDir  目标文件夹
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    private File saveFile(InputStream is, String destFileDir, String destFileName) throws IOException {
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            return file;

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 销毁
     */
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void installApk() {
        installApk(mFile);
    }

    /**
     * 安装apk
     */
    private void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        Uri data;

        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(mContext, "com.xinze.xinze.fileprovider", file);
            //兼容8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = mContext.getPackageManager().canRequestPackageInstalls();
                if (!hasInstallPermission) {
                    //请求安装未知应用来源的权限
                    mPresenterView.shotToast("需要打开安装未知来源应用权限");
                    mPresenterView.startInstallPermissionSettingActivity();
                    return;
                }
            }
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            data = Uri.fromFile(file);
        }

        // 广播里面操作需要加上这句，存在于一个独立的栈里
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    /**
     * 获取当前程序的版本号
     */
    private int getVersionCode() {
        try {
            //获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);

            return packInfo.versionCode;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return 1;
    }


}
