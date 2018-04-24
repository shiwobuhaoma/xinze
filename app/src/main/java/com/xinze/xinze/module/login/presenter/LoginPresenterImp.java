package com.xinze.xinze.module.login.presenter;


import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.login.modle.LoginResponse;
import com.xinze.xinze.module.login.view.ILoginView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

/**
 * @author lxf
 * 登陆主持人的实现类
 * Created by Administrator on 2018/1/4.
 */

public class LoginPresenterImp extends BasePresenterImpl<ILoginView> implements ILoginPresenter {
    private ILoginView iLoginView;

    public LoginPresenterImp(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(String name, String pwd,String userType) {
        RetrofitFactory.getInstence().Api().login(
                name,
                pwd,
                userType)
                .compose(this.<BaseEntity<LoginResponse>>setThread())
                .subscribe(new BaseObserver<LoginResponse>() {
                    @Override
                    protected void onSuccees(BaseEntity<LoginResponse> t)  {
                        if (t != null){
                            LoginResponse data = t.getData();
                            if (data != null){
                                String sessionId = data.getSessionid();
                                App.mUser.setSessionid(sessionId);
                                App.mUser.setLogin(true);
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
