package com.xinze.xinze.module.forget.presenter;

/**
 * Created by feibai on 2018/5/13.
 * desc:
 */

public interface IForgetPwdPresenter {

    /**
     * 获取验证码
     *  @author feibai
     *  @time 2018/5/13  17:24
     *  @desc 获取验证码
     *  @param phoneNum 手机号
     * @param type 短信类型
     */
    void getVerfifyCode(String phoneNum,String type);
    /**
     *  校验验证码
     *  @author feibai
     *  @time 2018/5/13  21:28
     *  @desc 校验验证码
     *  @param phone 手机号
     *  @param code 验证码
     */
    void checkVerfifyCode(String phone,String code);
}
