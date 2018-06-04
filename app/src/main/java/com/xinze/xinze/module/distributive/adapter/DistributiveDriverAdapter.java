package com.xinze.xinze.module.distributive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.distributive_driver_call_iv)
        ImageView distributiveDriverCallIv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            distributiveDriverCallIv.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.distributive_driver_call_iv:
                    mOnItemClickListener.call((int) itemView.getTag());
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
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
