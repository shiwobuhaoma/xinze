package com.xinze.xinze.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadService {
    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);
}
