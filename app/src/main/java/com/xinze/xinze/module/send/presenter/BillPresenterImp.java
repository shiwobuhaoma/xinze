package com.xinze.xinze.module.send.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.send.fragment.AbstractBillFragment;
import com.xinze.xinze.module.send.fragment.DirectionalBillFragment;
import com.xinze.xinze.module.send.view.IBillView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillPresenterImp extends BasePresenterImpl<IBillView> implements IBillPresenter {

    private AbstractBillFragment mBillFragment;
    public BillPresenterImp(IBillView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mBillFragment = (AbstractBillFragment) mPresenterView;
    }

    @Override
    public void getBillList(int wlBilltype, int pageNum, int pageSize,String remarks) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid",App.mUser.getId());
        RetrofitFactory.getInstence().Api().getBillList(headers,wlBilltype,pageNum,pageSize,remarks).compose(this.<BaseEntity<List<OrderItem>>>setThread()).subscribe(new BaseObserver<List<OrderItem>>(){

            @Override
            protected void onSuccees(BaseEntity<List<OrderItem>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<OrderItem> data = t.getData();
                        if (data != null){
                            mBillFragment.setData(data);
                            mBillFragment.getBillsSuccess(t.getMsg());
                        }else{
                            mBillFragment.getBillsSuccess(t.getMsg());
                            mBillFragment.shotToast("没有更多了");
                        }
                    }else{
                        mBillFragment.getBillsFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mBillFragment.getBillsFailed(e.getMessage());
            }
        } );
    }
}
