package com.xinze.xinze.module.login.presenter;


import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.login.modle.LoginResponse;
import com.xinze.xinze.module.login.modle.UserEntity;
import com.xinze.xinze.module.login.view.ILoginView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

/**
 * @author lxf
 * 登陆主持人的实现类
 * Created by Administrator on 2018/1/4.
 */

public class LoginPresenterImp extends BasePresenterImpl<ILoginView> implements ILoginPresenter {
    private ILoginView iLoginView;

    public LoginPresenterImp(ILoginView iLoginView, Context mContext) {
        super(iLoginView,mContext);
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(String name, String pwd,String userType) {
        RetrofitFactory.getInstence().Api().login(
                name,
                pwd,
                userType)
                .compose(this.<BaseEntity<LoginResponse>>setThread())
                .subscribe(new BaseObserver<LoginResponse>(mContext) {
                    @Override
                    protected void onSuccees(BaseEntity<LoginResponse> t)  {
                        if (t != null){
                            LoginResponse data = t.getData();
                            if (data != null){
                                String sessionId = data.getSessionid();
                                App.mUser.setSessionid(sessionId);
                                App.mUser.setId(data.getId());
                                App.mUser.setLogin(true);
                                App.mUser.setVertifyFlag(data.getVertifyFlag());
                                App.mUser.setVertifyDescription(data.getVertifyDescription());
                                App.mUser.setLogin_name(data.getLoginName());
                                iLoginView.shotToast(t.getMsg());
                                iLoginView.loginSuccess();
                            }else {
                                iLoginView.shotToast(t.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError){
                        iLoginView.shotToast(e.getMessage());

                    }
                });
    }


}
