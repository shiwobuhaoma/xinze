package com.xinze.xinze.module.message.presenter;

import android.content.Context;
import android.graphics.Color;

import com.xinze.xinze.App;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.message.model.NotifyEntity;
import com.xinze.xinze.module.message.view.ISystemMsgView;
import com.xinze.xinze.module.message.view.SystemMsgActivity;
import com.xinze.xinze.module.message.adapter.SystemMessageAdapter;
import com.xinze.xinze.mvpbase.BasePresenterImpl;
import com.xinze.xinze.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中介
 *
 * @author lxf
 */
public class SystemMsgPresenterImp extends BasePresenterImpl<ISystemMsgView> implements ISystemMsgPresenter {
    private SystemMsgActivity systemMsgActivity;

    public SystemMsgPresenterImp(ISystemMsgView mPresenterView, Context mContext) {
        super(mPresenterView, mContext);
        this.mContext = mContext;
        systemMsgActivity = (SystemMsgActivity) mPresenterView;
    }

    @Override
    public void getSystemMsgList(int pageNo, int pageSize) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().getSystemMsgList(headers, pageNo, pageSize).compose(this.<BaseEntity<List<NotifyEntity>>>setThread()).subscribe(new BaseObserver<List<NotifyEntity>>() {
            @Override
            protected void onSuccees(BaseEntity<List<NotifyEntity>> t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        List<NotifyEntity> data = t.getData();
                        if (data != null) {
                            if (data.size() <systemMsgActivity.getPageSize()) {
                                // 如果获取的数据集长度小于分页长度标记为最后一页
                                systemMsgActivity.setPageEndFlag(true);
                                systemMsgActivity.setData(data);
                                systemMsgActivity.getOrderListSuccess();
                            }else {
                                systemMsgActivity.setPageEndFlag(false);
                                systemMsgActivity.setData(data);
                                systemMsgActivity.getOrderListSuccess();
                            }

                        } else {
                            systemMsgActivity.getOrderListSuccess();
                            systemMsgActivity.shotToast(AppConfig.LOAD_INFO_FINISH);
                        }
                    }else{
                        systemMsgActivity.shotToast(t.getMsg());
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                systemMsgActivity.getOrderListFailed();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void markReaded(String msgId, final SystemMessageAdapter.ViewHolder holder) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put("sessionid", App.mUser.getSessionid());
        headers.put("userid", App.mUser.getId());
        RetrofitFactory.getInstence().Api().markNoticeReaded(headers, msgId).compose(this.<BaseEntity>setThread()).subscribe(new BaseObserver(systemMsgActivity) {

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                if (t != null) {
                    if (t.isSuccess()) {
                        System.out.println("------------------标记已读");
                        holder.tvSuccessReadStatus.setText(AppConfig.SYSTEM_MSG_READ);
                        holder.tvSuccessReadStatus.setTextColor(Color.BLACK);
                        //TODO 主页未读条数需要-1
                    } else {
                        ToastUtils.showToast(mContext, "标记已读失败");
                    }
                }
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                System.out.println();
                e.printStackTrace();
                ToastUtils.showToast(mContext, "标记已读失败");
            }
        });

    }
}
