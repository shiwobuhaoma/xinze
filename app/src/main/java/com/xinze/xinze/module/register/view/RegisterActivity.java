package com.xinze.xinze.module.register.view;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.config.ProtocolConfig;
import com.xinze.xinze.module.about.view.AboutUsActivity;
import com.xinze.xinze.module.register.presenter.RegisterPresenterImp;
import com.xinze.xinze.utils.CountDownButtonHelper;
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
    @BindView(R.id.tv_register_read)
    TextView mRegisterRead;
    @BindView(R.id.register_protocol_iv)
    ImageView registerProtocolIv;
    @BindView(R.id.register_protocol_fl)
    FrameLayout registerProtocolFl;

    private boolean isSelected = true;
    private String mPhoneNumber;
    private String mVerificationCode;
    private String mPassWord;
    private boolean isVisible;
    private String type = "1";
    private CountDownButtonHelper helper;


    @Override
    protected int initLayout() {
        return R.layout.register_activity;
    }

    @Override
    protected void initView() {

        registerToolBar.setMainTitle(getString(R.string.register));
        registerToolBar.setTitleMarginTop();
        mPassWordEyeIv.setOnClickListener(this);
        mGetVerificationCode.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mRegisterRead.setText(Html.fromHtml(getString(R.string.register_read_service)));
        mRegisterRead.setOnClickListener(this);
        registerProtocolFl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        RegisterPresenterImp rpi = new RegisterPresenterImp(this, this);
        mPhoneNumber = mRegisterPhoneNumberEdit.getText().toString().trim();
        switch (v.getId()) {
            case R.id.get_verification_code_bt:
                //TODO 缺少增加手机号码规格验证
                if (TextUtils.isEmpty(mPhoneNumber)) {
                    shotToast("手机号码不能为空");
                    return;
                }
                if (mPhoneNumber.length() != 11) {
                    shotToast("手机号码位数不对");
                    return;
                }
                mGetVerificationCode.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
                // 设置倒计时
                helper = new CountDownButtonHelper(mGetVerificationCode, "重新获取", AppConfig.VIERFY_CODE_DELAY_TIME, 1);
                helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

                    @Override
                    public void finish() {
                        // 倒计时结束恢复原有背景
                        mGetVerificationCode.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
                    }
                });
                helper.start();
                rpi.getVerificationCode(mPhoneNumber, type);
                break;
            case R.id.pass_word_eye_iv:
                isVisible = !isVisible;
                if (isVisible) {
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

                                rpi.register(mPhoneNumber, mVerificationCode, mPassWord, type, AppConfig.DRIVER);
                            }
                        }
                    }
                }
                break;
            case R.id.register_protocol_fl:

                if (isSelected) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.my_driver_selected);
                    registerProtocolIv.setBackground(drawable);

                } else {
                    Drawable drawable = getResources().getDrawable(R.mipmap.my_driver_unselected);
                    registerProtocolIv.setBackground(drawable);
                }
                isSelected = !isSelected;

                break;
            case R.id.tv_register_read:
                openActivity(AboutUsActivity.class, "type", ProtocolConfig.DRIVER_SERVICE_PROTOCOL);
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
    public void registerFailed(String msg) {
        helper.stop();
        shotToast(msg);
    }

    @Override
    public void getVerificationCodeSuccess() {
        shotToast("获取成功");
    }

    @Override
    public void getVerificationCodeFailed() {
        shotToast("获取失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.stop();
        }

    }
}
