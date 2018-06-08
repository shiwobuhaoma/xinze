package com.xinze.xinze.module.main.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.fragment.OrderFragment;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.main.view.IOrderView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中介
 * @author lxf
 */
public class OrderPresenterImp extends BasePresenterImpl<IOrderView> implements IOrderPresenter {
    private OrderFragment orderFragment;
    private int pageNo;
    private List<OrderItem> data;

    public OrderPresenterImp(IOrderView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        orderFragment = (OrderFragment) mPresenterView;
    }

    @Override
    public void getOderList(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getBillOrderList(headers,pageNo,pageSize).compose(this.<BaseEntity<List<OrderItem>>>setThread()).subscribe(new BaseObserver<List<OrderItem>>() {
            @Override
            protected void onSuccees(BaseEntity<List<OrderItem>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<OrderItem> data = t.getData();
                        setData(data);
                        orderFragment.getOrderListSuccess();
                    }else{
                        orderFragment.getOrderListFailed();
                        orderFragment.shotToast(t.getMsg());

                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                orderFragment.getOrderListFailed();
            }
        });
    }

    public void setData(final List<OrderItem> data) {
        if (pageNo == 1) {
            if (data != null && data.size() > 0) {
                this.data = data;
                orderFragment.upData(data);
            }else{
                orderFragment.clearData();
            }

        } else {
            if (data != null && data.size() > 0) {
                this.data.addAll(data);
                orderFragment.upData(data);
            }else{
                orderFragment.shotToast("没有更多");
            }

        }

    }
}
