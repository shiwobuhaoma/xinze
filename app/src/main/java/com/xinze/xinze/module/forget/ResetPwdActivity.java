package com.xinze.xinze.module.forget;

import android.content.Intent;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/13.
 * desc:
 */

public class ResetPwdActivity extends BaseActivity {
    @BindView(R.id.reset_pwd_tool_bar)
    SimpleToolbar mResetPwdToolbar;

    private String mPhone;


    @Override
    protected int initLayout() {
        return R.layout.reset_pwd_activity;
    }

    @Override
    protected void initView() {
        mResetPwdToolbar.setMainTitle("重置密码");
        mResetPwdToolbar.setTitleMarginTop();
        Intent intent = getIntent();
        mPhone = intent.getStringExtra("phone");
        shotToast(mPhone);
    }
}
