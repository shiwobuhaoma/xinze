package com.xinze.xinze.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.login.LoginActivity;
import com.xinze.xinze.main.adapter.MyRecycleViewAdapter;
import com.xinze.xinze.main.bean.MyRecycleViewItem;
import com.xinze.xinze.register.RegisterActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author lxf
 * Created by lxf on 2016/5/15.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{


    @BindView(R.id.my_register)
    Button myRegister;
    @BindView(R.id.my_unLogin)
    TextView myUnLogin;
    @BindView(R.id.my_login)
    Button myLogin;
    @BindView(R.id.my_rv)
    RecyclerView myRv;

    private ArrayList<MyRecycleViewItem>  myRecycleViewItems = new ArrayList<>();
    @Override
    protected int initLayout() {
        return R.layout.main_fragment_my;
    }

    @Override
    protected void initView() {
        myRegister.setOnClickListener(this);
        myLogin.setOnClickListener(this);
        myRecycleViewItems.add(new MyRecycleViewItem(R.string.driver_certification,0,true,true,true,true,"未认证"));
        myRecycleViewItems.add(new MyRecycleViewItem(R.string.my_cars,R.mipmap.my_ic_cars,true,false,true,false,"0辆"));
        myRecycleViewItems.add(new MyRecycleViewItem(R.string.my_routes,R.mipmap.my_ic_routes,true,true,false,true,""));
        myRecycleViewItems.add(new MyRecycleViewItem(R.string.my_system_message,R.mipmap.my_ic_sysmsg,true,false,true,false,"0条"));
        myRecycleViewItems.add(new MyRecycleViewItem(R.string.my_about_us,R.mipmap.my_ic_about_us,true,true,false,true,""));
        myRecycleViewItems.add(new MyRecycleViewItem(R.string.my_help,R.mipmap.my_ic_help,true,false,false,true,""));

        MyRecycleViewAdapter myva = new MyRecycleViewAdapter(mActivity,myRecycleViewItems);
        myRv.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        myRv.setAdapter(myva);

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
}
