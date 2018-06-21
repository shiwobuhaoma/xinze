package com.xinze.xinze.http.observer;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.widget.ProgressDialog;
import com.xinze.xinze.utils.ACache;
import com.xinze.xinze.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author lxf
 * @date 2018/7/4/11
 */

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    protected Context mContext;


    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        onRequestEnd();
        if (tBaseEntity.isSuccess()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("错误信息", e.getMessage() );
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onNetworkError(e, true);

            } else {
                onNetworkError(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void onNetworkError(Throwable throwable, boolean isNetWorkError) throws Exception {
        if (isNetWorkError){
            onFailure("网络错误");
        }else{
            onFailure(throwable.getMessage());
        }
    }

    @Override
    public void onComplete() {
        onRequestEnd();
    }

    /**
     * 返回成功
     *
     * @param t 基类
     * @throws Exception 异常
     */
    protected abstract void onSuccees(BaseEntity<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t 基类
     * @throws Exception 异常
     */
    private void onCodeError(BaseEntity<T> t) throws Exception {
        //sessionId过期码
        int sessionIDOverdue = -300;
        int loginOutCode = -200;
        if (t.getStatus() == -1){
            onSuccees(t);
        }else if(t.getStatus() == loginOutCode){
            EventBus.getDefault().postSticky(new MessageEvent(AppConfig.UNLOGIN));
            App.mUser.setLogin(false);
            ACache.get(mContext).put("user",App.mUser);
            RxToast.showToast("账号已过期或正在另外一台设备上登录");
         }else if(t.getStatus() == sessionIDOverdue){

        }
    }

    /**
     * 返回失败
     *
     * @param msg 异常信息
     * @throws Exception 异常
     */
    protected abstract void onFailure(String msg) throws Exception;

    private void onRequestStart() {
        if (mContext != null){
            showProgressDialog();
        }

    }

    private void onRequestEnd() {
        closeProgressDialog();
    }

    private void showProgressDialog() {
        ProgressDialog.show(mContext, true, "请稍后");
    }

    private void closeProgressDialog() {
        ProgressDialog.cancle();
    }

}
