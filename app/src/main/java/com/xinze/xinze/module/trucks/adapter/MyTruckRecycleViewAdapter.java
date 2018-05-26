package com.xinze.xinze.module.trucks.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.trucks.model.MyTruckVO;
import com.xinze.xinze.module.trucks.model.TruckDriverVO;
import com.xinze.xinze.module.trucks.model.TruckEntity;
import com.xinze.xinze.module.trucks.view.MyTruckActivity;
import com.xinze.xinze.utils.DialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author feibai
 * @date 2018/5/14
 * desc:MyTruckRecycleViewAdapter
 */
public class MyTruckRecycleViewAdapter extends RecyclerView.Adapter<MyTruckRecycleViewAdapter.ViewHolder> {

    private List<MyTruckVO> mBS;
    private MyTruckActivity mActivity;
    private View mView;


    public MyTruckRecycleViewAdapter(MyTruckActivity myDriverActivity) {
        this.mActivity = myDriverActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mActivity).inflate(R.layout.my_truck_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // 获取数据
        final MyTruckVO entity = mBS.get(position);
        TruckEntity truck = entity.getTruck();
        TruckDriverVO truckDriver = entity.getTruckDriver();
        String itemId = truck.getId();
        String truckName = truck.getTruckName();
        String updateDate = truck.getUpdateDate();
        String vertifyFlag = truck.getVertifyFlag();
        Integer weight = truck.getWeight();


        String truckType = entity.getTruckType();
        String reason = truck.getReason();

        // 展示数据
        holder.nameTextView.setText(truckName);
        StringBuffer contentSb = new StringBuffer();
        switch (vertifyFlag) {
            case AppConfig.YES:
                // 审核通过
                holder.statusImageView.setImageDrawable(mActivity.getResources().getDrawable(R.mipmap.check_yes));
                holder.nextImageView.setVisibility(View.VISIBLE);
                holder.editImageView.setVisibility(View.GONE);
                holder.delImageView.setVisibility(View.GONE);
                // 拼装车辆内容信息
                String rightFlag = null;
                if (truckDriver != null) {
                    String driverPhone = truckDriver.getDriverPhone();
                    rightFlag = truckDriver.getRightFlag();
                    String rightFlagStr = rightFlag.equals(AppConfig.YES) ? "可接单" : "不可接单";
                    String tempStr = mActivity.getString(R.string.my_truck_driver_info);
                    String blueStr = String.format(tempStr, driverPhone, rightFlagStr);
                    contentSb.append(blueStr);
                }
                contentSb.append(truckType).append("/").append(weight).append("吨<br>");
                contentSb.append(updateDate);
                holder.contentTextView.setText(Html.fromHtml(contentSb.toString()));
                if (rightFlag.equals(AppConfig.NO)) {
                    // next按钮绑定点击事件
                    holder.nextImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 跳转至分配司机界面
                            //mActivity.startActivity(intent);
                        }
                    });
                }
                break;
            case AppConfig.NO:
                // 审核不通过
                holder.statusImageView.setImageDrawable(mActivity.getResources().getDrawable(R.mipmap.check_no));
                holder.nextImageView.setVisibility(View.GONE);
                holder.editImageView.setVisibility(View.VISIBLE);
                holder.delImageView.setVisibility(View.VISIBLE);
                // 拼装车辆内容信息
                contentSb.append(truckType).append("/").append(weight).append("吨\n");
                contentSb.append("审核备注:").append(reason).append("\n");
                contentSb.append(updateDate);
                holder.contentTextView.setText(contentSb.toString());

                // 为编辑按钮绑定点击事件
                holder.editImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转至编辑页面
                    }
                });
                // 为删除按钮绑定点击事件
                holder.delImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.showCommonDialog(mActivity, "确定要删除吗?", new DialogUtil.ChoiceClickListener() {
                            @Override
                            public void onClickSureView(Object data) {
                                // mActivity.getmPresenter().delMyTruck(itemId);
                            }

                            @Override
                            public void onClickCancelView(Object data) {
                            }
                        });
                    }
                });
                break;
            case AppConfig.CONTINUE:
                // 审核中
                holder.statusImageView.setImageDrawable(mActivity.getResources().getDrawable(R.mipmap.check_continue));
                holder.nextImageView.setVisibility(View.GONE);
                holder.editImageView.setVisibility(View.GONE);
                holder.delImageView.setVisibility(View.GONE);
                // 拼装车辆内容信息
                contentSb.append(truckType).append("/").append(weight).append("吨\n");
                contentSb.append(updateDate);
                holder.contentTextView.setText(contentSb.toString());
                // 状态图标上移
                //holder.lineView.setVisibility(View.GONE);
                //RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.statusImageView.getLayoutParams();
                //layoutParams.topMargin = UIUtils.px2dip(50);
                //holder.statusImageView.setLayoutParams(layoutParams);
                break;

            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mBS == null ? 0 : mBS.size();
    }

    public void setData(List<MyTruckVO> data) {
        this.mBS = data;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_truck_truckname_tv)
        TextView nameTextView;
        @BindView(R.id.my_truck_edit_iv)
        ImageView editImageView;
        @BindView(R.id.my_truck_next_iv)
        ImageView nextImageView;
        @BindView(R.id.my_truck_del_iv)
        ImageView delImageView;
        @BindView(R.id.my_truck_middle_line_v)
        View lineView;
        @BindView(R.id.my_truck_content_tv)
        TextView contentTextView;
        @BindView(R.id.my_truck_status_iv)
        ImageView statusImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
