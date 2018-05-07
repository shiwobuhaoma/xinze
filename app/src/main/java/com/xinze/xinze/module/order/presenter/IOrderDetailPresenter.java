package com.xinze.xinze.module.order.presenter;

import com.xinze.xinze.module.order.view.IOrderDetailView;
import com.xinze.xinze.mvpbase.BasePresenter;

import java.io.File;
import java.util.List;

public interface IOrderDetailPresenter extends BasePresenter<IOrderDetailView> {
    void getOrderDetail(String orderId);
    void revoke(String id, List<File> files, String remarks, String orderStatus);
}
