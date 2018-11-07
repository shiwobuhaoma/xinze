package com.xinze.xinze.http;
import android.util.Log;

import com.xinze.xinze.http.listener.ProgressListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ProgressHelper {
//    private static ProgressBean progressBean = new ProgressBean();
//    private static ProgressHandler mProgressHandler;
//
//    public static OkHttpClient.Builder addProgress(OkHttpClient.Builder builder){
//
//        if (builder == null){
//            builder = new OkHttpClient.Builder();
//        }
//
//        final ProgressListener progressListener = new ProgressListener() {
//            @Override
//            public void onProgressChanged(long numBytes, long totalBytes, float percent, float speed) {
//                if (mProgressHandler == null){
//                    return;
//                }
//
//                progressBean.setBytesRead(numBytes);
//                progressBean.setContentLength(totalBytes);
//                mProgressHandler.sendMessage(progressBean);
//            }
//
//            //该方法在子线程中运行
//
//            public void onProgress(long progress, long total, boolean done) {
//                Log.d("progress:",String.format("%d%% done\n",(100 * progress) / total));
//                if (mProgressHandler == null){
//                    return;
//                }
//
//                progressBean.setBytesRead(progress);
//                progressBean.setContentLength(total);
//                progressBean.setDone(done);
//                mProgressHandler.sendMessage(progressBean);
//
//            }
//        };
//
//        //添加拦截器，自定义ResponseBody，添加下载进度
//        builder.networkInterceptors().add(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                okhttp3.Response originalResponse = chain.proceed(chain.request());
//                return originalResponse.newBuilder().body(
//                        new ProgressResponseBody(originalResponse.body(), progressListener))
//                        .build();
//
//            }
//        });
//
//        return builder;
//    }
//
//    public static void setProgressHandler(ProgressHandler progressHandler){
//        mProgressHandler = progressHandler;
//    }


    public static RequestBody withProgress(RequestBody requestBody, ProgressListener progressListener) {
        if (requestBody == null) {
            throw new IllegalArgumentException("requestBody == null");
        }
        if (progressListener == null) {
            throw new IllegalArgumentException("progressListener == null");
        }
        return new ProgressRequestBody(requestBody, progressListener);
    }


    /**
     * 包装请求体为带进度的响应体
     *
     * @param responseBody     待包装的响应体
     * @param progressListener 进度回调监听
     * @return 带进度的响应体，使用此响应体进行响应数据的读取
     */
    public static ResponseBody withProgress(ResponseBody responseBody, ProgressListener progressListener) {
        if (responseBody == null) {
            throw new IllegalArgumentException("responseBody == null");
        }
        if (progressListener == null) {
            throw new IllegalArgumentException("progressListener == null");
        }
        return new ProgressResponseBody(responseBody, progressListener);
    }
}
