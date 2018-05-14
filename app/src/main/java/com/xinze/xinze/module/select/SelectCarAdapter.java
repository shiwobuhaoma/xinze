package com.xinze.xinze.module.select;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

        String ownFlag = car.getOwnFlag();
        String own = mContext.getResources().getString(R.string.select_item_own);
        String s = String.format(own, truckName, ownDesc);
        holder.selectCar.setText(Html.fromHtml(s));

        //1表示自有 0 表示关联
        String vertifyFlag = car.getVertify_flag();
//        if ("1".equals(ownFlag)) {
            //1表示已审核 0 审核失败 2 审核中
            if ("1".equals(vertifyFlag)) {
                String state = mContext.getResources().getString(R.string.select_item_audited);
                String format = String.format(state);
                holder.selectAuditedState.setText(Html.fromHtml(format));

            } else if ("0".equals(vertifyFlag)) {
                String state = mContext.getResources().getString(R.string.select_item_audited_and_no_relation);
                String format = String.format(state);
                holder.selectAuditedState.setText(Html.fromHtml(format));
            } else {
                String state = mContext.getResources().getString(R.string.select_item_audit);
                String format = String.format(state);
                holder.selectAuditedState.setText(Html.fromHtml(format));
            }

//        }
        if (car.isSelected()){
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.select_choicd);
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            drawable.setBounds(0,0,intrinsicWidth,intrinsicHeight);
            holder.selectCar.setCompoundDrawables(drawable,null,null,null);
        }else{
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.select_choice);
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            drawable.setBounds(0,0,intrinsicWidth,intrinsicHeight);
            holder.selectCar.setCompoundDrawables(drawable,null,null,null);
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
        if (mCarList.get(position).isSelected()){
            mCarList.get(position).setSelected(false);
        }else{
            mCarList.get(position).setSelected(true);
        }

        notifyItemChanged(position);
    }

    public void updateAll(boolean isAllSelected) {
        for (Car car : mCarList){
            if (isAllSelected){
                car.setSelected(true);
            }else{
                car.setSelected(false);
            }
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void click(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
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
