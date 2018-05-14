package com.xinze.xinze.module.forget;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.forget.presenter.ResetPwdPresenterImpl;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/13.
 * desc:
 */

public class ResetPwdActivity extends BaseActivity implements  View.OnClickListener{

    @BindView(R.id.reset_pwd_tool_bar)
    SimpleToolbar mResetPwdToolbar;
    @BindView(R.id.reset_pwd_et)
    EditText mResetPwdEditText;
    @BindView(R.id.reset_comfirm_pwd_et)
    EditText mResetComfirmPwdEditText;
    @BindView(R.id.reset_alert_tv)
    TextView mResetAlertTextView;
    @BindView(R.id.reset_pwd_bt)
    Button mResetPwdButton;





    private String mPhone;
    private String mVerifyCode;
    private ResetPwdPresenterImpl mPresenter;
    private String mPwd;


    @Override
    protected int initLayout() {
        return R.layout.reset_pwd_activity;
    }

    @Override
    protected void initView() {
        mResetPwdToolbar.setMainTitle("重置密码");
        mResetPwdToolbar.setTitleMarginTop();
        mResetPwdButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mPhone = intent.getStringExtra("phone");
        mVerifyCode = intent.getStringExtra("verifyCode");
        mPresenter = new ResetPwdPresenterImpl(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_pwd_bt:
                resetPwd();
                break;

            default:
                break;
        }

    }

    private void resetPwd() {
        mPwd = mResetPwdEditText.getText().toString().trim();
        String confirmPwd = mResetComfirmPwdEditText.getText().toString().trim();
        if (TextUtils.isEmpty(mPwd)) {
            shotToast("新密码密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(confirmPwd)) {
            shotToast("确认密码不能为空");
            return;
        }
        if (!mPwd.equals(confirmPwd)) {
            mResetAlertTextView.setText("两次密码输入不一致");
            mResetAlertTextView.setVisibility(View.VISIBLE);
            return;
        }

        mPresenter.resetPwd(mPhone,mVerifyCode,mPwd);
    }


}

