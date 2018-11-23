package com.xinze.xinze.module.select.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.select.module.BillOrder;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.module.select.view.ISelectCarView;
import com.xinze.xinze.module.select.view.SelectCarActivity;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SelectCarPresenterImp extends BasePresenterImpl<ISelectCarView> implements ISelectCarPresenter {
    private SelectCarActivity mSelectCarActivity;

    public SelectCarPresenterImp(ISelectCarView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mSelectCarActivity = (SelectCarActivity)mPresenterView;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getCarryTruckList(String id) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getCarryTruckList(headers,id).compose(this.<BaseEntity<List<Car>>>setThread()).subscribe(new BaseObserver<List<Car>>() {
            @Override
            protected void onSuccess(BaseEntity<List<Car>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        List<Car> data = t.getData();
                        mSelectCarActivity.setData(data);
                        mSelectCarActivity.getCarryTruckListSuccess(t.getMsg());
                    }else{
                        mSelectCarActivity.getCarryTruckListFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mSelectCarActivity.getCarryTruckListFailed(msg);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getProtocolByType(String protocolType) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getProtocolByType(headers,protocolType).compose(this.<BaseEntity<Protocol>>setThread()).subscribe(new BaseObserver<Protocol>() {
            @Override
            protected void onSuccess(BaseEntity<Protocol> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        Protocol data = t.getData();
                        mSelectCarActivity.setProtocolData(data);
                    }else{
                        mSelectCarActivity.getProtocolByTypeFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mSelectCarActivity.getProtocolByTypeFailed(msg);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void createBillOrder(String wayBillId, List<Car> list) {
        BillOrder billOrder = new BillOrder();
        billOrder.setWayBillid(wayBillId);
        List<BillOrder.WaybillOrderEntitiesBean> l = new ArrayList<>();
        for (Car car : list){
            String driverId = car.getDriver_id();
            if (TextUtils.isEmpty(driverId)){
                driverId = App.mUser.getId();
            }
            String truckOwnerid = car.getTruck_ownerid();
            String id = car.getId();
            BillOrder.WaybillOrderEntitiesBean bean = new BillOrder.WaybillOrderEntitiesBean();
            bean.setDriverId(driverId);
            bean.setTruckOwnerid(truckOwnerid);
            bean.setTruckId(id);
            l.add(bean);
        }
        billOrder.setWaybillOrderEntities(l);
        Gson gson = new Gson();
        String json = gson.toJson(billOrder);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json);
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().createBillOrder(headers,requestBody).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver() {
            @Override
            protected void onSuccess(BaseEntity t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        mSelectCarActivity.createBillOrderSuccess(t.getMsg());
                    }else{
                        mSelectCarActivity.createBillOrderFailed(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mSelectCarActivity.createBillOrderFailed(msg);
            }
        });
    }
}
