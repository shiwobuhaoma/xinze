package com.xinze.xinze.module.line.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.line.view.ILineEditView;
import com.xinze.xinze.module.line.view.LineEditFragment;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.entity.Province;

public class LineEditPresenterImp extends BasePresenterImpl<ILineEditView> implements ILineEditPresenter {

    private LineEditFragment mLineEditFragment;

    public LineEditPresenterImp(ILineEditView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mLineEditFragment = (LineEditFragment)mPresenterView;
    }

    @Override
    public void addRegularRoute(String fromAreaId,String toAreaId) {
        @SuppressWarnings("unchecked")
        Map<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().addRegularRoute(headers,fromAreaId,toAreaId).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver() {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
//                        mLineEditFragment.addRegularRouteSuccess(t.getMsg());
                    }else{
                        mLineEditFragment.addRegularRouteFailed(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mLineEditFragment.addRegularRouteFailed(e.getMessage());
            }
        });
    }

    @Override
    public void editRegularRoute(String id,String fromAreaId, String toAreaId) {
        @SuppressWarnings("unchecked")
        Map<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().editRegularRoute(headers,id,fromAreaId,toAreaId).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver() {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        mLineEditFragment.addRegularRouteSuccess(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mLineEditFragment.addRegularRouteFailed(e.getMessage());
            }
        });
    }

    @Override
    public void getAreaList(String extId) {
        @SuppressWarnings("unchecked")
        Map<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getAreaList(headers,extId)
                .compose(this.<BaseEntity<List<Province>>>setThread()).subscribe(new BaseObserver<List<Province>>(){

            @Override
            protected void onSuccees(BaseEntity<List<Province>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        ArrayList<Province> data = (ArrayList<Province>) t.getData();
                        if (data != null){
                            mLineEditFragment.setAreaList(data);
                        }
                        mLineEditFragment.getAreaListSuccess(t.getMsg());
                    }else {
                        mLineEditFragment.getAreaListFailed(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }

}
