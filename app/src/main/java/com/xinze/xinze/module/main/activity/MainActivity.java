package com.xinze.xinze.module.main.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.MainConfig;
import com.xinze.xinze.http.listener.DownloadListener;
import com.xinze.xinze.module.main.adapter.SelectPageAdapter;
import com.xinze.xinze.module.main.fragment.HomeFragment;
import com.xinze.xinze.module.main.fragment.MyFragment;
import com.xinze.xinze.module.main.fragment.OrderFragment;
import com.xinze.xinze.module.main.presenter.MainPresenterImp;
import com.xinze.xinze.module.main.view.IMainView;
import com.xinze.xinze.utils.DialogUtil;
import com.xinze.xinze.utils.UIUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author lxf
 * 主界面
 */
public class MainActivity extends BaseActivity implements IMainView, DownloadListener, EasyPermissions.PermissionCallbacks {


    private static final int READ_AND_WRITE = 1;
    private static final int INSTALL_PACKAGES_REQUESTCODE = 2;
    private static final int GET_UNKNOWN_APP_SOURCES = 3;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.vp_main)
    ViewPager mVp;
    private HomeFragment home;
    private ArrayList<Fragment> fragments;
    private MyFragment my;
    private OrderFragment order;
    private SelectPageAdapter spa;
    public static Integer currentFragment = MainConfig.HOME_FRAGMENT;

    private MainPresenterImp mPresenter;
    private ProgressDialog mProgressDialog;

    /**
     * 所要申请的权限读和写
     */
    String[] mRequestPermissionList = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};


    /**
     * 所要申请的权限读和写
     */
    String[] mRequestInstallPermissionList = {Manifest.permission.REQUEST_INSTALL_PACKAGES};


    private List<String> mDeniedPermissionList = new ArrayList<>();


    @Override
    protected int initLayout() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView() {
        /**
         * MODE_DEFAULT
         *   如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         * MODE_FIXED
         *   填充模式，未选中的Item会显示文字，没有换挡动画。
         * MODE_SHIFTING
         *   换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        /**
         *    BACKGROUND_STYLE_DEFAULT
         *       如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
         *    BACKGROUND_STYLE_STATIC
         *        点击的时候没有水波纹效果
         *   BACKGROUND_STYLE_RIPPLE
         *       点击的时候有水波纹效果
         */
        mBottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        //值得一提，模式跟背景的设置都要在添加tab前面，不然不会有效果。
        mBottomNavigationBar
                .setActiveColor(R.color.main_ic_orange);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.main_ic_home_select, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.main_ic_order, "订单"))
                .addItem(new BottomNavigationItem(R.mipmap.main_ic_my, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        setBottomNavigationItem(mBottomNavigationBar, 10, 26, 10);
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mVp.setCurrentItem(position);
            }
        });

    }

    private void requestPermissions() {
        //第二个参数是被拒绝后再次申请该权限的解释
        //第三个参数是请求码
        //第四个参数是要申请的权限
        EasyPermissions.requestPermissions(this, "需要写入SD卡权限", READ_AND_WRITE, mRequestPermissionList);

    }



    private void initViewPager() {

        spa = new SelectPageAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(spa);
        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentFragment = position;
                mBottomNavigationBar.selectTab(position);
                if (fragments.get(position) == order) {
                    if (App.mUser != null && App.mUser.isLogin()) {
                        order.refresh();
                    } else {
                        order.clearData();
                        DialogUtil.showUnloginDialog(MainActivity.this);
                    }

                }
            }
        });
        mVp.setOffscreenPageLimit(3);
        mVp.setCurrentItem(0);
    }

    @Override
    protected void initData() {
        super.initData();
        fragments = getFragments();
        initViewPager();
        setDefaultFragment();
    }


    private void setDefaultFragment() {
        mVp.setCurrentItem(0);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        home = HomeFragment.newInstance();
        order = OrderFragment.newInstance();
        my = MyFragment.newInstance();
        fragments.add(home);
        fragments.add(order);
        fragments.add(my);
        return fragments;
    }

    /**
     * @param bottomNavigationBar，需要修改的 BottomNavigationBar
     * @param space                     图片与文字之间的间距
     * @param imgLen                    单位：dp，图片大小，应 <= 36dp
     * @param textSize                  单位：dp，文字大小，应 <= 20dp
     *                                  <p>
     *                                  使用方法：直接调用setBottomNavigationItem(bottomNavigationBar, 6, 26, 10);
     *                                  代表将bottomNavigationBar的文字大小设置为10dp，图片大小为26dp，二者间间距为6dp
     **/

    private void setBottomNavigationItem(BottomNavigationBar bottomNavigationBar, int space, int imgLen, int textSize) {
        Class barClass = bottomNavigationBar.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.getName().equals("mTabContainer")) {
                try {
                    //反射得到 mTabContainer
                    LinearLayout mTabContainer = (LinearLayout) field.get(bottomNavigationBar);
                    for (int j = 0; j < mTabContainer.getChildCount(); j++) {
                        //获取到容器内的各个Tab
                        View view = mTabContainer.getChildAt(j);
                        //获取到Tab内的各个显示控件
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(56));
                        FrameLayout container = (FrameLayout) view.findViewById(R.id.fixed_bottom_navigation_container);
                        container.setLayoutParams(params);
                        container.setPadding(UIUtils.dip2px(12), UIUtils.dip2px(0), UIUtils.dip2px(12), UIUtils.dip2px(0));

                        //获取到Tab内的文字控件
                        TextView labelView = (TextView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                        labelView.setIncludeFontPadding(false);
                        labelView.setPadding(0, 0, 0, UIUtils.dip2px(20 - textSize - space / 2));

                        //获取到Tab内的图像控件
                        ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = new FrameLayout.LayoutParams(UIUtils.dip2px(imgLen), UIUtils.dip2px(imgLen));
                        params.setMargins(0, 0, 0, space / 2);
                        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
                        iconView.setLayoutParams(params);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mVp.setCurrentItem(currentFragment);
        if (EasyPermissions.hasPermissions(this, mRequestPermissionList)) {
            checkUpdate();
        } else {
            requestPermissions();
        }

    }


    /**
     * 检查是否有新版本，如果有就升级
     */
    private void checkUpdate() {
        if (mPresenter == null) {
            mPresenter = new MainPresenterImp(this, this);
        }
        mPresenter.checkUpdate("1", "0");
    }

    @Override
    public void checkUpdateSuccess() {

    }

    @Override
    public void checkUpdateFailed() {

    }


    /**
     * 下载apk
     *
     * @param downloadUrl 下载apk地址
     */
    private void downloadApk(String downloadUrl) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("正在下载中。。。");
        mProgressDialog.setIcon(R.mipmap.ic_launcher);
        mProgressDialog.setMax(100);
        mProgressDialog.setTitle("提示");
        mPresenter.downloadApk(downloadUrl, Environment.getExternalStorageDirectory() + File.separator + "/apk", "xinZe.apk", this);
    }

    /**
     * 显示强制升级对话框
     *
     * @param des 版本升级描述
     */
    @Override
    public void showForceDialogUpdate(String des, String downloadUrl) {
        DialogUtil.showCommonDialog(this, des, "确认", new DialogUtil.ChoiceClickListener() {
            @Override
            public void onClickSureView(Object data) {
                downloadApk(downloadUrl);
            }

            @Override
            public void onClickCancelView(Object data) {

            }
        });
    }

    /**
     * 显示普通升级对话框
     *
     * @param des 版本升级描述
     */
    @Override
    public void showCommonDialogUpdate(String des, String downloadUrl) {
        DialogUtil.showCommonDialog(this, des, "确认", "取消", new DialogUtil.ChoiceClickListener() {
            @Override
            public void onClickSureView(Object data) {
                downloadApk(downloadUrl);
            }

            @Override
            public void onClickCancelView(Object data) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.dispose();
            mPresenter.onDestroy();
        }
    }


    @Override
    public void onStartDownload() {
        mProgressDialog.show();
    }

    @Override
    public void onProgress(int progress) {
        mProgressDialog.setProgress(progress);
    }

    @Override
    public void onFinishDownload(File file) {
        mProgressDialog.dismiss();

    }

    @Override
    public void onFail(Throwable ex) {
        mProgressDialog.dismiss();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (requestCode == INSTALL_PACKAGES_REQUESTCODE) {
                if (perms.size() > 0) {
                    mPresenter.installApk();
                } else {
                    startInstallPermissionSettingActivity();
                }
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        for (String s : mRequestPermissionList) {
            if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                mDeniedPermissionList.add(s);
            } else {
                mDeniedPermissionList.clear();
            }
        }


        //未授予的权限为空，表示都授予了
        if (!mDeniedPermissionList.isEmpty()) {
            //将List转为数组
            String[] permissions = mDeniedPermissionList.toArray(new String[mDeniedPermissionList.size()]);
            if (requestCode == READ_AND_WRITE) {

                //再次请求权限
                EasyPermissions.requestPermissions(this, "需要写入SD卡权限", READ_AND_WRITE, permissions);
            } else {
                //再次请求权限
                EasyPermissions.requestPermissions(this, "需要安装应用的权限", INSTALL_PACKAGES_REQUESTCODE, permissions);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GET_UNKNOWN_APP_SOURCES:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                    if (hasInstallPermission) {
                        mPresenter.installApk();
                    } else {
                        startInstallPermissionSettingActivity();
                    }
                }
                break;

            default:
                break;
        }
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, GET_UNKNOWN_APP_SOURCES);
    }


}


