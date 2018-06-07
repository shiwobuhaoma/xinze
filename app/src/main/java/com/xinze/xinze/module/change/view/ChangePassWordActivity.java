package com.xinze.xinze.module.change.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.change.presenter.ChangePassWordPresenterImp;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码
 *
 * @author lxf
 */
public class ChangePassWordActivity extends BaseActivity implements IChangePassWordView {
    @BindView(R.id.change_tool_bar)
    SimpleToolbar changeToolBar;
    @BindView(R.id.change_old_pass_word_et)
    EditText changeOldPassWordEt;
    @BindView(R.id.change_new_pass_word_et)
    EditText changeNewPassWordEt;
    @BindView(R.id.change_new_pass_word2_et)
    EditText changeNewPassWord2Et;
    @BindView(R.id.change_next_bt)
    Button changeNextBt;
    private ChangePassWordPresenterImp mPresenter;

    @Override
    protected int initLayout() {
        return R.layout.change_pass_word_acitivity;
    }

    @Override
    protected void initView() {
        initToolBar();
    }

    private void initToolBar() {
        changeToolBar.setLeftTitleVisible();
        changeToolBar.setMainTitle("修改密码");
        changeToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.change_next_bt)
    public void onClick() {
        String oldPassWord = changeOldPassWordEt.getText().toString().trim();
        String newPassWord = changeNewPassWordEt.getText().toString().trim();
        String newPassWord2 = changeNewPassWord2Et.getText().toString().trim();
        if (TextUtils.isEmpty(oldPassWord)) {
            shotToast("请输入旧密码");
            return;
        } else {
//            if ( oldPassWord.length()<6){
//                shotToast("请输入旧密码");
//                return;
//            }

        }
        if (TextUtils.isEmpty(newPassWord)) {
            shotToast("请输入新密码");
            return;
        } else {
            if (newPassWord.length() < 6) {
                shotToast("密码不能低于6位");
                return;
            }
        }
        if (TextUtils.isEmpty(newPassWord2)) {
            shotToast("请重新输入新密码");
            return;
        } else {
            if (newPassWord2.length() < 6) {
                shotToast("密码不能低于6位");
                return;
            }
        }
        if (!TextUtils.equals(newPassWord, newPassWord2)) {
            shotToast("两次密码不一致");
        }else{
            mPresenter = new ChangePassWordPresenterImp(this,this);
            mPresenter.changePassWord(oldPassWord,newPassWord);
        }
    }

    @Override
    public void changePassWordSuccess(String msg) {
        shotToast("修改成功");
        finish();
    }

    @Override
    public void changePassWordFailed(String msg) {
        shotToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
    }
}
