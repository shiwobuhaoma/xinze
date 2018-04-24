package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.view.IMyView;
import com.xinze.xinze.mvpbase.BasePresenterImpl;

/**
 * @author lxf
 * 退出逻辑
 */
public class MyPresenterImp extends BasePresenterImpl<IMyView> implements IMyPresenter {
    private final int SUCCESS_CODE = 200;
    private IMyView iMyView;

    public MyPresenterImp(IMyView iMyView) {
        this.iMyView = iMyView;
    }
    @Override
    public void loginOut() {
         RetrofitFactory.getInstence().Api().loginOut().compose(this.setThread()).subscribe(new BaseObserver() {
             @Override
             protected void onSuccees(BaseEntity t) throws Exception {
                 if (t != null){
                     if (t.getStatus() == SUCCESS_CODE){
                         App.mUser.setLogin(false);
                         App.mUser.setSessionid("");
                         App.mUser.setId("");
                         iMyView.loginOutSuccess();
                     }
                 }
             }

             @Override
             protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                 iMyView.loginOutFailed();
             }
         });

    }
}
