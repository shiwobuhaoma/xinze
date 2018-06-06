package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.http.observable.FileDownLoadObserver;
import com.xinze.xinze.module.main.view.IMainView;
import com.xinze.xinze.mvpbase.BasePresenter;

import java.io.File;

public interface IMainPresenter extends BasePresenter<IMainView> {
    void checkUpdate(String appType, String fileType);

    void downloadApk(String downloadUrl, String destDir, String fileName, FileDownLoadObserver<File> fileDownLoadObserver);
}
