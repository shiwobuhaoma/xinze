package com.xinze.xinze.module.select.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface ISelectCarView extends BaseView {
    void getCarryTruckListSuccess(String msg);
    void getCarryTruckListFailed(String msg);
    void getProtocolByTypeSuccess(String msg);
    void getProtocolByTypeFailed(String msg);
    void createBillOrderSuccess(String msg);
    void createBillOrderFailed(String msg);
}
