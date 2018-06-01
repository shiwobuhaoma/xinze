package com.xinze.xinze.module.main.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.fragment.MyFragment;
import com.xinze.xinze.module.main.view.IMyView;
import com.xinze.xinze.mvpbase.BaseBean;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.utils.ACache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxf
 * 退出逻辑
 */
public class MyPresenterImp extends BasePresenterImpl<IMyView> implements IMyPresenter {
    private MyFragment iMyView;

    public MyPresenterImp(IMyView iMyView, Context mContext) {
        super(iMyView, mContext);
        this.iMyView = (MyFragment) iMyView;
    }

    @Override
    public void loginOut() {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid",App.mUser.getSessionid());
        headers.put("userid",App.mUser.getId());
        RetrofitFactory.getInstence().Api().loginOut(headers).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(mContext) {

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        App.mUser.setLogin(false);
                        App.mUser.setSessionid("");
                        App.mUser.setId("");
                        ACache.get(mContext).remove("user");
                        iMyView.loginOutSuccess();
                    }else{
                        iMyView.shotToast(t.getMsg());
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
