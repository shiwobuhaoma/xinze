package com.xinze.xinze.module.submit;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * @author lxf
 * 司机认证信息提交成功界面
 */
public class DriverSubmitSuccessActivity extends BaseActivity {
    @BindView(R.id.driver_submit_tool_bar)
    SimpleToolbar driverSubmitToolBar;
    @BindView(R.id.tv_driver_submit_success)
    TextView tvDriverSubmitSuccess;

    @Override
    protected int initLayout() {
        return R.layout.driver_submit_success_activity;
    }

    @Override
    protected void initView() {
        initToolBar();
        String success = getString(R.string.driver_submit_success);
        tvDriverSubmitSuccess.setText(Html.fromHtml(success));
    }

    private void initToolBar() {
        driverSubmitToolBar.setMainTitle("司机认证");
        driverSubmitToolBar.setLeftTitleDrawable(R.mipmap.ic_back);
        driverSubmitToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
