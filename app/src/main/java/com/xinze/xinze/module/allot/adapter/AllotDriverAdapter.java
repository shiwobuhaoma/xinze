package com.xinze.xinze.module.allot.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllotDriverAdapter extends RecyclerView.Adapter<AllotDriverAdapter.ViewHolder> {
    private Context mContext;
    private List<TruckownerDriverVO> mData;

    public AllotDriverAdapter(Activity activity) {
        mContext = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.all_driver_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TruckownerDriverVO truckownerDriverVO = mData.get(position);
        String driverMobile = truckownerDriverVO.getDriverMobile();
        String driverName = truckownerDriverVO.getDriverName();
        String selectDriver = mContext.getResources().getString(R.string.select_driver);
        holder.allotDriverNameTel.setText(Html.fromHtml(String.format(selectDriver,driverName,driverMobile)));

        if (truckownerDriverVO.isChecked()){
            holder.allotDriverCb.setBackground(mContext.getResources().getDrawable(R.mipmap.my_driver_selected));
        }else{
            holder.allotDriverCb.setBackground(mContext.getResources().getDrawable(R.mipmap.my_driver_unselected));
        }

        if ("1".equals(truckownerDriverVO.getRightFlag())){
            holder.allotDriverRightFlag.setVisibility(View.VISIBLE);
        }else{
            holder.allotDriverRightFlag.setVisibility(View.GONE);
        }

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.allot_driver_cb)
        ImageView allotDriverCb;
        @BindView(R.id.allot_driver_name_tel)
        TextView allotDriverNameTel;
        @BindView(R.id.allot_driver_right_flag)
        TextView allotDriverRightFlag;
        @BindView(R.id.allot_driver_phone)
        ImageView allotDriverPhone;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
            allotDriverPhone.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            mOnCheckedAndClickListener.onClickView(v,(int)itemView.getTag());
        }
    }

    private OnClickListener mOnCheckedAndClickListener;

    public void setOnClickListener(OnClickListener onCheckedAndClickListener){
        mOnCheckedAndClickListener = onCheckedAndClickListener;
    }

    public interface OnClickListener{
        void onClickView(View v,int position);
    }

    public void setData(List<TruckownerDriverVO> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

}
