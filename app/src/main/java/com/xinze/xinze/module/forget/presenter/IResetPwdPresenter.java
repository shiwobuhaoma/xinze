package com.xinze.xinze.module.forget.presenter;

/**
 * Created by feibai on 2018/5/14.
 * desc:
 */

public interface IResetPwdPresenter {
    /**
     *  重置密码
     *  @author feibai
     *  @time 2018/5/14  10:52
     *  @desc
     *  @param phone 手机号
     *  @param code 验证码
     *  @param pwd  密码
     *  @return
     */
    void resetPwd(String phone,String code,String pwd);
}
