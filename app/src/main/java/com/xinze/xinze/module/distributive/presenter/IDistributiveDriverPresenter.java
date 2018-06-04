package com.xinze.xinze.module.distributive.presenter;

import com.xinze.xinze.module.distributive.view.IDistributiveDriverView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IDistributiveDriverPresenter extends BasePresenter<IDistributiveDriverView> {
    void appointDriver4Truck(String truckId, String driverId, String rightFlag, String id);
    void getMyTruckDrivers(int pageNum, int pageSize,String inviteFlag);
}
