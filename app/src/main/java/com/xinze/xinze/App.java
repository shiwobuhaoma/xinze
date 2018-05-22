package com.xinze.xinze;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.vondear.rxtools.RxTool;
import com.xinze.xinze.module.login.modle.UserEntity;
import com.xinze.xinze.utils.ACache;

/**
 * @author lxf
 * Created by Administrator on 2017/12/6.
 */

public class App extends MultiDexApplication {
    private static App context ;
    public static UserEntity mUser;
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context,@NonNull RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.themeBlack, android.R.color.white);
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        LeakCanary.install(this);
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
