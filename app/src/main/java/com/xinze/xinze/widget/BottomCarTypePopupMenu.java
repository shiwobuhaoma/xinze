package com.xinze.xinze.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 * 底部滑出对话框
 */
public class BottomCarTypePopupMenu {

    protected Activity mContext;

    private Dialog mPopupMenu;

    private String[] carType = {"自卸", "单车", "油罐车", "集装箱", "冷藏", "全封闭", "二拖三", "班车", "厢式车", "半封闭", "前四后八", "危货车", "半挂车"};

    private MenuAdapter mAdapter;

    private ArrayList<Menu> mMenuList;

    private MenuClickListener mMenuClickListener;
    private int black;
    private int white;
    private Drawable orangeDrawable;
    private Drawable whiteDrawable;

    public BottomCarTypePopupMenu(Activity context) {
        mContext = context;
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.bottom_car_type_popup_menu, null);
        mView.findViewById(R.id.cancel_take_up)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupMenu.dismiss();
                    }
                });
        RecyclerView mListView = (RecyclerView) mView.findViewById(R.id.bottom_car_type_popup_menu_list);
        mListView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        List<String> pros = Arrays.asList(carType);
        black = mContext.getResources().getColor(R.color.black);
        white = mContext.getResources().getColor(R.color.white);
        orangeDrawable = mContext.getResources().getDrawable(R.drawable.circle_orange_button);
        whiteDrawable = mContext.getResources().getDrawable(R.drawable.button_stroke_background);
        for (String s : pros) {
            addItem(s, black, orangeDrawable);
        }
        mAdapter = new MenuAdapter(mContext, mMenuList);
        mListView.setAdapter(mAdapter);

        mPopupMenu = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        mPopupMenu.setContentView(mView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        Window window = mPopupMenu.getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            decorView.setPadding(0, 0, 0, 0);
            window.setWindowAnimations(R.style.main_menu_animstyle);
            WindowManager.LayoutParams wl = window.getAttributes();
            Point outSize = new Point();
            mContext.getWindowManager().getDefaultDisplay().getSize(outSize);
            wl.x = 0;
            wl.y = outSize.y;
            wl.width = LayoutParams.MATCH_PARENT;
            wl.height = LayoutParams.WRAP_CONTENT;
            mPopupMenu.onWindowAttributesChanged(wl);
            mPopupMenu.setCanceledOnTouchOutside(true);
        }
    }

    public void setOnMenuClickListener(MenuClickListener listener) {
        mMenuClickListener = listener;
    }


    public void addItem(String title, int textColorRes, Drawable backgroundDrawableRes) {
        Menu menu = new Menu();
        menu.itemTitle = title;
        menu.textColorRes = textColorRes;
        menu.backgroundDrawableRes = backgroundDrawableRes;
        if (mMenuList == null) {
            mMenuList = new ArrayList<Menu>();
        }
        mMenuList.add(menu);
    }

    public void addItem(String title) {
        Menu menu = new Menu();
        menu.itemTitle = title;
        if (mMenuList == null) {
            mMenuList = new ArrayList<Menu>();
        }
        mMenuList.add(menu);
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

    private void dismiss() {
        if (mPopupMenu == null) {
            return;
        }

        for (Menu menu : mMenuList) {
            menu.textColorRes = black;
            menu.backgroundDrawableRes = whiteDrawable;
        }
        mAdapter.updateData(mMenuList);
        mPopupMenu.dismiss();
    }

    public boolean isShowing() {
        return mPopupMenu != null && mPopupMenu.isShowing();
    }

    public class Menu {
        public String itemTitle;
        public int textColorRes;
        public Drawable backgroundDrawableRes;
    }

    public interface MenuClickListener {
        void onMenuItemClick(View itemView, String carType);
    }

    /**
     * RecyclerView适配
     */
    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
        private List<Menu> menuList;
        private Context mContext;

        private MenuAdapter(Context context, List<Menu> mMenuList) {
            mContext = context;
            menuList = mMenuList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.bottom_car_type_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Menu menu = menuList.get(position);
            holder.menuTitle.setText(menu.itemTitle);
            holder.menuTitle.setTextColor(menu.textColorRes);
            if (menu.textColorRes == black) {
                holder.menuRl.setBackground(whiteDrawable);
            } else {
                holder.menuRl.setBackground(orangeDrawable);
            }
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return menuList == null ? 0 : menuList.size();
        }

        private void updateData(List<Menu> mMenuList) {
            menuList = mMenuList;
            notifyDataSetChanged();
        }


        class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
            @BindView(R.id.car_type_title)
            TextView menuTitle;
            @BindView(R.id.car_type_rl)
            RelativeLayout menuRl;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                menuTitle.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = (int) itemView.getTag();
                if (menuList == null || menuList.size() <= position) {
                    return;
                }
                if (mMenuClickListener != null) {
                    Menu element = menuList.get(position);

                    mMenuClickListener.onMenuItemClick(v, element.itemTitle);

                    element.textColorRes = white;
                    element.backgroundDrawableRes = orangeDrawable;
                    menuList.set(position, element);
                    updateData(menuList);
                }
                dismiss();
            }
        }

    }

}
