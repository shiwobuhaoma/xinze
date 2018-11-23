package com.xinze.xinze.module.change.presenter;

import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.change.view.ChangePassWordActivity;
import com.xinze.xinze.module.change.view.IChangePassWordView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.utils.Base64Util;

import java.util.HashMap;

public class ChangePassWordPresenterImp extends BasePresenterImpl<IChangePassWordView> implements IChangePassWordPresenter {


    private ChangePassWordActivity mActivity;

    public ChangePassWordPresenterImp(IChangePassWordView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        mActivity = (ChangePassWordActivity) mPresenterView;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void changePassWord(String oldPassWord, String newPassWord) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().changePassWord(headers,Base64Util.getBase64(oldPassWord),Base64Util.getBase64(newPassWord))
                .compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(){
            @Override
            protected void onSuccess(BaseEntity t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        mActivity.changePassWordSuccess(t.getMsg());
                    }else{
                        mActivity.changePassWordFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mActivity.changePassWordFailed(msg);
            }
        });
    }
}
