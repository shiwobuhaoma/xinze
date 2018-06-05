package com.xinze.xinze.module.distributive.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface IDistributiveDriverView extends BaseView {
    void appointDriver4TruckSuccess(String msg);

    void appointDriver4TruckFailed(String msg);

    void getMyTruckDriversSuccess(String msg);

    void getMyTruckDriversFailed(String msg);
}
