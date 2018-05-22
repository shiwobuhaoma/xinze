package com.xinze.xinze.module.message.presenter;

import com.xinze.xinze.module.message.view.ISystemMsgView;
import com.xinze.xinze.module.message.adapter.SystemMessageAdapter;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface ISystemMsgPresenter extends BasePresenter<ISystemMsgView>{
    void getSystemMsgList(int pageNo, int pageSize);
    void markReaded(String msgId, SystemMessageAdapter.ViewHolder holder);
}
