package com.xinze.xinze.http.listener;

import android.os.Bundle;

/**
 * 生命周期监听
 *
 * @author lxf
 */

public interface LifeCycleListener {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}