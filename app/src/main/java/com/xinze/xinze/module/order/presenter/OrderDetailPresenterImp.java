package com.xinze.xinze.module.order.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.certification.modle.CertificationRespones;
import com.xinze.xinze.module.order.view.OrderDetailActivity;
import com.xinze.xinze.module.order.modle.OrderDetail;
import com.xinze.xinze.module.order.modle.UpdateOrderState;
import com.xinze.xinze.module.order.view.IOrderDetailView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 订单详情中介
 * @author lxf
 *
 */
public class OrderDetailPresenterImp extends BasePresenterImpl<IOrderDetailView> implements IOrderDetailPresenter {
    private OrderDetailActivity fga;
    public OrderDetailPresenterImp(IOrderDetailView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        fga = (OrderDetailActivity)mPresenterView;
    }

    @Override
    public void getOrderDetail(String orderId) {
        RetrofitFactory.getInstence().Api().getBillOrderDetail(headers,orderId)
                .compose(this.<BaseEntity<OrderDetail>>setThread()).subscribe(new BaseObserver<OrderDetail>(mContext) {
            @Override
            protected void onSuccees(BaseEntity<OrderDetail> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        fga.setData(t.getData());
                    }else{
                        fga.getOrderDetailFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                fga.getOrderDetailFailed(msg);
            }
        });
    }

    @Override
    public void revoke(String id, List<String> files,String remarks,final String orderStatus) {
        UpdateOrderState uos = new UpdateOrderState();
        uos.setId(id);
        if(files != null){
            uos.setFiles(files);
        }
        if (!TextUtils.isEmpty(remarks)){
            uos.setRemarks(remarks);
        }
        uos.setOrderStatus(orderStatus);
        Gson gson = new Gson();
        String json = gson.toJson(uos);
        RequestBody body= RequestBody.create(MediaType.parse("application/json"),json);
        RetrofitFactory.getInstence().Api().changeBillOrderStatus(headers,body)
                .compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(mContext) {
            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        if (fga.GOODS_REFUSE.equals(orderStatus)){
                            fga.revokeSuccess("撤销订单成功",fga.GOODS_REFUSE);
                        }else if(fga.DELIVER_GOODS.equals(orderStatus)){
                            fga.revokeSuccess("确认取货成功",fga.DELIVER_GOODS);
                        }else if(fga.GOODS_ARRIVE.equals(orderStatus)){
                            fga.revokeSuccess("确认送货成功",fga.GOODS_ARRIVE);
                        }else if(fga.GOODS_SIGNED_IN.equals(orderStatus)){
                            fga.revokeSuccess("确认送达成功",fga.GOODS_SIGNED_IN);
                        }

                    }else{
                        fga.revokeFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                fga.revokeFailed(msg);
            }
        });
    }

    @Override
    public void uploadImages(List<MultipartBody.Part> partList) {
        RetrofitFactory.getInstence().Api().imagesUpload(headers,partList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<CertificationRespones>>(mContext){

                    @Override
                    protected void onSuccees(BaseEntity<List<CertificationRespones>> t) throws Exception {
                        if (t != null){
                            if (t.isSuccess()){
                                List<CertificationRespones> data = t.getData();
                                fga.setUploadImagesData(data);
                                fga.uploadImagesSuccess(t.getMsg());
                            }else{
                                fga.uploadImagesSuccess(t.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void onFailure(String msg) throws Exception {
                        fga.uploadImagesFailed(msg);
                    }
                });
    }
}
