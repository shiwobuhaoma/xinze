package com.xinze.xinze.module.line.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.line.view.ILineListView;
import com.xinze.xinze.module.line.view.LineListFragment;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineListPresenterImp extends BasePresenterImpl<ILineListView> implements ILineListPresenter {
    private LineListFragment mLineListFragment;

    public LineListPresenterImp(ILineListView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        this.mLineListFragment = (LineListFragment) mPresenterView;
    }

    @Override
    public void getRegularRouteList() {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getRegularRouteList(headers).compose(this.<BaseEntity<List<Route>>>setThread()).subscribe(new BaseObserver<List<Route>>() {
            @Override
            protected void onSuccess(BaseEntity<List<Route>> t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        List<Route> data = t.getData();
                        mLineListFragment.setRouteData(data);
                        mLineListFragment.getRegularRouteListSuccess(t.getMsg());
                    }else{
                        mLineListFragment.getRegularRouteListFailed(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mLineListFragment.getRegularRouteListFailed(msg);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delRegularRoute(String id) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().delRegularRoute(headers,id).compose(this.<BaseEntity>setThread()).
                subscribe(new BaseObserver(){
                    @Override
                    protected void onSuccess(BaseEntity t) throws Exception {
                        if (t != null) {
                            if (t.isSuccess()) {
                                mLineListFragment.delRegularRouteSuccess(t.getMsg());
                            }else{
                                mLineListFragment.delRegularRouteFailed(t.getMsg());
                            }
                        }

                    }

                    @Override
                    protected void onFailure(String msg) throws Exception {
                        mLineListFragment.delRegularRouteFailed(msg);
                    }
                });
    }

}
