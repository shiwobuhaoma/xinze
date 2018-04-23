package com.xinze.xinze.module.register.presenter;


import com.xinze.xinze.mvpbase.BasePresenter;
import com.xinze.xinze.module.register.view.IRegisterView;

/**
 *@author lxf
 * 注册接口
 * Created by Administrator on 2018/1/4.
 */

public interface IRegisterPresenter extends BasePresenter<IRegisterView> {
    /**
     * 注册接口
     * @param phoneNumber 手机号
     * @param verificationCode 验证码
     * @param pwd 密码
     */
    void register(String phoneNumber, String verificationCode, String pwd);
}
