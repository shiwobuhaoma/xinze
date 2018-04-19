package com.xinze.xinze.login.presenter;


import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.login.modle.LoginResponse;
import com.xinze.xinze.login.view.ILoginView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    public void login(String name, String pwd) {
        RetrofitFactory.getInstence().API().login(
                "thinkgem",
                "admin",
                true,
                true)
                .compose(this.<BaseEntity<LoginResponse>>setThread())
                .subscribe(new BaseObserver<LoginResponse>() {
                    @Override
                    protected void onSuccees(BaseEntity<LoginResponse> t) throws Exception {
                        if (t != null){
                            LoginResponse data = t.getData();
                            if (data != null){
                                String sessionId = data.getSessionid();
                                App.mUser.setSessionId(sessionId);
                                iLoginView.shotToast(t.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        iLoginView.shotToast(e.getMessage());
                    }
                });

        if (name.equals("1009281661@qq.com") && pwd.equals("123456")) {
            iLoginView.loginSuccess();
        } else {
            iLoginView.loginFailed();
        }
    }


}
