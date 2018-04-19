package com.xinze.xinze.forget;

import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;

import butterknife.BindView;

/**
 * @author lxf
 * Created by Administrator on 2018/4/1.
 * 忘记密码
 */

public class ForgetPassWordActivity extends BaseActivity {


    @BindView(R.id.title)
    TextView mTitle;

    @Override
    protected int initLayout() {
        return R.layout.forget_activity;
    }

    @Override
    protected void initView() {
        mTitle.setText(getResources().getString(R.string.forget_pwd));
    }
}
