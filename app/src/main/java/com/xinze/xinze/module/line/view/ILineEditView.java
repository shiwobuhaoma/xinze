package com.xinze.xinze.module.line.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface ILineEditView extends BaseView {
    void addRegularRouteSuccess(String msg);

    void addRegularRouteFailed(String msg);

    void getAreaListSuccess(String msg);

    void getAreaListFailed(String msg);
}
