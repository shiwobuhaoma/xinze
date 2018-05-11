package com.xinze.xinze.module.sysmsg.presenter;

import com.xinze.xinze.module.sysmsg.view.ISystemMsgView;
import com.xinze.xinze.module.sysmsg.adapter.SystemMessageAdapter;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ISystemMsgPresenter extends BasePresenter<ISystemMsgView>{
    void getSystemMsgList(int pageNo, int pageSize);
    void markReaded(String msgId, SystemMessageAdapter.ViewHolder holder);
}
