package com.xinze.xinze.module.invite.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.invite.fragment.DriverInviteFragment;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.invite.view.InviteDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xinze.xinze.utils.UIUtils.getResources;

/**
 * Created by feibai on 2018/5/14.
 * desc:DriverInviteRecycleViewAdapter
 */

public class DriverInviteRecycleViewAdapter extends RecyclerView.Adapter<DriverInviteRecycleViewAdapter.ViewHolder> {

    private List<TruckownerDriverVO> mBS;
    private Context mContext;
    private DriverInviteFragment mFragment;

    public DriverInviteRecycleViewAdapter(Activity mActivity, DriverInviteFragment driverInviteFragment) {
        this.mContext = mActivity;
        this.mFragment = driverInviteFragment;
    }

    private View mView;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.driver_invite_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final TruckownerDriverVO truckownerDriver = mBS.get(position);

        String truckOwnerName = truckownerDriver.getTruckOwnerName();
        final String truckOwnerMobile = truckownerDriver.getTruckOwnerMobile();
        String itemId = truckownerDriver.getId();
        String createDate = truckownerDriver.getCreateDate();
        String inviteFlag = truckownerDriver.getInviteFlag()==null?AppConfig.INVITE_FLAG_CONTINUE:truckownerDriver.getInviteFlag();
        String content = truckownerDriver.getContent();

        holder.driverInviteNameTextView.setText(truckOwnerName);
        holder.driverInviteTimeTextView.setText(createDate);
        switch (inviteFlag) {
            case AppConfig.INVITE_FLAG_CONTINUE:
                // 待确认
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                holder.driverInviteStatusTextView.setTextColor(getResources().getColor(R.color.hint_color));
                break;
            case AppConfig.YES:
                // 已同意
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                holder.driverInviteStatusTextView.setTextColor(getResources().getColor(R.color.themeBlue));
                break;
            case AppConfig.NO:
                // 已拒绝
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                holder.driverInviteStatusTextView.setTextColor(getResources().getColor(R.color.themeOrange));
                break;

            default:
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(AppConfig.INVITE_FLAG_CONTINUE);
                break;
        }
        holder.driverInviteStatusTextView.setText(inviteFlag);

        // 给电话图片绑定点击事件
        holder.driverInviteCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+truckOwnerMobile));
                mFragment.startActivity(intent);
            }
        });

        //给RL绑定点击事件
        holder.driverInviteRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到相关详情页
                Intent intent = new Intent(App.getContext(),InviteDetailActivity.class);
                intent.putExtra("type",AppConfig.INVITE_RESPONSE_TYPE_TRUCKOWNER);
                intent.putExtra("data",truckownerDriver);
                mFragment.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mBS == null ? 0 : mBS.size();
    }

    public void setData(List<TruckownerDriverVO> data) {
        this.mBS = data;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.driver_invite_name_tv)
        TextView driverInviteNameTextView;
        @BindView(R.id.driver_invite_time_tv)
        TextView driverInviteTimeTextView;
        @BindView(R.id.driver_invite_call_iv)
        ImageView driverInviteCallImageView;
        @BindView(R.id.driver_invite_status_tv)
        TextView driverInviteStatusTextView;
        @BindView(R.id.driver_invite_rl)
        RelativeLayout driverInviteRelativeLayout;



        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
