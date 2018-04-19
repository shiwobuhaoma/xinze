package com.xinze.xinze.http.observable;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xinze.xinze.http.entity.HttpResponse;
import com.xinze.xinze.http.function.HttpResultFunction;
import com.xinze.xinze.http.function.ServerResultFunction;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 适用Retrofit网络请求Observable(被观察者)
 *
 * @author lxf
 */
public class HttpRxObservable {
    /**
     * 获取被观察者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider自动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity.../RxFragment...
     *
     * @author lxf
     */
    public static Observable getObservable(Observable<HttpResponse> apiObservable, LifecycleProvider lifecycle) {
        //showLog(request);
        Observable observable;
        //随生命周期自动管理.eg:onCreate(start)->onStop(end)
        observable =apiObservable
                .map(new ServerResultFunction())
                //需要在这个位置添加
                .compose(lifecycle.bindToLifecycle())
                .onErrorResumeNext(new HttpResultFunction<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    /**
     * 获取被订阅者
     * 备注:网络请求Observable构建
     * <h1>补充说明</h1>
     * 传入LifecycleProvider<ActivityEvent>手动管理生命周期,避免内存泄漏
     * 备注:需要继承RxActivity,RxAppCompatActivity,RxFragmentActivity
     */
    public static Observable getObservable(Observable<HttpResponse> apiObservable, LifecycleProvider<ActivityEvent> lifecycle, ActivityEvent event) {
        Observable observable;
        observable = apiObservable
                .map(new ServerResultFunction())
                //手动管理移除监听生命周期.eg:ActivityEvent.STOP
                .compose(lifecycle.bindUntilEvent(event))
                .onErrorResumeNext(new HttpResultFunction<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
