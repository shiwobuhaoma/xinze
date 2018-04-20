package com.xinze.xinze.http;


import com.xinze.xinze.http.config.HttpConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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
    private static ApiServer mApiServer;

    private RetrofitFactory() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                //token失效拦截器
//                .addInterceptor(InterceptorUtil.tokenInterceptor())
                //添加日志拦截器
                .addInterceptor(InterceptorUtil.logInterceptor())
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

    public ApiServer Api() {
        return mApiServer;
    }
}
