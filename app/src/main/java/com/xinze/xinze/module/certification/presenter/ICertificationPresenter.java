package com.xinze.xinze.module.certification.presenter;

import com.xinze.xinze.module.certification.view.ICertificationView;
import com.xinze.xinze.mvpbase.BasePresenter;

import java.util.List;

import okhttp3.MultipartBody;

public interface ICertificationPresenter extends BasePresenter<ICertificationView> {
    void uploadImages(List<MultipartBody.Part> partList);

    void certifitcation(String name, String idCard, String areaId, String detailAddress, String idCardImg, String drivingImg);

    void getAreaList(String extId);
}
