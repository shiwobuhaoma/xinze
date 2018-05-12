package com.xinze.xinze.module.select.view;

import com.xinze.xinze.App;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.select.presenter.SelectCarPresenterImp;
import com.xinze.xinze.module.select.view.ISelectCarView;
import com.xinze.xinze.module.transport.module.Car;

import java.util.List;


/**
 * 选择车辆
 * @author lxf
 */
public class SelectCarActivity extends BaseActivity implements ISelectCarView {
    @Override
    protected int initLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        super.initData();
        SelectCarPresenterImp scp = new SelectCarPresenterImp(this,this);
        scp.getCarryTruckList(App.mUser.getId());
    }

    @Override
    public void getCarryTruckListSuccess(String msg) {

    }

    @Override
    public void getCarryTruckListFailed(String msg) {

    }

    public void setData(List<Car> data) {

    }
}
