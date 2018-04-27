package com.xinze.xinze.module.main.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.about.AboutUsActivity;
import com.xinze.xinze.module.certification.CertificationActivity;
import com.xinze.xinze.module.login.LoginActivity;
import com.xinze.xinze.module.main.adapter.MyRecycleViewAdapter;
import com.xinze.xinze.module.main.bean.MyRecycleViewItem;
import com.xinze.xinze.module.main.constant.MyItemSelected;
import com.xinze.xinze.module.main.presenter.MyPresenterImp;
import com.xinze.xinze.module.main.view.IMyView;
import com.xinze.xinze.module.register.RegisterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author lxf
 *         Created by lxf on 2016/5/15.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener, IMyView {


    @BindView(R.id.my_register)
    Button myRegister;
    @BindView(R.id.my_unLogin)
    TextView myUnLogin;
    @BindView(R.id.my_login)
    Button myLogin;
    @BindView(R.id.my_rv)
    RecyclerView myRv;

    private ArrayList<MyRecycleViewItem> myRecycleViewItems = new ArrayList<>();
    private MyRecycleViewAdapter myva;
    private MyRecycleViewItem myDrivers;
    private MyRecycleViewItem myInvitation;
    private MyRecycleViewItem myChangePwd;
    private MyRecycleViewItem myLoginOut;
    private MyRecycleViewItem driverCer;
    private MyRecycleViewItem myCars;
    private MyRecycleViewItem myRoutes;
    private MyRecycleViewItem mySystemMsg;
    private MyRecycleViewItem myAboutUs;
    private MyRecycleViewItem myHelp;

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_my;
    }

    @Override
    protected void initView() {
        myRegister.setOnClickListener(this);
        myLogin.setOnClickListener(this);

        myDrivers = new MyRecycleViewItem(R.string.my_drivers, R.mipmap.my_ic_driver, true, false, true, false, "0人");
        myInvitation = new MyRecycleViewItem(R.string.my_invitation, R.mipmap.my_ic_invitation, true, true, false, true, "");
        myChangePwd = new MyRecycleViewItem(R.string.my_change_pwd, R.mipmap.my_ic_change_pwd, false, false, false, false, "");
        myLoginOut = new MyRecycleViewItem(R.string.my_login_out, 0, false, true, false, true, "");

        driverCer = new MyRecycleViewItem(R.string.driver_certification, 0, true, true, true, true, "未认证");

        myCars = new MyRecycleViewItem(R.string.my_cars, R.mipmap.my_ic_cars, true, false, true, false, "0辆");

        myRoutes = new MyRecycleViewItem(R.string.my_routes, R.mipmap.my_ic_routes, true, true, false, true, "");
        mySystemMsg = new MyRecycleViewItem(R.string.my_system_message, R.mipmap.my_ic_sysmsg, true, false, true, false, "0条");
        myAboutUs = new MyRecycleViewItem(R.string.my_about_us, R.mipmap.my_ic_about_us, true, true, false, true, "");
        myHelp = new MyRecycleViewItem(R.string.my_help, R.mipmap.my_ic_help, true, false, false, true, "");

        myRecycleViewItems.add(driverCer);
        myRecycleViewItems.add(myCars);
        myRecycleViewItems.add(myRoutes);
        myRecycleViewItems.add(mySystemMsg);
        myRecycleViewItems.add(myAboutUs);
        myRecycleViewItems.add(myHelp);
        myva = new MyRecycleViewAdapter(mActivity, myRecycleViewItems);
        myRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        myRv.setAdapter(myva);
        myva.setOnItemClickListener(new MyRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int titleRes = myva.getTitleRes(position);
                switch (titleRes) {
                    case R.string.driver_certification:
                        doType(MyItemSelected.DRIVER_CERTIFICATION);
                        break;
                    case R.string.my_cars:
                        doType(MyItemSelected.MY_CARS);
                        break;
                    case R.string.my_routes:
                        doType(MyItemSelected.MY_ROUTES);
                        break;
                    case R.string.my_system_message:
                        doType(MyItemSelected.MY_SYSTEM_MESSAGE);
                        break;
                    case R.string.my_about_us:
                        mActivity.startActivity(new Intent(mActivity, AboutUsActivity.class));
                        break;
                    case R.string.my_login_out:
                        MyPresenterImp mpi = new MyPresenterImp(MyFragment.this,mActivity);
                        mpi.loginOut();
                        break;
                    case R.string.my_change_pwd:
                        doType(MyItemSelected.MY_CHANGE_PWD);
                        break;
                    case R.string.my_invitation:
                        doType(MyItemSelected.MY_INVITATION);
                        break;
                    case R.string.my_drivers:
                        doType(MyItemSelected.MY_DRIVERS);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    /**
     * 判断有没有登录，没有登录显示未登录对话框，登录了跳转到不同界面
     * @param type 不同界面的标识
     */
    private void doType(String type) {
        if (isLogin()) {
            doSomething(type);
        } else {
            showUnloginDialog();
        }
    }

    /**
     * 根据type跳转到不同界面
     * @param type 不同界面的标识
     */
    private void doSomething(String type) {
        switch (type) {
            case MyItemSelected.DRIVER_CERTIFICATION:
                    if ("1".equals(App.mUser.getVertifyFlag()) || "2".equals(App.mUser.getVertifyFlag())){
                        startActivity(new Intent(mActivity,CertificationActivity.class));
                    }else{
                        showUnIdentificationDialog();
                    }
                break;
            case MyItemSelected.MY_CARS:
                break;
            case MyItemSelected.MY_ROUTES:
                break;
            case MyItemSelected.MY_SYSTEM_MESSAGE:
                break;
            case MyItemSelected.MY_CHANGE_PWD:
                break;
            case MyItemSelected.MY_INVITATION:
                break;
            case MyItemSelected.MY_DRIVERS:
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        myRv.setAdapter(myva);
        if (isLogin()) {
            if (!myRecycleViewItems.contains(myDrivers) && !myRecycleViewItems.contains(myInvitation) && !myRecycleViewItems.contains(myChangePwd)) {
                myRecycleViewItems.add(1, myDrivers);
                myRecycleViewItems.add(4, myInvitation);
                myRecycleViewItems.add(5, myChangePwd);
                myRecycleViewItems.add(myLoginOut);
            }
            myRegister.setVisibility(View.GONE);
            myLogin.setVisibility(View.GONE);
            myUnLogin.setText(App.mUser.getLogin_name());
            driverCer.setRightText(App.mUser.getVertifyDescription());
        } else {
            if (myRecycleViewItems.contains(myDrivers) && myRecycleViewItems.contains(myInvitation) && myRecycleViewItems.contains(myChangePwd) && myRecycleViewItems.contains(myLoginOut)) {
                myRecycleViewItems.remove(myDrivers);
                myRecycleViewItems.remove(myInvitation);
                myRecycleViewItems.remove(myChangePwd);
                myRecycleViewItems.remove(myLoginOut);
            }
            driverCer.setRightText("未认证");
            myRegister.setVisibility(View.VISIBLE);
            myLogin.setVisibility(View.VISIBLE);
            myUnLogin.setText(R.string.unLogin);
        }
        myva.notifyDataSetChanged();
    }

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    @OnClick({R.id.my_register, R.id.my_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_register:
                mActivity.startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.my_login:
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loginOutSuccess() {
        shotToast("注销成功");
    }

    @Override
    public void loginOutFailed() {
        shotToast("注销失败");
    }

    @Override
    public void shotToast(String msg) {
        RxToast.showToast(msg);
    }

    /**
     * 提示未登录对话框
     */
    public void showUnloginDialog() {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setText(R.string.unLogin);
        rxDialogSureCancel.getContentView().setText(R.string.isGoLogin);
        rxDialogSureCancel.getSureView().setText(R.string.goLogin);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }

    /**
     * 判断当前用户是否登录
     *
     * @return true为登录状态  false为注销状态
     */
    public boolean isLogin() {
        return App.mUser.isLogin();
    }

    /**
     * 提示没有认证对话框
     */
    public void showUnIdentificationDialog() {
        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mActivity);
        rxDialogSureCancel.getTitleView().setText(R.string.unIdentification);
        rxDialogSureCancel.getContentView().setText(R.string.isGoIdentification);
        rxDialogSureCancel.getSureView().setText(R.string.goIdentification);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();

    }
}
