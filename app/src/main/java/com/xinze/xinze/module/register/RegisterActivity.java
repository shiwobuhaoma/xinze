package com.xinze.xinze.module.register;

import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.register.presenter.RegisterPresenterImp;
import com.xinze.xinze.module.register.view.IRegisterView;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;


/**
 * @author lxf
 * 注册界面
 * Created by Administrator on 2018/4/1.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {


    @BindView(R.id.register_phone_number_et)
    EditText mRegisterPhoneNumberEdit;
    @BindView(R.id.get_verification_code_bt)
    Button mGetVerificationCode;
    @BindView(R.id.verification_code_et)
    EditText mVerificationCodeEdit;
    @BindView(R.id.pass_word_et)
    EditText mPassWordEdit;
    @BindView(R.id.pass_word_eye_iv)
    ImageView mPassWordEyeIv;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.register_tool_bar)
    SimpleToolbar registerToolBar;

    private String mPhoneNumber;
    private String mVerificationCode;
    private String mPassWord;
    private boolean isVisiable;


    @Override
    protected int initLayout() {
        return R.layout.register_activity;
    }

    @Override
    protected void initView() {
        registerToolBar.setMainTitle(getResources().getString(R.string.register));
        mPassWordEyeIv.setOnClickListener(this);
        mGetVerificationCode.setOnClickListener(this);
        mRegister.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_verification_code_bt:

                break;
            case R.id.pass_word_eye_iv:
                isVisiable = !isVisiable;
                if (isVisiable) {
                    mPassWordEyeIv.setBackgroundResource(R.mipmap.eye_open);
                    mPassWordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable editable = mPassWordEdit.getText();
                    Selection.setSelection(editable, editable.length());
                } else {
                    mPassWordEyeIv.setBackgroundResource(R.mipmap.eye_close);
                    mPassWordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable editable = mPassWordEdit.getText();
                    Selection.setSelection(editable, editable.length());
                }
                break;
            case R.id.register:
                mPhoneNumber = mRegisterPhoneNumberEdit.getText().toString().trim();
                mVerificationCode = mVerificationCodeEdit.getText().toString().trim();
                mPassWord = mPassWordEdit.getText().toString().trim();
                if (TextUtils.isEmpty(mPhoneNumber)) {
                    shotToast("手机号码不能为空");
                    return;
                } else {
                    if (mPhoneNumber.length() != 11) {
                        shotToast("手机号码位数不对");
                        return;
                    } else {
                        if (TextUtils.isEmpty(mVerificationCode)) {
                            shotToast("验证码不能为空");
                            return;
                        } else {
                            if (TextUtils.isEmpty(mPassWord)) {
                                shotToast("密码不能为空");
                                return;
                            } else {
                                RegisterPresenterImp rpi = new RegisterPresenterImp(this);
                                rpi.register(mPhoneNumber, mVerificationCode, mPassWord);
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void registerSuccess() {
        shotToast("注册成功");
        finish();
    }

    @Override
    public void registerFailed() {
        shotToast("注册失败");
    }


}
