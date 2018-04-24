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
}
