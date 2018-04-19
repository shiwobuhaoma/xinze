package com.xinze.xinze.register.presenter;


import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.register.view.IRegisterView;

/**
 * @author lxf
 *         注册主持实现类
 *         Created by Administrator on 2018/1/4.
 */

public class RegisterPresenterImp extends BasePresenterImpl<IRegisterView> implements IRegisterPresenter {
    private IRegisterView iRegisterView;

    public RegisterPresenterImp(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }


    @Override
    public void register(String phoneNumber, String verificationCode, String pwd) {
        if ("17600876300".equals(phoneNumber) && "1234".equals(verificationCode) && "123456".equals(pwd)) {
            iRegisterView.registerSuccess();
        } else {
            iRegisterView.registerFailed();
        }
    }
}
