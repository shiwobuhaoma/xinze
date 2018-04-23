package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.mvpbase.BasePresenter;

/**
 * @author lxf
 */
public interface IHomePresenter extends BasePresenter<IHomeView> {
    void getBanner(String type);

}
