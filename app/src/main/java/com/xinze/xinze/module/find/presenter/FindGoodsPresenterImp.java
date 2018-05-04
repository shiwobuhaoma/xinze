package com.xinze.xinze.module.find.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.find.FindGoodsActivity;
import com.xinze.xinze.module.find.view.IFindGoodsView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.Map;

public class FindGoodsPresenterImp extends BasePresenterImpl<IFindGoodsView> implements IFindGoodsPresenter{
    private FindGoodsActivity fga;
    public FindGoodsPresenterImp(IFindGoodsView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        fga = (FindGoodsActivity)mPresenterView;
    }

//    @Override
//    public void getOrderDetail(String orderId) {
//        Map<String, String> headers = new HashMap<>(2);
//        headers.put("sessionid", App.mUser.getSessionid());
//        headers.put("userid",App.mUser.getId());
//
//    }
}
