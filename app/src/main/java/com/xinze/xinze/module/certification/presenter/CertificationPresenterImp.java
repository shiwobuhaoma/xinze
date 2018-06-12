package com.xinze.xinze.module.certification.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.certification.modle.CertificationRespones;
import com.xinze.xinze.module.certification.view.CertificationActivity2;
import com.xinze.xinze.module.certification.view.ICertificationView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.entity.Province;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class CertificationPresenterImp extends BasePresenterImpl<ICertificationView> implements ICertificationPresenter {
    private   CertificationActivity2 mCertificationActivity;
    public CertificationPresenterImp(ICertificationView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mCertificationActivity = (CertificationActivity2)mPresenterView;

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
                                mCertificationActivity.setData(data);
                                mCertificationActivity.uploadImagesSuccess(t.getMsg());
                            }else{
                                  mCertificationActivity.uploadImagesSuccess(t.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void onFailure(String msg) throws Exception {
                        mCertificationActivity.uploadImagesFailed(msg);
                    }
                });
    }

    @Override
    public void certifitcation(String name, String idCard, String areaId, String detailAddress, String idCardImg,String drivingImg) {

        RetrofitFactory.getInstence().Api().driverCertification(headers,name,idCard,areaId,detailAddress,idCardImg,drivingImg)
                .compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(mContext){

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
//                        mCertificationActivity.certificationSuccess(t.getMsg());
                    }else{
                        mCertificationActivity.certificationFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mCertificationActivity.certificationFailed(msg);
            }
        });
    }


    @Override
    public void getAreaList(String extId) {
        RetrofitFactory.getInstence().Api().getAreaList(headers,extId)
                .compose(this.<BaseEntity<List<Province>>>setThread()).subscribe(new BaseObserver<List<Province>>(){

            @Override
            protected void onSuccees(BaseEntity<List<Province>> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        ArrayList<Province> data = (ArrayList<Province>) t.getData();
                        if (data != null){
                            mCertificationActivity.setAreaList(data);
                        }
                        mCertificationActivity.getAreaListSuccess(t.getMsg());
                    }else {
                        mCertificationActivity.getAreaListFailed(t.getMsg());
                    }
                }

            }

            @Override
            protected void onFailure(String msg) throws Exception {

            }
        });
    }
}
