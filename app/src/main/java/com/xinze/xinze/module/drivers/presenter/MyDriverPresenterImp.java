package com.xinze.xinze.module.drivers.presenter;

import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.module.drivers.view.MyDriverActivity;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.utils.ReturnResult;

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
    private MyDriverActivity mActivity;


    public MyDriverPresenterImp(MyDriverActivity myDriverActivity) {
        this.mActivity = myDriverActivity;
    }


    @Override
    public void myTruckDrivers(final int pageNum, int pageSize, String inviteFlag) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().myTruckDrivers(headers, pageNum, pageSize, inviteFlag).enqueue(new Callback<ReturnResult<List<TruckownerDriverVO>>>() {

            @Override
            public void onResponse(Call<ReturnResult<List<TruckownerDriverVO>>> call, Response<ReturnResult<List<TruckownerDriverVO>>> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    mActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                final Object obj = returnResult.getData();
                if (obj != null) {
                    List<TruckownerDriverVO> data = (List<TruckownerDriverVO>) returnResult.getData();
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
            public void onFailure(Call<ReturnResult<List<TruckownerDriverVO>>> call, Throwable t) {
                // 请求失败
                mActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }

}
