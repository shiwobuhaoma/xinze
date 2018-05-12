package com.xinze.xinze.module.select.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.select.view.SelectCarActivity;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.module.select.view.ISelectCarView;

import java.util.List;
import java.util.Map;

public class SelectCarPresenterImp extends BasePresenterImpl<ISelectCarView> implements ISelectCarPresenter {
    private SelectCarActivity mSelectCarActivity;
    public SelectCarPresenterImp(ISelectCarView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mSelectCarActivity = (SelectCarActivity)mPresenterView;
    }

    @Override
    public void getCarryTruckList(String id) {
        Map headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getCarryTruckList(headers,id).compose(this.<BaseEntity<List<Car>>>setThread()).subscribe(new BaseObserver<List<Car>>() {
            @Override
            protected void onSuccees(BaseEntity<List<Car>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<Car> data = t.getData();
                        mSelectCarActivity.setData(data);
                        mSelectCarActivity.getCarryTruckListSuccess(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mSelectCarActivity.getCarryTruckListFailed(e.getMessage());
            }
        });
    }
}
