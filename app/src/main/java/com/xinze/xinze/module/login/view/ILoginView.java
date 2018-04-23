package com.xinze.xinze.module.login.view;


import com.xinze.xinze.mvpbase.BaseView;

/**
 * @author lxf
 * 登陆业务的提示信息方法
 * Created by Administrator on 2018/1/4.
 */

public interface ILoginView extends BaseView {
    /**
     * 登陆成功
     */
    void loginSuccess();
    /**
     * 登陆失败
     */
    void loginFailed();
}
