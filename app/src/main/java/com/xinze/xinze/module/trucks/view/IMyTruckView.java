package com.xinze.xinze.module.trucks.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface IMyTruckView extends BaseView {
    void deleteMyTruckSuccess(String msg);
    void deleteMyTruckFailed(String msg);
}
