package com.xinze.xinze.module.about.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.about.modle.AboutUs;
import com.xinze.xinze.module.about.view.AboutUsActivity;
import com.xinze.xinze.module.about.view.IAboutUsView;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import java.util.HashMap;

public class AboutUsPresenterImp extends BasePresenterImpl<IAboutUsView> implements IAboutUsPresenter{
    private AboutUsActivity mAboutUsActivity;
    public AboutUsPresenterImp(IAboutUsView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mAboutUsActivity = (AboutUsActivity) mPresenterView;
    }

    @Override
    public void getAboutUs(String aboutAsType) {
        RetrofitFactory.getInstence().Api().aboutUs(aboutAsType)
                .compose(this.<BaseEntity<AboutUs>>setThread()).subscribe(new BaseObserver<AboutUs>(mContext) {
            @Override
            protected void onSuccees(BaseEntity<AboutUs> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        mAboutUsActivity.setData(t.getData());
//                        mAboutUsActivity.getAboutUsSuccess(t.getMsg());
                    }else {
                        mAboutUsActivity.getAboutUsFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mAboutUsActivity.getAboutUsFailed(e.getMessage());
            }
        });
    }

    @Override
    public void getProtocolByType(String protocolType) {
        HashMap<String,String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getProtocolByType(headers,protocolType).compose(this.<BaseEntity<Protocol>>setThread()).subscribe(new BaseObserver<Protocol>() {
            @Override
            protected void onSuccees(BaseEntity<Protocol> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        Protocol data = t.getData();
                        mAboutUsActivity.setProtocolData(data);
                    }else{
                        mAboutUsActivity.getProtocolByTypeFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                mAboutUsActivity.getProtocolByTypeFailed(e.getMessage());
            }
        });
    }


}
