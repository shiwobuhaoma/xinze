package com.xinze.xinze.module.invite.presenter;

import com.xinze.xinze.App;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.module.invite.fragment.DriverInviteFragment;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.invite.view.InviteDetailActivity;
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

public class InvitePresenterImp implements IInvitePresenter {
    private DriverInviteFragment mFragment;
    private BaseActivity mActivity;

    public InvitePresenterImp(DriverInviteFragment driverInviteFragment) {
        this.mFragment = driverInviteFragment;
    }

    public InvitePresenterImp(InviteDetailActivity inviteDetailActivity) {
        this.mActivity =inviteDetailActivity;
    }

    @Override
    public void getMyTruckOwnerInvitation(int pageNum, int pageSize, String inviteFlag) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().getTruckOwnerInvitation(headers, pageNum, pageSize, inviteFlag).enqueue(new Callback<ReturnResult<List<TruckownerDriverVO>>>() {

            @Override
            public void onResponse(Call<ReturnResult<List<TruckownerDriverVO>>> call, Response<ReturnResult<List<TruckownerDriverVO>>> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    mFragment.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
                final Object obj = returnResult.getData();
                if (obj!=null) {
                    List<TruckownerDriverVO> data = (List<TruckownerDriverVO>) returnResult.getData();
                    if (data!=null&&data.size()==AppConfig.PAGE_SIZE) {
                        mFragment.setPageEndFlag(false);
                        mFragment.setData(data);
                        mFragment.getInitDataSuccess();
                    }else if (data!=null&&data.size()<AppConfig.PAGE_SIZE) {
                        // 获取到最后一页
                        mFragment.setPageEndFlag(true);
                        mFragment.setData(data);
                        mFragment.getInitDataSuccess();
                    }else{
                        mFragment.setPageEndFlag(true);
                    }
                }else{
                    // 响应数据为空
                    mFragment.setPageEndFlag(true);
                    mFragment.getInitDataSuccess();
                }
            }

            @Override
            public void onFailure(Call<ReturnResult<List<TruckownerDriverVO>>> call, Throwable t) {
                // 请求失败
                mFragment.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });
    }

    @Override
    public void responseInvitation(String itemId, String inviteFlag, String inviteResponseType, String content) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().responseInvitation(headers,itemId,inviteResponseType,inviteFlag,content).enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                // 请求成功
                ReturnResult returnResult = response.body();
                if (!returnResult.getStatus().equals(AppConfig.REQUEST_STATUS_SUCESS)) {
                    mActivity.shotToast(returnResult.getMsg() == null ? AppConfig.COMMON_FAILURE_RESPONSE : returnResult.getMsg());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable t) {
                mActivity.shotToast(AppConfig.COMMON_FAILURE_RESPONSE);
            }
        });

    }
}
