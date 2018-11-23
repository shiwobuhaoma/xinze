package com.xinze.xinze.module.main.presenter;

import android.content.Context;

import com.xinze.xinze.App;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.main.fragment.MyFragment;
import com.xinze.xinze.module.main.modle.Count;
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


    public MyPresenterImp(IMyView iMyView, Context mContext) {
        super(iMyView, mContext);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loginOut() {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().loginOut(headers).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(mContext) {

            @Override
            protected void onSuccess(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        App.mUser.setLogin(false);
                        App.mUser.setSessionid("");
                        App.mUser.setId("");
                        ACache.get(mContext).remove("user");
                        mPresenterView.loginOutSuccess();
                    }else{
                        mPresenterView.shotToast(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mPresenterView.loginOutFailed();
            }
        });

    }

    @Override
    public void getCount(String system) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getCount(headers,system)
                .compose(this.<BaseEntity<Count>>setThread()).subscribe(new BaseObserver<Count>(){

            @Override
            protected void onSuccess(BaseEntity<Count> t) throws Exception {
                if (t != null){
                    if (t.isSuccess()){
                        Count data = t.getData();
                        String driverCount = data.getDriverCount2Driver();
                        String truckCount = data.getTruckCount();
                        String systemMsgCount = data.getSystemMsgCount();
                        mPresenterView.refresh(driverCount,truckCount,systemMsgCount);
                        mPresenterView.getCountSuccess(t.getMsg());
                    }else{
                        mPresenterView.getCountFailed(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(String msg) throws Exception {
                mPresenterView.getCountFailed(msg);
            }
        });
    }


}
