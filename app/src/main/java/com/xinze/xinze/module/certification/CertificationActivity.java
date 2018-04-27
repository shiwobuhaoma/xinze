package com.xinze.xinze.module.certification;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.certification.adapter.CertificationRecycleViewAdapter;
import com.xinze.xinze.module.certification.bean.CertificationRecycleViewItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertificationActivity extends BaseActivity {


    @BindView(R.id.certification_rv)
    RecyclerView certificationRv;
    @BindView(R.id.certification_bt)
    Button certificationBt;
    private List<CertificationRecycleViewItem> mbs = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.certification_activity;
    }

    @Override
    protected void initView() {
        CertificationRecycleViewItem name = new CertificationRecycleViewItem("姓名", false, false, true, true, "请真实输入姓名");
        CertificationRecycleViewItem idCard = new CertificationRecycleViewItem("身份证号码", false, false, true, true, "请输入身份证号");
        CertificationRecycleViewItem address = new CertificationRecycleViewItem("现居住地", true, false, true, true, "请选择所在地区");
        CertificationRecycleViewItem detailAddress = new CertificationRecycleViewItem("详细地址", false, true, true, true, "请输入详细地址（不包含省市）");
        CertificationRecycleViewItem upload = new CertificationRecycleViewItem("资质上传", false, false, true, false, "");
        mbs.add(name);
        mbs.add(idCard);
        mbs.add(address);
        mbs.add(detailAddress);
        mbs.add(upload);

        certificationRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CertificationRecycleViewAdapter cfra = new CertificationRecycleViewAdapter(this, mbs);
        certificationRv.setAdapter(cfra);
    }


    @OnClick(R.id.certification_bt)
    public void onViewClicked() {
    }
}
