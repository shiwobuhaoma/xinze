package com.xinze.xinze.module.regular.view;

import com.xinze.xinze.mvpbase.BaseView;

public interface IRegularRouteView extends BaseView {
   void getRegularRouteListSuccess(String msg);
   void getRegularRouteListFailed(String msg);
   void searchRouteListSuccess(String msg);
   void searchRouteListFailed(String msg);
}
