package com.xinze.xinze.module.distributive.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.distributive.view.DistributiveDriverActivity;
import com.xinze.xinze.module.distributive.view.IDistributiveDriverView;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;

public class DistributiveDriverPresenter extends BasePresenterImpl<IDistributiveDriverView> implements IDistributiveDriverPresenter {

    private DistributiveDriverActivity distributiveDriverActivity;

    public DistributiveDriverPresenter(IDistributiveDriverView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        distributiveDriverActivity = (DistributiveDriverActivity) mPresenterView;
    }

    @Override
    public void appointDriver4Truck(String truckId, String driverId, String rightFlag, String id) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().appointDriver4Truck(headers, truckId, driverId, rightFlag, id).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver() {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        distributiveDriverActivity.appointDriver4TruckSuccess(t.getMsg());
                    } else {
                        distributiveDriverActivity.appointDriver4TruckFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                distributiveDriverActivity.appointDriver4TruckFailed(e.getMessage());
            }
        });
    }

    @Override
    public void getMyTruckDrivers(int pageNum, int pageSize, String inviteFlag) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getMyTruckDrivers(headers, pageNum, pageSize, inviteFlag).compose(this.<BaseEntity<List<TruckownerDriverVO>>>setThread()).subscribe(new BaseObserver<List<TruckownerDriverVO>>() {
            @Override
            protected void onSuccees(BaseEntity<List<TruckownerDriverVO>> t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        distributiveDriverActivity.setData(t.getData());
                        distributiveDriverActivity.getMyTruckDriversSuccess(t.getMsg());
                    } else {
                        distributiveDriverActivity.getMyTruckDriversFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                distributiveDriverActivity.getMyTruckDriversFailed(e.getMessage());
            }
        });
    }


}
