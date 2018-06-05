package com.xinze.xinze.module.distributive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DistributiveDriverAdapter extends RecyclerView.Adapter<DistributiveDriverAdapter.ViewHolder> {
    private Context mContext;
    private List<TruckownerDriverVO> mData;


    public DistributiveDriverAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.distributive_driver_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mData.get(position).isChecked()) {
            holder.distributiveIvSelected.setBackground(mContext.getResources().getDrawable(R.mipmap.my_driver_selected));
            holder.allowRobbing.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);

        } else {
            holder.distributiveIvSelected.setBackground(mContext.getResources().getDrawable(R.mipmap.my_driver_unselected));
            holder.allowRobbing.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }
        if (position == mData.size() - 1) {
            holder.space.setVisibility(View.GONE);
        } else {
            holder.space.setVisibility(View.VISIBLE);
        }


        holder.distributiveDriverNameTv.setText(mData.get(position).getDriverName());
        holder.distributiveDriverPhoneTv.setText(mData.get(position).getDriverMobile());

        if ("1".equals(mData.get(position).getRightFlag())) {
            holder.allowRobbingCb.setChecked(true);
        } else {
            holder.allowRobbingCb.setChecked(false);
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.distributive_driver_call_iv)
        ImageView distributiveDriverCallIv;
        @BindView(R.id.distributive_iv_selected)
        ImageView distributiveIvSelected;
        @BindView(R.id.distributive_driver_name_tv)
        TextView distributiveDriverNameTv;
        @BindView(R.id.distributive_driver_phone_tv)
        TextView distributiveDriverPhoneTv;
        @BindView(R.id.allow_robbing)
        LinearLayout allowRobbing;
        @BindView(R.id.allow_robbing_cb)
        CheckBox allowRobbingCb;
        @BindView(R.id.directional_line)
        View line;
        @BindView(R.id.distributive_space)
        View space;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            distributiveDriverCallIv.setOnClickListener(this);
            itemView.setOnClickListener(this);
            allowRobbing.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.distributive_driver_call_iv:
                    mOnItemClickListener.call((int) itemView.getTag());
                    break;
                case R.id.allow_robbing:
                    mOnItemClickListener.allowRobbing((int) itemView.getTag());
                    break;
                default:
                    mOnItemClickListener.click(v, (int) itemView.getTag());
                    break;
            }
        }
    }

    public void setData(List<TruckownerDriverVO> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void click(View view, int position);

        void call(int position);

        void allowRobbing(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
