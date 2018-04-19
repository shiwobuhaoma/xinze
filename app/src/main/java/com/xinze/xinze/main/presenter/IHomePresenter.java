package com.xinze.xinze.main.presenter;

import com.xinze.xinze.main.view.IHomeView;
import com.xinze.xinze.mvpbase.BasePresenter;

/**
 * @author lxf
 */
public interface IHomePresenter extends BasePresenter<IHomeView> {
    void getBanner(String type);

}
