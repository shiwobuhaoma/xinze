package com.xinze.xinze.module.drivers.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.drivers.view.MyDriverActivity;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.utils.DialogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author feibai
 * @date 2018/5/14
 * desc:MyDriverRecycleViewAdapter
 */
public class MyDriverRecycleViewAdapter extends RecyclerView.Adapter<MyDriverRecycleViewAdapter.ViewHolder> {

    private List<TruckownerDriverVO> mBS;
    private MyDriverActivity mActivity;
    private View mView;
    private GestureDetector gestureDetector;
    private ViewHolder mCurrentViewHolder;

    public MyDriverRecycleViewAdapter(MyDriverActivity myDriverActivity) {
        this.mActivity = myDriverActivity;
        // 初始化手势滑动检测器
        initGestureDetector();
    }

    /**
     * @author feibai
     * @time 2018/5/19  18:21
     * @desc
     */
    private void initGestureDetector() {
        gestureDetector = new GestureDetector(mActivity, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //监听手势的移动
                if (e1.getX() - e2.getX() > 0) {
                    //左滑
                    if (mCurrentViewHolder != null) {
                        mCurrentViewHolder.delButton.setVisibility(View.VISIBLE);
                    }
                }

                if (e1.getX() - e2.getX() < 0) {
                    //右滑
                    if (mCurrentViewHolder != null) {
                        mCurrentViewHolder.delButton.setVisibility(View.GONE);
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mActivity).inflate(R.layout.my_driver_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(mView);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // 获取数据
        final TruckownerDriverVO truckownerDriver = mBS.get(position);
        String driverName = truckownerDriver.getDriverName();
        final String driverMobile = truckownerDriver.getDriverMobile();
        final String itemId = truckownerDriver.getId();
        //TODO 司机头像待处理暂用默认头像
        String driverPhotoUrl = truckownerDriver.getDriverPhoto();
        // 展示数据
        holder.nameTextView.setText(driverName);
        holder.phoneTextView.setText(driverMobile);


        // 给电话图片绑定点击事件
        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + driverMobile));
                mActivity.startActivity(intent);
            }
        });


        //给RL绑定左右滑动事件
        new GestureDetector(mActivity, new GestureDetector.SimpleOnGestureListener());
        holder.itemRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mCurrentViewHolder = holder;
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        // 给删除btn绑定点击事件
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showCommonDialog(mActivity, "确定要删除吗?", new DialogUtil.ChoiceClickListener() {
                    @Override
                    public void onClickSureView(Object data) {
                        mActivity.getmPresenter().delMyDriver(itemId);
                    }

                    @Override
                    public void onClickCancelView(Object data) {
                    }
                });

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
        @BindView(R.id.my_driver_avatar_iv)
        ImageView avatarImageView;
        @BindView(R.id.my_driver_name_tv)
        TextView nameTextView;
        @BindView(R.id.my_driver_phone_tv)
        TextView phoneTextView;
        @BindView(R.id.my_driver_call_iv)
        ImageView callImageView;
        @BindView(R.id.my_driver_del_bt)
        Button delButton;
        @BindView(R.id.my_driver_rl)
        RelativeLayout itemRelativeLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
