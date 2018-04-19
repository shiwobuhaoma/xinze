package com.xinze.xinze;

import android.app.Application;

import com.vondear.rxtools.RxTool;
import com.xinze.xinze.login.modle.User;

/**
 * @author lxf
 * Created by Administrator on 2017/12/6.
 */

public class App extends Application {
    private static App context ;
    public static User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        RxTool.init(this);
    }

    public static App getContext(){
        return context;
    }
}
