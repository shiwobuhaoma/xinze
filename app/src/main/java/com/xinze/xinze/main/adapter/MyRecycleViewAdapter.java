package com.xinze.xinze.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.main.bean.MyRecycleViewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<MyRecycleViewItem> mBS;
    private Context mContext;
    private View view;

    public MyRecycleViewAdapter(Context context, List<MyRecycleViewItem> mbs) {
        this.mContext = context;
        this.mBS = mbs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(
                mContext).inflate(R.layout.my_rv_item, parent,
                false);
        ViewHolder holder = new ViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MyRecycleViewItem myRecycleViewItem = mBS.get(position);
        holder.rvTitle.setText(myRecycleViewItem.getTitleResources());
        int icon = myRecycleViewItem.getIcon();
        if (icon != 0){
            Drawable drawable = mContext.getResources().getDrawable(icon);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/5, drawable.getIntrinsicHeight()/5);
            holder.rvTitle.setCompoundDrawables(drawable, null, null, null);
            holder.rvTitle.setCompoundDrawablePadding(20);
        }
        String rightText = myRecycleViewItem.getRightText();
        holder.rvMore.setText(rightText);
        if (myRecycleViewItem.isShowSpace()){
            holder.vSpace.setVisibility(View.VISIBLE);
        }else{
            holder.vSpace.setVisibility(View.GONE);
        }
        if (myRecycleViewItem.isShowBottomLine()){
            holder.vLine.setVisibility(View.VISIBLE);
        }else{
            holder.vLine.setVisibility(View.GONE);
        }
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mBS.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_title)
        TextView rvTitle;
        @BindView(R.id.rv_more)
        TextView rvMore;
        @BindView(R.id.v_space)
        View vSpace;
        @BindView(R.id.v_bottom_line)
        View vLine;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //点击事件回调
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    //自定义监听事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }


}
