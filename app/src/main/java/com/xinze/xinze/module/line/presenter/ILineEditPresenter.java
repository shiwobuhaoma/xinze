package com.xinze.xinze.module.line.presenter;

import com.xinze.xinze.module.line.view.ILineEditView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ILineEditPresenter extends BasePresenter<ILineEditView> {
    void addRegularRoute(String fromAreaId, String toAreaId);

    void editRegularRoute(String id, String fromAreaId, String toAreaId);

    void getAreaList(String extId);
}
