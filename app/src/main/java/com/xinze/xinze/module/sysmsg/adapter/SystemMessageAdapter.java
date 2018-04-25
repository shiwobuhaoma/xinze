package com.xinze.xinze.module.sysmsg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.sysmsg.SystemMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 *         系统消息的适配器
 *         Created by LF on 2018/4/24.
 */

public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<SystemMessage> listSysMsg;

    public SystemMessageAdapter(Context mContext, ArrayList<SystemMessage> listSysMsg) {
        this.listSysMsg = listSysMsg;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.system_msg_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listSysMsg.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_success_msg)
        TextView tvSuccessMsg;
        @BindView(R.id.tv_success_time)
        TextView tvSuccessTime;
        @BindView(R.id.tv_success_content)
        TextView tvSuccessContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
