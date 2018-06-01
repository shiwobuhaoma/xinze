package com.xinze.xinze.module.add.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.add.modle.AddCarRespones;
import com.xinze.xinze.module.add.view.AddMyCarActivity;
import com.xinze.xinze.module.add.view.IAddMyCarView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;

import okhttp3.MultipartBody;

public class AddMyCarPresenterImp extends BasePresenterImpl<IAddMyCarView> implements IAddMyCarPresenter {

    private AddMyCarActivity addMyCarActivity;

    public AddMyCarPresenterImp(IAddMyCarView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        addMyCarActivity = (AddMyCarActivity) mPresenterView;
    }

    @Override
    public void addTruck(String truckName,String truckCode,String weight,String vehicleLicenseImg) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().addTruck(headers,truckName,truckCode,weight,vehicleLicenseImg);
    }

    @Override
    public void imageUpload(MultipartBody.Part img) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().imageUpload(headers,img).compose(this.<BaseEntity<AddCarRespones>>setThread()).subscribe(new BaseObserver<AddCarRespones>(mContext) {

            @Override
            protected void onSuccees(BaseEntity<AddCarRespones> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        addMyCarActivity.imageUploadSuccess(t.getData().getUrl());
                    }else{
                        addMyCarActivity.imageUploadFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                addMyCarActivity.imageUploadFailed(e.getMessage());
            }
        });
    }
}
