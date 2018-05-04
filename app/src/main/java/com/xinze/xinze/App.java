package com.xinze.xinze;

import android.app.Application;

import com.vondear.rxtools.RxTool;
import com.xinze.xinze.module.login.modle.UserEntity;
import com.xinze.xinze.utils.ACache;

/**
 * @author lxf
 * Created by Administrator on 2017/12/6.
 */

public class App extends Application {
    private static App context ;
    public static UserEntity mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        UserEntity user = (UserEntity)ACache.get(this).getAsObject("user");
        if (user != null){
            mUser = user;
        }else{
            mUser = new UserEntity();
        }
        RxTool.init(this);
    }

    public static App getContext(){
        return context;
    }
}
