package com.xinze.xinze.module.select.presenter;

import com.xinze.xinze.module.select.view.ISelectCarView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ISelectCarPresenter extends BasePresenter<ISelectCarView> {
    void getCarryTruckList(String id);
}
