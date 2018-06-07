package com.xinze.xinze.module.main.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.listener.DownloadListener;
import com.xinze.xinze.http.observer.BaseDownloadObserver;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.activity.MainActivity;
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
    private MainActivity mActivity;


    /**
     * 清除线程需要用到的
     */
    private Disposable disposable;

    public MainPresenterImp(IMainView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mActivity = (MainActivity) mPresenterView;
    }

    @Override
    public void checkUpdate(String appType, String fileType) {
        RetrofitFactory.getInstence().Api().checkUpdate(appType, fileType).compose(this.<BaseEntity<AppUpdate>>setThread()).subscribe(new BaseObserver<AppUpdate>() {
            @Override
            protected void onSuccees(BaseEntity<AppUpdate> t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        mActivity.setData(t);
                        mActivity.checkUpdateSuccess();
                    } else {
                        mActivity.checkUpdateFailed();
                    }
                }


            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mActivity.checkUpdateFailed();
            }
        });
    }


    @Override
    public void downloadApk(String downloadUrl, final String destDir, final String fileName, DownloadListener mDownloadListener) {
        dispose();
         RetrofitFactory.getInstence(mDownloadListener).Api().downloadApk(downloadUrl)
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
}
