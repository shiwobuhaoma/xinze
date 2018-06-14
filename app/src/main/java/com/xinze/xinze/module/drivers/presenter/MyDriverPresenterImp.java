package com.xinze.xinze.module.drivers.presenter;

import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.module.drivers.view.DriverAddActivity;
import com.xinze.xinze.module.drivers.view.MyDriverActivity;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.utils.MessageEvent;
import com.xinze.xinze.utils.ReturnResult;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by feibai on 2018/5/14.
 * desc:
 */

public class MyDriverPresenterImp implements IMyDriverPresenter {


    private MyDriverActivity myDriverActivity;
    private DriverAddActivity driverAddActivity;


    public MyDriverPresenterImp(MyDriverActivity myDriverActivity) {
        this.myDriverActivity = myDriverActivity;
    }

    public MyDriverPresenterImp(DriverAddActivity driverAddActivity) {
        this.driverAddActivity = driverAddActivity;
    }



    @Override
    public void myTruckDrivers(final int pageNum, int pageSize, String inviteFlag) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().myTruckDrivers(headers, pageNum, pageSize, inviteFlag).enqueue(new Callback<ReturnResult<List<TruckownerDriverVO>>>() {

            @Override
            public void onResponse(Call<ReturnResult<List<TruckownerDriverVO>>> call, Response<ReturnResult<List<TruckownerDriverVO>>> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (returnResult == null){
                    return;
                }
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    myDriverActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                final Object obj = returnResult.getData();
                if (obj != null) {
                    EventBus.getDefault().post(new MessageEvent(AppConfig.UPDATE_COUNT));
                    List<TruckownerDriverVO> data = (List<TruckownerDriverVO>) obj;
                    if ( data.size() == AppConfig.PAGE_SIZE) {
                        myDriverActivity.setPageEndFlag(false);
                        myDriverActivity.setData(data);
                        myDriverActivity.getInitDataSuccess();
                    } else if ( data.size() < AppConfig.PAGE_SIZE) {
                        // 获取到最后一页
                        myDriverActivity.setPageEndFlag(true);
                        myDriverActivity.setData(data);
                        myDriverActivity.getInitDataSuccess();
                    } else {
                        myDriverActivity.setPageEndFlag(true);
                    }
                } else {
                    // 响应数据为空
                    myDriverActivity.setPageEndFlag(true);
                    if (pageNum == AppConfig.PAGE_NO) {
                        // 如果第一页需要显示说明页
                        myDriverActivity.showEmptyPage();
                    }
                    myDriverActivity.getInitDataSuccess();
                }
            }

            @Override
            public void onFailure(Call<ReturnResult<List<TruckownerDriverVO>>> call, Throwable t) {
                // 请求失败
                myDriverActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }

    @Override
    public void delMyDriver(String itemId) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().delMyDriver(headers, itemId).enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    myDriverActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                myDriverActivity.shotToast(AppConfig.COMMON_SUCCESS_RESPONSE);
                // 删除成功需要刷新页面
                myDriverActivity.setRefreshData();
            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable t) {
                myDriverActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });

    }

    @Override
    public void inviteDriver(String phone) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().inviteDriver(headers, phone).enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    driverAddActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                driverAddActivity.shotToast(AppConfig.COMMON_SUCCESS_RESPONSE);
                //TODO 跳转到邀请记录界面

            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable t) {
                driverAddActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }

}
