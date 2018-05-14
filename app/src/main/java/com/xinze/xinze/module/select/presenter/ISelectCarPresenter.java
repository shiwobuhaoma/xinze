package com.xinze.xinze.module.select.presenter;

import com.xinze.xinze.module.select.view.ISelectCarView;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.mvpbase.BasePresenter;

import java.util.List;

public interface ISelectCarPresenter extends BasePresenter<ISelectCarView> {
    void getCarryTruckList(String id);
    void getProtocolByType(String protocolType);
    void createBillOrder(String wayBillid, List<Car> list);
}
