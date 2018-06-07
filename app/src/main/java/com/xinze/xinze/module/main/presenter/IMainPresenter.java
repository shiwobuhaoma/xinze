package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.http.listener.DownloadListener;
import com.xinze.xinze.module.main.view.IMainView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IMainPresenter extends BasePresenter<IMainView> {
    void checkUpdate(String appType, String fileType);

    void downloadApk(String downloadUrl, String destDir, String fileName, DownloadListener listener);
}
