package com.xinze.xinze.module.regular.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xinze.xinze.R;
import com.xinze.xinze.module.regular.module.Menu;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 * RecyclerView适配
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements View.OnClickListener {
    private List<Menu> mData;
    private Context mContext;
    private View view;

    public MenuAdapter(Context mContext) {

        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(
                mContext).inflate(R.layout.top_item_menu, parent,
                false);
        MenuAdapter.ViewHolder holder = new MenuAdapter.ViewHolder(view);

        //给布局设置点击和长点击监听
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        Menu item = mData.get(position);
        String from = item.getFrom();
        String to = item.getTo();
        boolean isShowDrawable = item.isShowDrawable();
        view.setTag(position);
        if (position != 0 && !isShowDrawable) {
            holder.start.setText(from);
            holder.end.setText(to);
            holder.middle.setVisibility(View.VISIBLE);
            holder.end.setVisibility(View.VISIBLE);
        } else if (position == 0) {
            LinearLayout.LayoutParams paramsWeight = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsWeight.weight = 0;
            paramsWeight.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            holder.start.setLayoutParams(paramsWeight);
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ruglar_selected);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            holder.start.setText(from);
            holder.start.setTextColor(mContext.getResources().getColor(R.color.themeOrange));
            holder.start.setCompoundDrawables(null, null, drawable, null);
            holder.start.setCompoundDrawablePadding(15);
            holder.middle.setVisibility(View.GONE);
            holder.end.setVisibility(View.GONE);
        } else {
            holder.start.setText(from);
            holder.middle.setVisibility(View.GONE);
            holder.end.setVisibility(View.GONE);
        }
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onMenuItemClick(v, (int) v.getTag());
        }
    }
    public void addItem(int id, String from, String to, boolean isShowDrawable) {
        Menu menu = new Menu();
        menu.setFrom(from);
        menu.setId(id);
        menu.setTo(to);
        menu.setShowDrawable(isShowDrawable);
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(menu);
    }


    public void removeItem(int id) {
        if (mData == null) {
            return;
        }
        int count = mData.size();
        for (int i = 0; i < count; i++) {
            if (mData.get(i).getId() == id) {
                mData.remove(i);
                return;
            }
        }
    }

    public void clearMenu() {
        if (mData == null) {
            return;
        }
        mData.clear();
    }

    private void updateData(ArrayList<Menu> data) {
        if (data != null && data.size() != 0) {
            mData = data;
            notifyDataSetChanged();
        } else {
            notifyDataSetChanged();
        }
    }

    public interface MenuClickListener {
        void onMenuItemClick(View itemView, int position);
    }

    private MenuClickListener mOnItemClickListener;

    public void setOnItemClickListener(MenuClickListener listener) {
        mOnItemClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.top_menu_ll)
        LinearLayout topMenu;
        @BindView(R.id.top_menu_start)
        TextView start;
        @BindView(R.id.top_menu_middle)
        ImageView middle;
        @BindView(R.id.top_menu_end)
        TextView end;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }
}
