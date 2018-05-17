package com.xinze.xinze.module.invite.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.xinze.xinze.module.invite.fragment.OwnerInviteFragment;
import com.xinze.xinze.module.invite.model.OwnerDriverVO;
import com.xinze.xinze.module.invite.view.InviteDetailActivity;
import com.xinze.xinze.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xinze.xinze.utils.UIUtils.getResources;

/**
 * Created by feibai on 2018/5/14.
 * desc:OwnerInviteRecycleViewAdapter
 */

public class OwnerInviteRecycleViewAdapter extends RecyclerView.Adapter<OwnerInviteRecycleViewAdapter.ViewHolder> {

    private List<OwnerDriverVO> mBS;
    private Context mContext;
    private OwnerInviteFragment mFragment;

    public OwnerInviteRecycleViewAdapter(Activity mActivity, OwnerInviteFragment ownerInviteFragment) {
        this.mContext = mActivity;
        this.mFragment = ownerInviteFragment;
    }

    private View mView;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.owner_invite_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final OwnerDriverVO ownerDriver = mBS.get(position);

        String ownerName = ownerDriver.getOwnerName();
        final String ownerMobile = ownerDriver.getOwnerMobile();
        String createDate = ownerDriver.getCreateDate();
        String inviteFlag = ownerDriver.getInviteFlag() == null ? AppConfig.INVITE_FLAG_CONTINUE : ownerDriver.getInviteFlag();
        String ownerType = ownerDriver.getOwnerType() == null ? AppConfig.INVITE_OWNER_TYPE_COMPANY : ownerDriver.getOwnerType();

        holder.ownerInviteNameTextView.setText(ownerName);
        // 根据货主类型填充货主类型图片
        switch (ownerType) {
            case AppConfig.INVITE_OWNER_TYPE_COMPANY:
                // 公司类型
                Drawable companyDrawable = getResources().getDrawable(R.mipmap.invite_company);
                // 设置边界
                companyDrawable.setBounds(0, 0, companyDrawable.getMinimumWidth(), companyDrawable.getMinimumHeight());
                holder.ownerInviteNameTextView.setCompoundDrawablePadding(UIUtils.dip2px(10));
                holder.ownerInviteNameTextView.setCompoundDrawablesRelative(null,null,companyDrawable,null);
                break;
            case AppConfig.INVITE_OWNER_TYPE_AGENT:
                // 信息部类型
                Drawable agentDrawable = getResources().getDrawable(R.mipmap.invite_info_agent);
                // 设置边界
                agentDrawable.setBounds(0, 0, agentDrawable.getMinimumWidth(), agentDrawable.getMinimumHeight());
                holder.ownerInviteNameTextView.setCompoundDrawablePadding(UIUtils.dip2px(10));
                holder.ownerInviteNameTextView.setCompoundDrawablesRelative(null,null,agentDrawable,null);
                break;

            default:
                break;
        }

        holder.ownerInviteTimeTextView.setText(createDate);
        switch (inviteFlag) {
            case AppConfig.INVITE_FLAG_CONTINUE:
                // 待确认
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                holder.ownerInviteStatusTextView.setTextColor(getResources().getColor(R.color.hint_color));
                break;
            case AppConfig.YES:
                // 已同意
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                holder.ownerInviteStatusTextView.setTextColor(getResources().getColor(R.color.themeBlue));
                break;
            case AppConfig.NO:
                // 已拒绝
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(inviteFlag);
                holder.ownerInviteStatusTextView.setTextColor(getResources().getColor(R.color.themeOrange));
                break;

            default:
                inviteFlag = AppConfig.INVITE_FLAG_MAP.get(AppConfig.INVITE_FLAG_CONTINUE);
                break;
        }
        holder.ownerInviteStatusTextView.setText(inviteFlag);

        // 给电话图片绑定点击事件
        holder.ownerInviteCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ownerMobile));
                mFragment.startActivity(intent);
            }
        });

        //给RL绑定点击事件
        holder.ownerInviteRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到相关详情页
                Intent intent = new Intent(App.getContext(), InviteDetailActivity.class);
                intent.putExtra("type",AppConfig.INVITE_RESPONSE_TYPE_OWNER);
                intent.putExtra("data", ownerDriver);
                mFragment.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mBS == null ? 0 : mBS.size();
    }

    public void setData(List<OwnerDriverVO> data) {
        this.mBS = data;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.owner_invite_name_tv)
        TextView ownerInviteNameTextView;
        @BindView(R.id.owner_invite_time_tv)
        TextView ownerInviteTimeTextView;
        @BindView(R.id.owner_invite_call_iv)
        ImageView ownerInviteCallImageView;
        @BindView(R.id.owner_invite_status_tv)
        TextView ownerInviteStatusTextView;
        @BindView(R.id.owner_invite_rl)
        RelativeLayout ownerInviteRelativeLayout;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
