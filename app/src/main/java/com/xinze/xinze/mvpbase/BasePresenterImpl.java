package com.xinze.xinze.mvpbase;

import android.content.Context;
import android.os.Bundle;

import com.xinze.xinze.http.config.HeaderConfig;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>Description:
 *
 * @author lxf
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {


    protected HashMap<String, String> headers = HeaderConfig.getHeaders();
    protected T mPresenterView ;
    protected Context mContext;

    public BasePresenterImpl(T mPresenterView, Context mContext) {
        attachView(mPresenterView);
        attachActivity(mContext);
    }

    @Override
    public void attachView(T t) {
        this.mPresenterView = t ;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null ;
    }

    @Override
    public void attachActivity(Context context) {
        this.mContext = context;
    }

    @Override
    public void detachActivity() {
        this.mContext = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        detachView();
        detachActivity();
    }
    public <T> ObservableTransformer<T,T> setThread(){
        return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
