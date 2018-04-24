package com.xinze.xinze.module.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.about.AboutUsActivity;
import com.xinze.xinze.module.login.LoginActivity;
import com.xinze.xinze.module.main.adapter.MyRecycleViewAdapter;
import com.xinze.xinze.module.main.bean.MyRecycleViewItem;
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
 * Created by lxf on 2016/5/15.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener ,IMyView {


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
                        break;
                    case R.string.my_cars:
                        break;
                    case R.string.my_routes:
                        break;
                    case R.string.my_system_message:
                        break;
                    case R.string.my_about_us:
                        mActivity.startActivity(new Intent(mActivity, AboutUsActivity.class));
                        break;
                    case R.string.exit:
                        MyPresenterImp mpi = new MyPresenterImp(MyFragment.this);
                        mpi.loginOut();
                        break;
                    case R.string.my_change_pwd:
                        break;
                    case R.string.my_invitation:
                        break;
                    case R.string.my_drivers:
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.mUser.isLogin()) {
            if (!myRecycleViewItems.contains(myDrivers) && !myRecycleViewItems.contains(myInvitation) && !myRecycleViewItems.contains(myChangePwd)) {
                myRecycleViewItems.add(1, myDrivers);
                myRecycleViewItems.add(4, myInvitation);
                myRecycleViewItems.add(5, myChangePwd);
                myRecycleViewItems.add(myLoginOut);
            }
            myRegister.setVisibility(View.GONE);
            myLogin.setVisibility(View.GONE);
            myUnLogin.setText(App.mUser.getLogin_name());

        } else {
            if (myRecycleViewItems.contains(myDrivers) && myRecycleViewItems.contains(myInvitation) && myRecycleViewItems.contains(myChangePwd) && myRecycleViewItems.contains(myLoginOut)) {
                myRecycleViewItems.remove(myDrivers);
                myRecycleViewItems.remove(myInvitation);
                myRecycleViewItems.remove(myChangePwd);
                myRecycleViewItems.remove(myLoginOut);
            }
            myRegister.setVisibility(View.VISIBLE);
            myLogin.setVisibility(View.VISIBLE);
            myUnLogin.setText(R.string.unLogin);
        }
        myva.notifyDataSetChanged();
    }

    public static MyFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
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
}
