package com.xinze.xinze.http;

import com.xinze.xinze.http.listener.ProgressCallback;
import com.xinze.xinze.http.listener.ProgressListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by ljd on 3/29/16.
 */
public class ProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private final ProgressCallback progressListener;
    private BufferedSource progressSource;


    ProgressResponseBody(ResponseBody responseBody, ProgressCallback progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }


    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }


    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }


    @Override
    public BufferedSource source() {
        if (progressListener == null) {
            return responseBody.source();
        }
        ProgressInputStream progressInputStream = new ProgressInputStream(responseBody.source().inputStream(), progressListener, contentLength());
        progressSource = Okio.buffer(Okio.source(progressInputStream));
        return progressSource;
    }

    @Override
    public void close() {
        if (progressSource != null) {
            try {
                progressSource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}