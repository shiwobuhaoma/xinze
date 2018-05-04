package com.xinze.xinze.module.order.presenter;

import com.xinze.xinze.module.order.view.IOrderDetailView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IOrderDetailPresenter extends BasePresenter<IOrderDetailView> {
    void getOrderDetail(String orderId);
}
