package com.xinze.xinze.module.forget;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.utils.ValidatorUtil;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/*
* @author feibai
* @time  2018/5/11 23:23
* desc:
*/
public class ForgetPassWordActivity extends BaseActivity implements View.OnClickListener {


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


    @Override
    protected int initLayout() {
        return R.layout.forget_activity;
    }


    @Override
    protected void initView() {
        forgetToolBar.setMainTitle(getResources().getString(R.string.forget_pwd));
        mForgetVerifyCodeButton.setOnClickListener(this);
        mForgetNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       // mPhoneNum = mForgetPhoneEditText.getText();
        switch (v.getId()) {
            case R.id.forget_verify_code_bt:
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
            break;
            case R.id.forget_next_bt:
                //下一步

            break;

            default:
            break;
        }

    }


    public String test(String s){

        return null;
    }
}
