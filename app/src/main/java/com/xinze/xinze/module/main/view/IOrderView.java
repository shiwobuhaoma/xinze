package com.xinze.xinze.module.main.view;

import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.mvpbase.BaseView;

import java.util.List;

public interface IOrderView extends BaseView {
    void getOrderListSuccess();
    void getOrderListFailed();
    void upData(List<OrderItem> data);
    void clearData();
}
