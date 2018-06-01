package com.xinze.xinze.module.trucks.presenter;

import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.module.trucks.model.MyTruckVO;
import com.xinze.xinze.module.trucks.view.MyTruckActivity;
import com.xinze.xinze.utils.ReturnResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author  feibai on 2018/5/14.
 * desc:
 */

public class MyTruckPresenterImp implements IMyTruckPresenter {

    private MyTruckActivity mActivity;



    public MyTruckPresenterImp(MyTruckActivity myTruckActivity) {
        this.mActivity = myTruckActivity;
    }




    @Override
    public void myTrucks(final int pageNum, int pageSize, String verifyFlag) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().myTrucks(headers, pageNum, pageSize, verifyFlag).enqueue(new Callback<ReturnResult<List<MyTruckVO>>>() {

            @Override
            public void onResponse(Call<ReturnResult<List<MyTruckVO>>> call, Response<ReturnResult<List<MyTruckVO>>> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (returnResult != null && !returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    mActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }

                if (returnResult != null && returnResult.getData() != null) {
                    List<MyTruckVO> data = (List<MyTruckVO>) returnResult.getData();
                    if (data != null && data.size() == AppConfig.PAGE_SIZE) {
                        mActivity.setPageEndFlag(false);
                        mActivity.setData(data);
                        mActivity.getInitDataSuccess();
                    } else if (data != null && data.size() < AppConfig.PAGE_SIZE) {
                        // 获取到最后一页
                        mActivity.setPageEndFlag(true);
                        mActivity.setData(data);
                        mActivity.getInitDataSuccess();
                    } else {
                        mActivity.setPageEndFlag(true);
                    }
                } else {
                    // 响应数据为空
                    mActivity.setPageEndFlag(true);
                    if (pageNum == AppConfig.PAGE_NO) {
                        // 如果第一页需要显示说明页
                        mActivity.showEmptyPage();

                    }
                    mActivity.getInitDataSuccess();
                }
            }

            @Override
            public void onFailure(Call<ReturnResult<List<MyTruckVO>>> call, Throwable t) {
                // 请求失败
                mActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }

    @Override
    public void delMyTruck(String itemId) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
/*        RetrofitFactory.getInstence().Api().delMyDriver(headers, itemId).enqueue(new Callback<ReturnResult>() {
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
        });*/

    }

}
