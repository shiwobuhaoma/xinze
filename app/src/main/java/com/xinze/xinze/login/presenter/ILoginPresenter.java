package com.xinze.xinze.login.presenter;


import com.xinze.xinze.login.view.ILoginView;
import com.xinze.xinze.mvpbase.BasePresenter;

/**
 *@author lxf
 * Created by Administrator on 2018/1/4.
 */

public interface ILoginPresenter extends BasePresenter<ILoginView> {
    void login(String name, String pwd,String userType);
}
