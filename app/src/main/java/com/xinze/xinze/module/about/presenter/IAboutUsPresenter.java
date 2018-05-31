package com.xinze.xinze.module.about.presenter;

import com.xinze.xinze.module.about.view.IAboutUsView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IAboutUsPresenter extends BasePresenter<IAboutUsView> {
    void getAboutUs(String aboutAsType);
    void getProtocolByType(String protocolType);
}
