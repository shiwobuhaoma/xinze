package com.xinze.xinze.module.select.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.transport.module.Car;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCarAdapter extends RecyclerView.Adapter<SelectCarAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Car> mCarList;

    public SelectCarAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public SelectCarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_rv_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCarAdapter.ViewHolder holder, int position) {
        Car car = mCarList.get(position);
        String truckName = car.getTruck_name();
        String ownDesc = car.getOwnDesc();

        String own = mContext.getResources().getString(R.string.select_item_own);
        String s = String.format(own, truckName, ownDesc);
        holder.selectCar.setText(Html.fromHtml(s));

        //可抢单 和 不可抢单
        String reason = car.getReason();

        //0表示自有 1表示关联
        String ownFlag = car.getOwnFlag();
        if ("0".equals(ownFlag)) {
            //driverId不为空说明车辆已经关联司机  否则未关联司机
            String driverId = car.getDriver_id();

            //审核状态 1表示已审核 0 审核失败 2 审核中
            String verifyFlag = car.getVertify_flag();
            //审核状态描述
            String verifyDesc = car.getVertify_desc();
            if ("1".equals(verifyFlag)) {

                if (!TextUtils.isEmpty(driverId)) {
                    //审核已通过，司机已关联
                    String state = mContext.getResources().getString(R.string.select_item_audited);
                    String format = String.format(state, verifyDesc);
                    holder.selectAuditedState.setText(Html.fromHtml(format));
                } else {
                    //审核已通过，司机未关联
                    String state = mContext.getResources().getString(R.string.select_item_audited_and_no_relation);
                    String format = String.format(state, verifyDesc, "未关联司机");
                    holder.selectAuditedState.setText(Html.fromHtml(format));
                }

            } else if ("0".equals(verifyFlag)) {
                String state = mContext.getResources().getString(R.string.select_item_audited_failed);
                String format = String.format(state, verifyDesc);
                holder.selectAuditedState.setText(Html.fromHtml(format));
            } else {
                String state = mContext.getResources().getString(R.string.select_item_audit);
                String format = String.format(state, verifyDesc);
                holder.selectAuditedState.setText(Html.fromHtml(format));
            }
        } else {
            //1可抢单  0不可抢单
            String rightFlag = car.getRight_flag();
            if ("1".equals(rightFlag)) {
                holder.selectAuditedState.setTextColor(mContext.getResources().getColor(R.color.themeOrange));
            } else {
                holder.selectAuditedState.setTextColor(mContext.getResources().getColor(R.color.gray));
            }
            holder.selectAuditedState.setText(reason);
        }


        if (car.isSelected()) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.select_choicd);
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            holder.selectCar.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.select_choice);
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            holder.selectCar.setCompoundDrawables(drawable, null, null, null);
        }
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mCarList == null ? 0 : mCarList.size();
    }

    public void setData(List<Car> carList) {
        mCarList = carList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.click(v, (Integer) v.getTag());
    }

    public void updateState(int position) {
        Car car = mCarList.get(position);
        if ("0".equals(car.getOwnFlag())) {
            if ("1".equals(car.getVertify_flag())) {
                if (car.isSelected()) {
                    car.setSelected(false);
                } else {
                    car.setSelected(true);
                }
            } else {
                car.setSelected(false);
            }
        } else {
            if ("1".equals(car.getRight_flag())) {
                if (car.isSelected()) {
                    car.setSelected(false);
                } else {
                    car.setSelected(true);
                }
            } else {
                car.setSelected(false);
            }
        }


        notifyItemChanged(position);
    }

    public void updateAll(boolean isAllSelected) {
        for (Car car : mCarList) {
            if ("0".equals(car.getOwnFlag())) {
                if ("1".equals(car.getVertify_flag())) {
                    car.setSelected(isAllSelected);
                } else {
                    car.setSelected(false);
                }
            } else {
                if ("1".equals(car.getRight_flag())) {
                    car.setSelected(isAllSelected);
                } else {
                    car.setSelected(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void click(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.select_car)
        TextView selectCar;
        @BindView(R.id.select_audited_state)
        TextView selectAuditedState;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
