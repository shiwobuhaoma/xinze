package com.xinze.xinze.module.transport.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.transport.view.TransportDetailsActivity;
import com.xinze.xinze.module.transport.module.TransportDetails;
import com.xinze.xinze.module.transport.view.ITransportDetailsView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.Map;

public class TransportDetailsPresenterImp extends BasePresenterImpl<ITransportDetailsView> implements ITransportDetailsPresenter{
    private TransportDetailsActivity mTransportDetailsActivity;
    public TransportDetailsPresenterImp(ITransportDetailsView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mTransportDetailsActivity = (TransportDetailsActivity) mPresenterView;
    }


    @Override
    public void getBillDetail(String orderId) {
        @SuppressWarnings("unchecked")
        Map<String,String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getBillDetail(headers,orderId).
                compose(this.<BaseEntity<TransportDetails>>setThread()).subscribe(new BaseObserver<TransportDetails>(mContext) {
            @Override
            protected void onSuccees(BaseEntity<TransportDetails> t) throws Exception {
                if (t != null){
                    if(t.isSuccess()){
                        mTransportDetailsActivity.setData(t.getData());
//                        mTransportDetailsActivity.getBillDetailSuccess(t.getMsg());
                    }else{
                        mTransportDetailsActivity.getBillDetailFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mTransportDetailsActivity.getBillDetailFailed(e.getMessage());
            }
        });
    }

    @Override
    public void backBill(String id) {
        @SuppressWarnings("unchecked")
        Map<String,String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().backBill(headers,id).
                compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(mContext) {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null){
                    if(t.isSuccess()){
//                        mTransportDetailsActivity.backBillSuccess(t.getMsg());
                    }else{
                        mTransportDetailsActivity.backBillSuccess(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mTransportDetailsActivity.backBillSuccess(e.getMessage());
            }
        });
    }

    @Override
    public void getCarryOrderRight(String userId) {
        @SuppressWarnings("unchecked")
        Map<String,String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getCarryOrderRight(headers,userId).compose(this.<BaseEntity<Integer>>setThread()).subscribe(new BaseObserver<Integer>() {
            @Override
            protected void onSuccees(BaseEntity<Integer> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        mTransportDetailsActivity.isCarry(t.getData());
//                        mTransportDetailsActivity.getCarryOrderRightSuccess(t.getMsg());
                    }else{
                        mTransportDetailsActivity.getCarryOrderRightFailed(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mTransportDetailsActivity.getCarryOrderRightFailed(e.getMessage());
            }
        });
    }


}
