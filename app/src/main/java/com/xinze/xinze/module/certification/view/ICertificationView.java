package com.xinze.xinze.module.certification.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface ICertificationView extends BaseView {

    void uploadImagesSuccess(String msg);

    void uploadImagesFailed(String msg);

    void certificationSuccess(String msg);

    void certificationFailed(String msg);


}
