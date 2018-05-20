package com.xinze.xinze.module.drivers.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.drivers.presenter.IMyDriverPresenter;
import com.xinze.xinze.module.drivers.presenter.MyDriverPresenterImp;
import com.xinze.xinze.utils.ValidatorUtil;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/19.
 * desc: DriverAddActivity
 */
public class DriverAddActivity extends BaseActivity {
    @BindView(R.id.my_driver_add_toolbar)
    SimpleToolbar mToolbar;
    @BindView(R.id.my_driver_add_bt)
    Button myDriverAddButton;
    @BindView(R.id.my_driver_add_phone_et)
    EditText phoneEditText;



    protected IMyDriverPresenter mPresenter;


    @Override
    protected int initLayout() {
        return R.layout.my_driver_add_activity;
    }

    @Override
    protected void initView() {
        // 初始化标题栏
        initToolbar();
        // 添加司机btn点击事件
        myDriverAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = phoneEditText.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    shotToast("手机号码不能为空");
                    return;
                }

                if (!ValidatorUtil.isMobile(phoneNum)) {
                    shotToast("手机号码格式不正确");
                    return;
                }
                mPresenter.inviteDriver(phoneNum);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MyDriverPresenterImp(this);
    }


    /**
     * 初始化标题栏
     *
     * @author feibai
     * @time 2018/5/17  21:48
     * @desc
     */
    private void initToolbar() {
        mToolbar.setTitleMarginTop();
        mToolbar.setMainTitle(getString(R.string.my_driver_add_title));
        mToolbar.setLeftTitleVisible();
        mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
