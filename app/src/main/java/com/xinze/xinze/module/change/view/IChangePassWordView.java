package com.xinze.xinze.module.change.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface IChangePassWordView extends BaseView {
    void changePassWordSuccess(String msg);
    void changePassWordFailed(String msg);
}
