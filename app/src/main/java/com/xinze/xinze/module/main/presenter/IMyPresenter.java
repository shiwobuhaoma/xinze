package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.module.main.view.IMyView;
import com.xinze.xinze.mvpbase.BasePresenter;

/**
 * 退出逻辑
 *
 * @author lxf
 */
public interface IMyPresenter extends BasePresenter<IMyView> {
    /**
     * 注销接口
     */
    void loginOut();

}
