package com.xinze.xinze.module.forget;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.forget.presenter.ForgetPwdPresenterImpl;
import com.xinze.xinze.module.forget.view.IForgetPwdView;
import com.xinze.xinze.utils.CountDownButtonHelper;
import com.xinze.xinze.utils.ValidatorUtil;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
* @author feibai
* @date   2018/5/11 23:23
* desc:
*/
public class ForgetPassWordActivity extends BaseActivity implements View.OnClickListener, IForgetPwdView {


    @BindView(R.id.forget_tool_bar)
    SimpleToolbar forgetToolBar;
    @BindView(R.id.forget_phone_number_et)
    EditText mForgetPhoneEditText;
    @BindView(R.id.forget_verify_code_bt)
    Button mForgetVerifyCodeButton;
    @BindView(R.id.forget_verifiy_code_et)
    EditText mForgetVerifiyCodeEditText;
    @BindView(R.id.forget_next_bt)
    Button mForgetNextButton;
    private String mPhoneNum;


    private ForgetPwdPresenterImpl mPresenter;
    private CountDownButtonHelper helper;


    @Override
    protected int initLayout() {
        return R.layout.forget_activity;
    }


    @Override
    protected void initView() {
        forgetToolBar.setMainTitle(getString(R.string.forget_pwd));
        forgetToolBar.setTitleMarginTop();
        mForgetVerifyCodeButton.setOnClickListener(this);
        mForgetNextButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new ForgetPwdPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        // mPhoneNum = mForgetPhoneEditText.getText();

        switch (v.getId()) {
            case R.id.forget_verify_code_bt:
                // 获取验证码
                getVerifyCode();
                break;
            case R.id.forget_next_bt:
                //下一步
                String writeVerifyCode = mForgetVerifiyCodeEditText.getText().toString().trim();

                if (TextUtils.isEmpty(writeVerifyCode)) {
                    shotToast("验证码不能为空");
                    return;
                }
                // 校验验证码
                if (TextUtils.isEmpty(mPhoneNum)) {
                    mPhoneNum = mForgetPhoneEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(mPhoneNum)) {
                        shotToast("手机号码不能为空");
                        return;
                    }

                    if (!ValidatorUtil.isMobile(mPhoneNum)) {
                        shotToast("手机号码格式不正确");
                        return;
                    }
                }
                mPresenter.checkVerfifyCode(mPhoneNum,writeVerifyCode);
                break;

            default:
                break;
        }
    }
    
    /**
     * @param
     * @return
     * @author feibai
     * @time 2018/5/13  21:22
     * @describe 获取验证码
     */
    private void getVerifyCode() {
        //获取验证码
        mPhoneNum = mForgetPhoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNum)) {
            shotToast("手机号码不能为空");
            return;
        }

        if (!ValidatorUtil.isMobile(mPhoneNum)) {
            shotToast("手机号码格式不正确");
            return;
        }
        // 点击后背景色修改恢复为灰色
        mForgetVerifyCodeButton.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
        // 设置倒计时
        helper = new CountDownButtonHelper(mForgetVerifyCodeButton, "再次获取", AppConfig.VIERFY_CODE_DELAY_TIME, 1);
        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

            @Override
            public void finish() {
                // 倒计时结束恢复原有背景
                mForgetVerifyCodeButton.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
            }
        });
        helper.start();
        mPresenter.getVerfifyCode(mPhoneNum, AppConfig.VIERFY_CODE_TYPE_OTHER);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null){
            helper.stop();
        }
    }
}
