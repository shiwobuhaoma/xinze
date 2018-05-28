package com.xinze.xinze.module.send.presenter;

import com.xinze.xinze.module.send.view.IBillView;
import com.xinze.xinze.mvpbase.BasePresenter;

/**
 * @author lxf
 */
public interface IBillPresenter extends BasePresenter<IBillView> {
    void getBillList(int wlBilltype,int pageNum,int pageSize,String remarks);
    void searchRouteList(String fromAreaId,String toAreaId,int pageNo,int pageSize);
}
