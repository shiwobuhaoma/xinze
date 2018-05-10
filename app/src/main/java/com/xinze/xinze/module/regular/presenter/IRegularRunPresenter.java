package com.xinze.xinze.module.regular.presenter;

import com.xinze.xinze.module.regular.view.IRegularRouteView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IRegularRunPresenter extends BasePresenter<IRegularRouteView> {
    void getRegularRouteList();
    void searchRouteList(String fromAreaId,String toAreaId,int pageNo,int pageSize);
}
