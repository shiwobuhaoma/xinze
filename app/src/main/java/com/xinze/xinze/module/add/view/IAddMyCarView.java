package com.xinze.xinze.module.add.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface IAddMyCarView extends BaseView {
    void addTruckSuccess(String msg);

    void addTruckFailed(String msg);

    void imageUploadSuccess(String msg);

    void imageUploadFailed(String msg);
}
