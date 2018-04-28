package com.xinze.xinze.module.certification;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.certification.adapter.CertificationRecycleViewAdapter;
import com.xinze.xinze.module.certification.bean.CertificationRecycleViewItem;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 司机认证
 *
 * @author lxf
 */
public class CertificationActivity extends BaseActivity {


    @BindView(R.id.certification_rv)
    RecyclerView certificationRv;
    @BindView(R.id.certification_bt)
    Button certificationBt;
    @BindView(R.id.certification_tool_bar)
    SimpleToolbar certificationToolBar;
    private List<CertificationRecycleViewItem> mbs = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.certification_activity;
    }

    @Override
    protected void initView() {

        certificationToolBar.setMainTitle("司机认证");
        certificationToolBar.setLeftTitleDrawable(R.mipmap.ic_back);
        certificationToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CertificationRecycleViewItem name = new CertificationRecycleViewItem("姓        名", false, false, true, true, "请真实输入姓名");
        CertificationRecycleViewItem idCard = new CertificationRecycleViewItem("身份证号", false, false, true, true, "请输入身份证号");
        CertificationRecycleViewItem address = new CertificationRecycleViewItem("现居住地", true, false, true, true, "请选择所在地区");
        CertificationRecycleViewItem detailAddress = new CertificationRecycleViewItem("详细地址", false, true, true, true, "请输入详细地址（不包含省市）");
        CertificationRecycleViewItem upload = new CertificationRecycleViewItem("资质上传", false, false, true, false, "");
        CertificationRecycleViewItem photo = new CertificationRecycleViewItem("身份证正面照", false, false, false, false, "驾驶证正面照");

        mbs.add(name);
        mbs.add(idCard);
        mbs.add(address);
        mbs.add(detailAddress);
        mbs.add(upload);
        mbs.add(photo);

        certificationRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CertificationRecycleViewAdapter cfra = new CertificationRecycleViewAdapter(this, mbs);
        certificationRv.setAdapter(cfra);
    }


    @OnClick(R.id.certification_bt)
    public void onViewClicked() {
    }

}
