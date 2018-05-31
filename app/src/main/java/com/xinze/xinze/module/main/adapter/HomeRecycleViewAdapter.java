package com.xinze.xinze.module.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.main.bean.HomeRecycleViewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 * 首页适配器
 */
public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    /**
     *布局类型
     */
    private final int ITEM_TYPE_ONE = 1;
    private final int ITEM_TYPE_TWO = 2;

    private List<HomeRecycleViewItem> mBS;
    private Context mContext;
    private View view;

    public HomeRecycleViewAdapter(Context context, List<HomeRecycleViewItem> mbs) {
        this.mContext = context;
        this.mBS = mbs;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ITEM_TYPE_ONE:
                view = LayoutInflater.from(
                        mContext).inflate(R.layout.home_rv_item, parent,
                        false);
                holder = new ViewHolder(view);
                break;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(
                        mContext).inflate(R.layout.home_rv_item2, parent,
                        false);
                holder = new ViewHolder2(view);
                break;
            default:
                break;
        }
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);

        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        HomeRecycleViewItem homeRecycleViewItem = mBS.get(position);

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            String rightText = homeRecycleViewItem.getRightText();
            if (!TextUtils.isEmpty(rightText)) {
                viewHolder.rvRightText.setText(rightText);
            }
            viewHolder.rvTitle.setText(homeRecycleViewItem.getTitleResources());
            int icon = homeRecycleViewItem.getIcon();
            if (icon != 0) {
                Drawable drawable = mContext.getResources().getDrawable(icon);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                viewHolder.rvTitle.setCompoundDrawables(drawable, null, null, null);
                viewHolder.rvTitle.setCompoundDrawablePadding(20);
            }
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 viewHolder = (ViewHolder2) holder;
            viewHolder.rvTitle2.setText(homeRecycleViewItem.getTitle());
        }

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mBS.size() - 1) {
            return ITEM_TYPE_TWO;
        } else {
            return ITEM_TYPE_ONE;
        }
    }

    @Override
    public int getItemCount() {
        return mBS.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_rv_title)
        TextView rvTitle;
        @BindView(R.id.home_rv_right_text)
        TextView rvRightText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.home_rv_title2)
        TextView rvTitle2;

        ViewHolder2(View view) {
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


    public void setData(List<HomeRecycleViewItem> data){
        mBS = data;
        notifyDataSetChanged();
    }

}
