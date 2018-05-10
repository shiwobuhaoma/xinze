package com.xinze.xinze.module.sysmsg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.main.modle.NotifyEntity;
import com.xinze.xinze.module.main.presenter.SystemMsgPresenterImp;
import com.xinze.xinze.module.sysmsg.SystemMsgActivity;
import com.xinze.xinze.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 *         系统消息的适配器
 *         Created by LF on 2018/4/24.
 */

public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.ViewHolder> {
    private SystemMsgActivity mContext;
    private ArrayList<NotifyEntity> listSysMsg;
    private List<NotifyEntity> mBS;
    SystemMsgPresenterImp opi;


    public SystemMessageAdapter(SystemMsgActivity mContext, ArrayList<NotifyEntity> listSysMsg, SystemMsgPresenterImp paramOpi) {
        this.listSysMsg = listSysMsg;
        this.mContext = mContext;
        this.opi = paramOpi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.system_msg_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        NotifyEntity notifyEntity = mBS.get(position);
        String title = notifyEntity.getTitle();
        String type = "";
        String typeStr=notifyEntity.getType()==null?"":notifyEntity.getType();
        switch (typeStr) {
            case AppConfig.NOTIFY_TYPE_SYSTEM:
                type="["+AppConfig.NOTIFY_TYPE_MAP.get(typeStr)+"]";
                break;
            case AppConfig.NOTIFY_TYPE_INVITE:
                type="["+AppConfig.NOTIFY_TYPE_MAP.get(typeStr)+"]";
                break;
            case AppConfig.NOTIFY_TYPE_WAYBILL:
                type="["+AppConfig.NOTIFY_TYPE_MAP.get(typeStr)+"]";
                break;
            case AppConfig.NOTIFY_TYPE_ORDER:
                type="["+AppConfig.NOTIFY_TYPE_MAP.get(typeStr)+"]";
                break;
        }
        final String content = notifyEntity.getContent();
        String createDate = notifyEntity.getCreateDate();
        final String id = notifyEntity.getId();
        final String readStatus = notifyEntity.getReadFlag() == null
                ? AppConfig.SYSTEM_MSG_UNREAD : notifyEntity.getReadFlag().equals(AppConfig.YES)
                ? AppConfig.SYSTEM_MSG_READ : AppConfig.SYSTEM_MSG_UNREAD;
        holder.tvSuccessReadStatus.setText(readStatus);
        if (readStatus.equals(AppConfig.SYSTEM_MSG_UNREAD)){
            holder.tvSuccessReadStatus.setTextColor(android.graphics.Color.RED);
        }else{
            holder.tvSuccessReadStatus.setTextColor(Color.BLACK);
        }
        holder.tvSuccessMsg.setText(type+title);
        holder.tvSuccessContent.setText(content);
        holder.tvSuccessTime.setText(createDate);
        holder.itemView.setTag(position);
        holder.lvMsgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果未读需要请求标记已读
                if (readStatus.equals(AppConfig.SYSTEM_MSG_UNREAD)) {
                    opi.markReaded(id, holder);
                }else{
                    holder.tvSuccessReadStatus.setTextColor(Color.BLACK);
                }

                DialogUtil.showSystemMsgContentDialog(mContext, content);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBS == null ? 0 : mBS.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_success_msg)
        TextView tvSuccessMsg;
        @BindView(R.id.tv_success_time)
        TextView tvSuccessTime;
        @BindView(R.id.tv_success_content)
        TextView tvSuccessContent;
        @BindView(R.id.lv_item)
        LinearLayout lvMsgItem;
        @BindView(R.id.tv_success_read_status)
        public
        TextView tvSuccessReadStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public void setData(List<NotifyEntity> data) {
        this.mBS = data;
        notifyDataSetChanged();
    }
}
