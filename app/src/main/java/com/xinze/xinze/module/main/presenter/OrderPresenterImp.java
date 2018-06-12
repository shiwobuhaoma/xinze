package com.xinze.xinze.module.main.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
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

    private int pageNo;
    private List<OrderItem> mData;

    public OrderPresenterImp(IOrderView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
    }

    @Override
    public void getOderList(int pageNo, int pageSize,String remark) {
        this.pageNo = pageNo;
        RetrofitFactory.getInstence().Api().getBillOrderList(headers,pageNo,pageSize,remark).compose(this.<BaseEntity<List<OrderItem>>>setThread()).subscribe(new BaseObserver<List<OrderItem>>() {
            @Override
            protected void onSuccees(BaseEntity<List<OrderItem>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<OrderItem> data = t.getData();
                        setData(data);
                        mPresenterView.getOrderListSuccess();
                    }else{
                        mPresenterView.getOrderListFailed();
                        mPresenterView.shotToast(t.getMsg());

                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mPresenterView.getOrderListFailed();
            }
        });
    }

    public void setData(final List<OrderItem> data) {
        if (pageNo == 1) {
            if (data != null && data.size() > 0) {
                this.mData = data;
                mPresenterView.upData(data);
            }else{
                mPresenterView.clearData();
            }

        } else {
            if (data != null && data.size() > 0) {
                this.mData.addAll(data);
                mPresenterView.upData(mData);
            }else{
                mPresenterView.shotToast(AppConfig.LOAD_INFO_FINISH);
            }

        }

    }
}
