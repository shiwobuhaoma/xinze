package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.module.main.view.IOrderView;
import com.xinze.xinze.mvpbase.BasePresenter;

public interface IOrderPresenter extends BasePresenter<IOrderView>{
    void getOderList(int pageNo,int pageSize);
}
