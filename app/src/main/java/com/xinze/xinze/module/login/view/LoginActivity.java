package com.xinze.xinze.module.login.view;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.config.MainConfig;
import com.xinze.xinze.module.forget.ForgetPassWordActivity;
import com.xinze.xinze.module.login.modle.UserEntity;
import com.xinze.xinze.module.login.presenter.LoginPresenterImp;
import com.xinze.xinze.module.main.activity.MainActivity;
import com.xinze.xinze.module.register.view.RegisterActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

/**
 * 登陆界面
 *
 * @author lxf
 * Created by Administrator on 2017/12/7.
 */

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {


    @BindView(R.id.usernameWrapper)
    TextInputLayout usernameWrapper;
    @BindView(R.id.passwordWrapper)
    TextInputLayout passwordWrapper;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;
    @BindView(R.id.register)
    TextView register;

    private LoginPresenterImp loginPresenterImp;
    private String mUserPwd;
    private String mUserName;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private EditText mUserEditText;
    private EditText mPwdEditText;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenterImp != null) {
            loginPresenterImp.onDestroy();
        }

    }

    @Override
    protected int initLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {
        forgetPwd.setOnClickListener(this);
        register.setOnClickListener(this);
        mUserEditText = usernameWrapper.getEditText();
        mUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    usernameWrapper.setErrorEnabled(false);
                } else if (s.length() < 11) {
//                    usernameWrapper.setError("长度不能小于11位");
                    usernameWrapper.setErrorEnabled(true);
                } else {
                    usernameWrapper.setErrorEnabled(false);
//                    if (!validateEmail(s.toString())) {
//                        usernameWrapper.setError("无效的账户");
//
//                    } else {
//                        usernameWrapper.setErrorEnabled(false);
//                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPwdEditText = passwordWrapper.getEditText();
        mPwdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    passwordWrapper.setErrorEnabled(false);
                } else if (s.length() < 2) {
//                    passwordWrapper.setError("密码长度不能小于2位");
                    passwordWrapper.setErrorEnabled(true);
                } else {
                    passwordWrapper.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenterImp = new LoginPresenterImp(LoginActivity.this, LoginActivity.this);
                mUserName = mUserEditText.getText().toString();
                mUserPwd = mPwdEditText.getText().toString();
                UserEntity user = new UserEntity();
                user.setName(mUserName);
                user.setPassword(mUserPwd);
                if (TextUtils.isEmpty(mUserName)) {
                    shotToast("请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(mUserPwd)) {
                    shotToast("请输入密码");
                    return;
                }
                //工号和手机号位数判断
                if (mUserName.length() != 11 ) {
                    shotToast("请正确输入11位手机号");
                    return;

                    //手机号格式判断
                } else {
                    if (!isMobileNO(mUserName)) {
                        shotToast("手机号格式不正确");
                        return;
                    }
                }

                if (mUserPwd.length() < 6) {
                    shotToast("密码不得少于6个字符，数字/字母/组合");
                    return;
                }

                loginPresenterImp.login(user.getName(), user.getPassword(), AppConfig.DRIVER);
            }
        });
    }


    private boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    public void loginSuccess(String msg) {
        shotToast("登录成功");
        //跳转到我的fragment
        MainActivity.currentFragment = MainConfig.HOME_FRAGMENT;
        finish();
    }


    @Override
    public void loginFailed(String msg) {
        shotToast(msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_pwd:
                startActivity(new Intent(this, ForgetPassWordActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 验证手机格式
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 新增：147、170、177
     * 总结起来就是第一位必定为1，第二位必定为3、4、5、7、8，其他位置的可以为0-9
     */
    public static boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，
        // "\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }
}
