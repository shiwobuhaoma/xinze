package com.xinze.xinze.module.main.view;

import com.xinze.xinze.mvpbase.BaseView;

/**
 * @author lxf
 * 注销视图
 */
public interface IMyView extends BaseView {
   /**
    * 注销成功
    */
   void loginOutSuccess();
   /**
    * 注销失败
    */
   void loginOutFailed();
   /**
    * 查询数量接口成功
    */
   void getCountSuccess(String system);
    /**
     * 查询数量接口失败
     */
   void getCountFailed(String system);

    void refresh(String driverCount, String truckCount, String systemMsgCount);
}
