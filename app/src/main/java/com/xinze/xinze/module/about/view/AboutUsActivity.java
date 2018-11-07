package com.xinze.xinze.module.about.view;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.ProtocolConfig;
import com.xinze.xinze.module.about.modle.AboutUs;
import com.xinze.xinze.module.about.presenter.AboutUsPresenterImp;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.widget.SimpleToolbar;

import butterknife.BindView;

/**
 * 关于我们
 *
 * @author lxf
 */
public class AboutUsActivity extends BaseActivity implements IAboutUsView {


    @BindView(R.id.allot_driver_toolbar)
    SimpleToolbar mToolbar;
    @BindView(R.id.about_us_content)
    TextView aboutUsContent;
    private AboutUsPresenterImp aupi;
    private String type;

    @Override
    protected int initLayout() {
        return R.layout.about_us_activity;
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra("type");
        aupi = new AboutUsPresenterImp(this,this);
        if (TextUtils.isEmpty(type)){
            type = ProtocolConfig.GOODS_OWNER_SERVICE_PROTOCOL;
            aupi.getAboutUs(type);
        }else{
            aupi.getProtocolByType(type);
        }
        // 初始化标题栏
        initToolbar();

    }

    private void initToolbar() {

        mToolbar.setLeftTitleVisible();
        mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getAboutUsSuccess(String msg) {
        shotToast(msg);
    }

    @Override
    public void getAboutUsFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void getProtocolByTypeSuccess(String msg) {
        shotToast(msg);
    }

    @Override
    public void getProtocolByTypeFailed(String msg) {
        shotToast(msg);
    }

    public void setData(AboutUs data) {
        if (data != null){
            aboutUsContent.setText(data.getContent());
            mToolbar.setMainTitle(data.getTitle());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (aupi != null){
            aupi.onDestroy();
        }
    }

    public void setProtocolData(Protocol data) {
        if (data != null){
            aboutUsContent.setText(Html.fromHtml(data.getContent()));
            mToolbar.setMainTitle(data.getProtocolName());
        }
    }
}
