package com.xinze.xinze.module.main.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.about.AboutUsActivity;
import com.xinze.xinze.module.certification.CertificationActivity;
import com.xinze.xinze.module.drivers.view.MyDriverActivity;
import com.xinze.xinze.module.invite.view.InviteActivity;
import com.xinze.xinze.module.login.LoginActivity;
import com.xinze.xinze.module.main.adapter.MyRecycleViewAdapter;
import com.xinze.xinze.module.main.bean.MyRecycleViewItem;
import com.xinze.xinze.module.main.constant.MyItemSelected;
import com.xinze.xinze.module.main.presenter.MyPresenterImp;
import com.xinze.xinze.module.main.view.IMyView;
import com.xinze.xinze.module.register.RegisterActivity;
import com.xinze.xinze.module.sysmsg.SystemMsgActivity;
import com.xinze.xinze.utils.DialogUtil;

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

    /**
     *布局类型
     */
    private static final int ITEM_TYPE_ONE = 1;
    private static final int ITEM_TYPE_ZERO = 2;
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

        myDrivers = new MyRecycleViewItem("我的司机", R.mipmap.my_ic_driver, true, false, true, false, "0人",false,ITEM_TYPE_ZERO);
        myInvitation = new MyRecycleViewItem("我的邀请", R.mipmap.my_ic_invitation, true, true, false, true, "",false,ITEM_TYPE_ZERO);
        myChangePwd = new MyRecycleViewItem("修改密码", R.mipmap.my_ic_change_pwd, false, false, false, false, "",false,ITEM_TYPE_ZERO);
        myLoginOut = new MyRecycleViewItem("退出登录", 0, false, true, false, true, "",true,ITEM_TYPE_ONE);

        driverCer = new MyRecycleViewItem("司机认证", 0, true, true, true, true, "未认证",true,ITEM_TYPE_ZERO);

        myCars = new MyRecycleViewItem("我的车辆", R.mipmap.my_ic_cars, true, false, true, false, "0辆",true,ITEM_TYPE_ZERO);

        myRoutes = new MyRecycleViewItem("长跑路线", R.mipmap.my_ic_routes, true, false, false, true, "",true,ITEM_TYPE_ZERO);
        mySystemMsg = new MyRecycleViewItem("系统消息", R.mipmap.my_ic_sysmsg, true, true, true, true, "0条",true,ITEM_TYPE_ZERO);
        myAboutUs = new MyRecycleViewItem("关于我们", R.mipmap.my_ic_about_us, true, false, false, true, "",true,ITEM_TYPE_ZERO);
        myHelp = new MyRecycleViewItem("帮助", R.mipmap.my_ic_help, true, true, false, true, "",false,ITEM_TYPE_ZERO);

        myRecycleViewItems.add(driverCer);
        myRecycleViewItems.add(myCars);
        myRecycleViewItems.add(myRoutes);
        myRecycleViewItems.add(mySystemMsg);
        myRecycleViewItems.add(myAboutUs);
        myRecycleViewItems.add(myHelp);
        myRecycleViewItems.add(myLoginOut);
        myva = new MyRecycleViewAdapter(mActivity, myRecycleViewItems);
        myRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        myRv.setAdapter(myva);
        myRv.setNestedScrollingEnabled(false);
        myva.setOnItemClickListener(new MyRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String title = myva.getTitle(position);
                switch (title) {
                    case "司机认证":
                        doType(MyItemSelected.DRIVER_CERTIFICATION);
                        break;
                    case "我的车辆":
                        doType(MyItemSelected.MY_CARS);
                        break;
                    case "长跑路线":
                        doType(MyItemSelected.MY_ROUTES);
                        break;
                    case "系统消息":
                        doType(MyItemSelected.MY_SYSTEM_MESSAGE);
                        break;
                    case "关于我们":
                        mActivity.startActivity(new Intent(mActivity, AboutUsActivity.class));
                        break;
                    case "退出登录":
                        MyPresenterImp mpi = new MyPresenterImp(MyFragment.this,mActivity);
                        mpi.loginOut();
                        break;
                    case "修改密码":
                        doType(MyItemSelected.MY_CHANGE_PWD);
                        break;
                    case "我的邀请":
                        doType(MyItemSelected.MY_INVITATION);
                        break;
                    case "我的司机":
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
            DialogUtil.showUnloginDialog(mActivity);
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
                        DialogUtil.showUnIdentificationDialog(mActivity);
                    }
                break;
            case MyItemSelected.MY_CARS:
                break;
            case MyItemSelected.MY_ROUTES:
                break;
            case MyItemSelected.MY_SYSTEM_MESSAGE:
                startActivity(new Intent(mActivity,SystemMsgActivity.class));
                break;
            case MyItemSelected.MY_CHANGE_PWD:
                break;
            case MyItemSelected.MY_INVITATION:
                startActivity(new Intent(mActivity,InviteActivity.class));
                break;
            case MyItemSelected.MY_DRIVERS:
                startActivity(new Intent(mActivity,MyDriverActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        myRv.setAdapter(myva);
        refreshPage();
    }

    private void refreshPage() {
        if (isLogin()) {
            if (!myRecycleViewItems.contains(myDrivers) && !myRecycleViewItems.contains(myInvitation) && !myRecycleViewItems.contains(myChangePwd)) {
                myDrivers.setShowTopLine(true);
                myRecycleViewItems.add(1, myDrivers);
                myRecycleViewItems.add(4, myInvitation);
                myRecycleViewItems.add(5, myChangePwd);
            }
            myChangePwd.setShowTopLine(true);
            myRoutes.setShowSpace(false);
            myRegister.setVisibility(View.GONE);
            myLogin.setVisibility(View.GONE);
            myUnLogin.setText(App.mUser.getLogin_name());
            driverCer.setRightText(App.mUser.getVertifyDescription());
        } else {
            if (myRecycleViewItems.contains(myDrivers) && myRecycleViewItems.contains(myInvitation) && myRecycleViewItems.contains(myChangePwd) && myRecycleViewItems.contains(myLoginOut)) {
                myRecycleViewItems.remove(myDrivers);
                myRecycleViewItems.remove(myInvitation);
                myRecycleViewItems.remove(myChangePwd);
            }

            myRoutes.setShowSpace(true);
            mySystemMsg.setShowBottomLine(true);
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
        refreshPage();
        shotToast("注销成功");
    }

    @Override
    public void loginOutFailed() {
        shotToast("注销失败");
    }

    /**
     * 判断当前用户是否登录
     *
     * @return true为登录状态  false为注销状态
     */
    public boolean isLogin() {
        return App.mUser.isLogin();
    }


}
