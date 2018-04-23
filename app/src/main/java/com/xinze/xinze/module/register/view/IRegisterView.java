package com.xinze.xinze.module.register.view;

import com.xinze.xinze.mvpbase.BaseView;

/**
 * @author lxf
 * 注册业务的提示信息方法
 * Created by Administrator on 2018/1/4.
 */

public interface IRegisterView extends BaseView {
    /**
     * 注册成功
     */
    void registerSuccess();
    /**
     * 注册失败
     */
    void registerFailed();
}
