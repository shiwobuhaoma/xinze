package com.xinze.xinze.module.register.presenter;


import android.content.Context;

import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.register.modle.RegisterResponse;
import com.xinze.xinze.mvpbase.BaseBean;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.module.register.view.IRegisterView;
import com.xinze.xinze.utils.Base64Util;

/**
 * @author lxf
 *         注册主持实现类
 *         Created by Administrator on 2018/1/4.
 */

public class RegisterPresenterImp extends BasePresenterImpl<IRegisterView> implements IRegisterPresenter {
    private IRegisterView iRegisterView;

    public RegisterPresenterImp(IRegisterView iRegisterView, Context mContext) {
        super(iRegisterView,mContext);
        this.iRegisterView = iRegisterView;
    }


    @Override
    public void register(String phoneNumber, String verificationCode, String pwd, String type, String userType) {
        RetrofitFactory.getInstence().Api().register(phoneNumber,
                verificationCode,
                Base64Util.getBase64(pwd),
                type,
                userType
            ).compose(this.<BaseEntity<RegisterResponse>>setThread()).subscribe(new BaseObserver<RegisterResponse>() {
            @Override
            protected void onSuccess(BaseEntity<RegisterResponse> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        RegisterResponse data = t.getData();
                        iRegisterView.shotToast("注册成功");
                    }else {
                        iRegisterView.registerFailed(t.getMsg());
                    }

                }

            }

            @Override
            protected void onFailure(String msg) throws Exception {
                iRegisterView.registerFailed(msg);
            }
        });

    }

    @Override
    public void getVerificationCode(String phoneNumber, String type) {
        RetrofitFactory.getInstence().Api().getVerificationCode(phoneNumber,type).compose(this.<BaseEntity<BaseBean>>setThread()).subscribe(new BaseObserver<BaseBean>() {
            @Override
            protected void onSuccess(BaseEntity<BaseBean> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        iRegisterView.shotToast("获取成功");
                    }else {
                        iRegisterView.shotToast(t.getMsg());
                    }

                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                iRegisterView.getVerificationCodeFailed();
            }
        });
    }


}
