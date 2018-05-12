package com.xinze.xinze.module.transport.presenter;

import com.xinze.xinze.module.transport.view.ITransportDetailsView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ITransportDetailsPresenter extends BasePresenter<ITransportDetailsView> {
    void getCarryOrderRight(String userId);
    void getBillDetail(String id);
}
