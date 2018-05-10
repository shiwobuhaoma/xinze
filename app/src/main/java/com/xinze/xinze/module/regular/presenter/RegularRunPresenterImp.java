package com.xinze.xinze.module.regular.presenter;

import android.app.Activity;
import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.regular.activity.RegularRunActivity;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.module.regular.view.IRegularRouteView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import java.util.List;
import java.util.Map;

public class RegularRunPresenterImp extends BasePresenterImpl<IRegularRouteView> implements IRegularRunPresenter {
    private RegularRunActivity mRegularRunActivity;
    public RegularRunPresenterImp(IRegularRouteView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mRegularRunActivity = (RegularRunActivity)mPresenterView;
    }

    @Override
    public void getRegularRouteList() {
        @SuppressWarnings("unchecked")
        Map<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getRegularRouteList(headers).compose(this.<BaseEntity<List<Route>>>setThread()).subscribe(new BaseObserver<List<Route>>() {
            @Override
            protected void onSuccees(BaseEntity<List<Route>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<Route> data = t.getData();
                        mRegularRunActivity.setRouteData(data);
                    }
                }

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mRegularRunActivity.getRegularRouteListFailed(e.getMessage());
            }
        });
    }

    @Override
    public void searchRouteList(String fromAreaId,String toAreaId,int pageNo,int pageSize) {
        @SuppressWarnings("unchecked")
        Map<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().searchRoute(headers,fromAreaId,toAreaId,pageNo,pageSize).compose(this.<BaseEntity<List<OrderItem>>>setThread()).subscribe(new BaseObserver<List<OrderItem>>() {
            @Override
            protected void onSuccees(BaseEntity<List<OrderItem>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<OrderItem> data = t.getData();
                        mRegularRunActivity.setOrderItemData(data);
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mRegularRunActivity.searchRouteListFailed(e.getMessage());
            }
        });
    }
}
