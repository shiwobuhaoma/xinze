package com.xinze.xinze.module.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.xinze.xinze.module.login.view.ILoginView;
import com.xinze.xinze.module.main.activity.MainActivity;
import com.xinze.xinze.module.register.RegisterActivity;

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
    //@BindView(R.id.login_tool_bar)
    //SimpleToolbar loginToolBar;
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
            loginPresenterImp.detachView();
        }

    }

    @Override
    protected int initLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {
        //loginToolBar.setMainTitle(getResources().getString(R.string.login));
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
                } else if (s.length() < 6) {
                    usernameWrapper.setError("长度不能小于6位");
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
                    passwordWrapper.setError("密码长度不能小于2位");
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
                hideKeyboard();
                loginPresenterImp = new LoginPresenterImp(LoginActivity.this,LoginActivity.this);
                mUserName = mUserEditText.getText().toString();
                mUserPwd = mPwdEditText.getText().toString();
                UserEntity user = new UserEntity();
                user.setName(mUserName);
                user.setPassword(mUserPwd);
                if (TextUtils.isEmpty(mUserName)) {
                    shotToast("请先输入帐号");
                    return;
                }
                if (TextUtils.isEmpty(mUserPwd)) {
                    shotToast("请输入密码");
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

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void loginSuccess() {
        shotToast("登陆成功");
        //跳转到我的fragment
        MainActivity.currentFragment= MainConfig.MY_FRAGMENT;
        finish();
    }


    @Override
    public void loginFailed() {
        shotToast("登陆失败");
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
}
