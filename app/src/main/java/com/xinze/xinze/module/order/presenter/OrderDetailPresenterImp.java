package com.xinze.xinze.module.order.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.find.view.IFindGoodsView;
import com.xinze.xinze.module.order.OrderDetailActivity;
import com.xinze.xinze.module.order.modle.OrderDetail;
import com.xinze.xinze.module.order.view.IOrderDetailView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailPresenterImp extends BasePresenterImpl<IOrderDetailView> implements IOrderDetailPresenter {
    private OrderDetailActivity fga;
    public OrderDetailPresenterImp(IOrderDetailView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        fga = (OrderDetailActivity)mPresenterView;
    }

    @Override
    public void getOrderDetail(String orderId) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid",App.mUser.getId());
        RetrofitFactory.getInstence().Api().getBillOrderDetail(headers,orderId).compose(this.<BaseEntity<OrderDetail>>setThread()).subscribe(new BaseObserver<OrderDetail>() {
            @Override
            protected void onSuccees(BaseEntity<OrderDetail> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        fga.setData(t.getData());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

            }
        });
    }
}
