package com.xinze.xinze.module.allot.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.allot.view.AllotDriverActivity;
import com.xinze.xinze.module.allot.view.IAllotDriverView;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllotDriverPresenterImp extends BasePresenterImpl<IAllotDriverView> implements IAllotDriverPresenter {
    private AllotDriverActivity mActivity;
    public AllotDriverPresenterImp(IAllotDriverView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mActivity = (AllotDriverActivity)mPresenterView;
    }


    @Override
    public void getMyTruckDrivers(int pageNum, int pageSize, String verifyFlag) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().getMyTrucksList(headers, pageNum, pageSize, verifyFlag)
                .compose(this.<BaseEntity<List<TruckownerDriverVO>>>setThread()).subscribe(new BaseObserver<List<TruckownerDriverVO>>(){

            @Override
            protected void onSuccees(BaseEntity<List<TruckownerDriverVO>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        mActivity.setData(t.getData());
//                        mActivity.getMyTruckDriversSuccess(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mActivity.getMyTruckDriversFailed(e.getMessage());
            }
        });
    }

    @Override
    public void truckAllotDriver(String truckId, String driverId, String rightFlag, String id) {

    }
}
