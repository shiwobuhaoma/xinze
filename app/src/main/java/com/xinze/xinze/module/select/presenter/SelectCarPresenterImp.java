package com.xinze.xinze.module.select.presenter;

import android.content.Context;

import com.google.gson.Gson;
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
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SelectCarPresenterImp extends BasePresenterImpl<ISelectCarView> implements ISelectCarPresenter {
    private SelectCarActivity mSelectCarActivity;
    private final Map headers;

    public SelectCarPresenterImp(ISelectCarView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        headers = HeaderConfig.getHeaders();
        mSelectCarActivity = (SelectCarActivity)mPresenterView;
    }

    @Override
    public void getCarryTruckList(String id) {

        RetrofitFactory.getInstence().Api().getCarryTruckList(headers,id).compose(this.<BaseEntity<List<Car>>>setThread()).subscribe(new BaseObserver<List<Car>>() {
            @Override
            protected void onSuccees(BaseEntity<List<Car>> t) throws Exception {
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
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mSelectCarActivity.getCarryTruckListFailed(e.getMessage());
            }
        });
    }

    @Override
    public void getProtocolByType(String protocolType) {
        RetrofitFactory.getInstence().Api().getProtocolByType(headers,protocolType).compose(this.<BaseEntity<Protocol>>setThread()).subscribe(new BaseObserver<Protocol>() {
            @Override
            protected void onSuccees(BaseEntity<Protocol> t) throws Exception {
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
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mSelectCarActivity.getProtocolByTypeFailed(e.getMessage());
            }
        });
    }

    @Override
    public void createBillOrder(String wayBillId, List<Car> list) {
        BillOrder billOrder = new BillOrder();
        billOrder.setWayBillid(wayBillId);
        List<BillOrder.WaybillOrderEntitiesBean> l = new ArrayList<>();
        for (Car car : list){
            String driverId = car.getDriver_id();
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
        RetrofitFactory.getInstence().Api().createBillOrder(headers,requestBody).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver() {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        mSelectCarActivity.createBillOrderSuccess(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mSelectCarActivity.createBillOrderFailed(e.getMessage());
            }
        });
    }
}
