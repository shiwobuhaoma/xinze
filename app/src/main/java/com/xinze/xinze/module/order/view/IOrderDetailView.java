package com.xinze.xinze.module.order.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface IOrderDetailView extends BaseView{
    void getOrderDetailSuccess(String msg);
    void getOrderDetailFailed(String msg);
    void revokeSuccess(String message,String orderStatus);
    void revokeFailed(String message);
}
