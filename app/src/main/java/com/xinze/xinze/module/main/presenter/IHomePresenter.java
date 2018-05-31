package com.xinze.xinze.module.main.presenter;

import com.xinze.xinze.module.main.view.IHomeView;
import com.xinze.xinze.mvpbase.BasePresenter;

/**
 * @author lxf
 */
public interface IHomePresenter extends BasePresenter<IHomeView> {
    /**
     * 获取轮播图接口
     * @param type 类型
     */
    void getBanner(String type);

    /**
     * 获取首页右上角未读消息数量（司机）
     * @param id 用户id
     */
    void getUnReadNotifyNum(String id);

    /**
     * 获取定向货单未读数量
     * @param id 用户id
     */
    void getFixBillNum(String id);


    void getCustomerPhone();
}
