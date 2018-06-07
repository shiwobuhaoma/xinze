package com.xinze.xinze.module.change.presenter;

import com.xinze.xinze.module.change.view.IChangePassWordView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IChangePassWordPresenter extends BasePresenter<IChangePassWordView> {
    void changePassWord(String oldPassWord,String newPassWord);
}
