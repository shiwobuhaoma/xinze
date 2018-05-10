package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.module.main.view.ISystemMsgView;
import com.xinze.xinze.module.sysmsg.adapter.SystemMessageAdapter;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ISystemMsgPresenter extends BasePresenter<ISystemMsgView>{
    void getOderList(int pageNo, int pageSize);
    void markReaded(String msgId, SystemMessageAdapter.ViewHolder holder);
}
