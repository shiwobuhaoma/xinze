package com.xinze.xinze.http.interceptor;


import android.support.annotation.NonNull;

import com.xinze.xinze.http.DownloadResponseBody;
import com.xinze.xinze.http.listener.DownloadListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor {

    private DownloadListener downloadListener;

    public DownloadInterceptor(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        request.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip,deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .build();
        Response response = chain.proceed(request);
        return response.newBuilder().body(
                new DownloadResponseBody(response.body(), downloadListener))
                .build();
    }
}
