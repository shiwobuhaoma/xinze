package com.xinze.xinze.http;


import com.xinze.xinze.App;
import com.xinze.xinze.http.config.HttpConfig;
import com.xinze.xinze.http.interceptor.DownloadInterceptor;
import com.xinze.xinze.http.listener.DownloadListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lxf
 * @date 2018/4/11
 */

public class RetrofitFactory {

    private static RetrofitFactory mRetrofitFactory;
    private static RetrofitFactory mRetrofitDownloadFactory;
    private static ApiServer mApiServer;
    private static DownloadService mDownloadService;

    private RetrofitFactory() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                //token失效拦截器
//                .addInterceptor(InterceptorUtil.tokenInterceptor())
                //添加日志拦截器
                .addInterceptor(InterceptorUtil.logInterceptor())
                //添加自定义请求头
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("sessionid", App.mUser.getSessionid())
//                                .addHeader("userid",App.mUser.getId())
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return null;
//                    }
//                })
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                //添加gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                //添加rxjava转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        mApiServer = mRetrofit.create(ApiServer.class);

    }
    private RetrofitFactory(DownloadListener listener) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                //添加自定义请求头
                .addInterceptor(new DownloadInterceptor(listener))
                .retryOnConnectionFailure(true)
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                //添加rxjava转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        mDownloadService = mRetrofit.create(DownloadService.class);

    }
    public static RetrofitFactory getInstence() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null) {
                    mRetrofitFactory = new RetrofitFactory();
                }
            }

        }
        return mRetrofitFactory;
    }
    public static RetrofitFactory getInstence(DownloadListener listener) {
        if (mRetrofitDownloadFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitDownloadFactory == null) {
                    mRetrofitDownloadFactory = new RetrofitFactory(listener);
                }
            }

        }
        return mRetrofitDownloadFactory;
    }
    public ApiServer Api() {
        return mApiServer;
    }

    public DownloadService downService() {
        return mDownloadService;
    }

}
