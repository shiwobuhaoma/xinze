package com.xinze.xinze.http.listener;

import java.io.File;

public interface DownloadListener {
    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload(File file);

    void onFail(Throwable ex);

}
