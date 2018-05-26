package com.xinze.xinze.module.line.presenter;

import com.xinze.xinze.module.line.view.ILineListView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ILineListPresenter extends BasePresenter<ILineListView> {
    void getRegularRouteList();
    void delRegularRoute(String id);
}
