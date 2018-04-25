package com.xinze.xinze.module.forget;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * @author lxf
 * Created by Administrator on 2018/4/1.
 * 忘记密码
 */

public class ForgetPassWordActivity extends BaseActivity {


    @BindView(R.id.forget_tool_bar)
    SimpleToolbar forgetToolBar;

    @Override
    protected int initLayout() {
        return R.layout.forget_activity;
    }

    @Override
    protected void initView() {
        forgetToolBar.setMainTitle(getResources().getString(R.string.forget_pwd));
    }
}
