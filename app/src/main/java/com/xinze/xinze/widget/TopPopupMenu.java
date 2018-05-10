
package com.xinze.xinze.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinze.xinze.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 * 顶部滑出对话框
 */
public class TopPopupMenu {

    protected Activity mContext;

    protected Dialog mPopupMenu;

    private View mView;

    private RecyclerView mListView;

    private MenuAdapter mAdapter;

    private ArrayList<Menu> mMenuList;

    private MenuClickListener mMenuClickListener;

    private float y = 0;
    private FrameLayout mMenuFl;

    public TopPopupMenu(Activity context,float Y) {
        mContext = context;
        y = Y;
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.top_popup_menu, null);

        mMenuFl = (FrameLayout) mView.findViewById(R.id.top_menu_fl);
        LayoutParams layoutParams = mMenuFl.getLayoutParams();
        layoutParams.height = (int)y;
        mMenuFl.setLayoutParams(layoutParams);
        mListView = (RecyclerView) mView.findViewById(R.id.layout_top_popup_menu_list);
        mListView.setLayoutManager(new LinearLayoutManager(mContext));
        mListView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new MenuAdapter(mContext);
        mListView.setAdapter(mAdapter);

        mPopupMenu = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        mPopupMenu.setContentView(mView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        Window window = mPopupMenu.getWindow();
        window.setGravity(Gravity.TOP);
        window.getDecorView().setPadding(0, 0, 0, 0);
//        window.setWindowAnimations(R.style.top_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        wl.width = LayoutParams.MATCH_PARENT;
        wl.height = LayoutParams.WRAP_CONTENT;
        mPopupMenu.onWindowAttributesChanged(wl);
        mPopupMenu.setCanceledOnTouchOutside(false);
    }

    public void setOnMenuClickListener(MenuClickListener listener) {
        mMenuClickListener = listener;
    }


    public void addItem(int id, String from, String to, boolean isShowDrawable) {
        Menu menu = new Menu();
        menu.from = from;
        menu.id = id;
        menu.to = to;
        menu.isShowDrawable = isShowDrawable;
        if (mMenuList == null) {
            mMenuList = new ArrayList<Menu>();
        }
        mMenuList.add(menu);
    }


    public void removeItem(int id) {
        if (mMenuList == null) {
            return;
        }
        int count = mMenuList.size();
        for (int i = 0; i < count; i++) {
            if (mMenuList.get(i).id == id) {
                mMenuList.remove(i);
                return;
            }
        }
    }

    public void clearMenu() {
        if (mMenuList == null) {
            return;
        }
        mMenuList.clear();
    }

    public void showMenu() {
        mAdapter.updateData(mMenuList);
        mPopupMenu.show();
    }

    public void dismiss() {
        if (mPopupMenu == null) {
            return;
        }
        mPopupMenu.dismiss();
    }

    public boolean isShowing() {
        if (mPopupMenu == null)
            return false;
        return mPopupMenu.isShowing();
    }

    public class Menu {
        public int id;
        public String from;
        public String to;
        public boolean isShowDrawable;

    }

    public interface MenuClickListener {
         void onMenuItemClick(View itemView, int position);
    }
    private MenuClickListener mOnItemClickListener;

    public void setOnItemClickListener(MenuClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
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
            ViewHolder holder = new ViewHolder(view);

            //给布局设置点击和长点击监听
            view.setOnClickListener(this);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
            Menu item = mData.get(position);
            String from = item.from;
            String to = item.to;
            boolean isShowDrawable = item.isShowDrawable;
            view.setTag(position);
            if (position != 0 && !isShowDrawable){
                holder.start.setText(from);
                holder.end.setText(to);
                holder.middle.setVisibility(View.VISIBLE);
                holder.end.setVisibility(View.VISIBLE);
            }else if(position == 0){
                LinearLayout.LayoutParams paramsWeight = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsWeight.weight = 0;
                paramsWeight.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                holder.start.setLayoutParams(paramsWeight);
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ruglar_line_up);
                drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                holder.start.setText(from);
                holder.start.setTextColor(mContext.getResources().getColor(R.color.themeOrange));
                holder.start.setCompoundDrawables(null,null,drawable,null);
                holder.start.setCompoundDrawablePadding(15);
                holder.middle.setVisibility(View.GONE);
                holder.end.setVisibility(View.GONE);
            }else{
                holder.start.setText(from);
                holder.middle.setVisibility(View.GONE);
                holder.end.setVisibility(View.GONE);
            }
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

        private void updateData(ArrayList<Menu> data) {
            if (data != null && data.size() != 0) {
                mData = data;
                notifyDataSetChanged();
            } else {
                notifyDataSetChanged();
            }
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

}
