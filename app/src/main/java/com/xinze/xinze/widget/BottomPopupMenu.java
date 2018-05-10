
package com.xinze.xinze.widget;

import java.util.ArrayList;


import com.xinze.xinze.R;
import com.xinze.xinze.base.ArrayAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author lxf
 * 底部滑出对话框
 */
public class BottomPopupMenu {

    protected Activity mContext;

    protected Dialog mPopupMenu;

    private View mView;

    private ListView mListView;

    private MenuAdapter mAdapter;

    private ArrayList<Menu> mMenuList;

    private MenuClickListener mMenuClickListener;

    public BottomPopupMenu(Activity context) {
        mContext = context;
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.bottom_popup_menu, null);
        mView.findViewById(R.id.layout_bottom_popup_menu_cancel)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupMenu.dismiss();
                    }
                });
        mListView = (ListView) mView.findViewById(R.id.layout_bottom_popup_menu_list);
        mAdapter = new MenuAdapter(mContext);
        mListView.setAdapter(mAdapter);

        mPopupMenu = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        mPopupMenu.setContentView(mView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        Window window = mPopupMenu.getWindow();

        window.getDecorView().setPadding(0, 0, 0, 0);
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

    public void setOnMenuClickListener(MenuClickListener listener) {
        mMenuClickListener = listener;
    }

    public void setCancelTextColor(int colorRes) {
        ((Button) mView.findViewById(R.id.layout_bottom_popup_menu_cancel)).setTextColor(colorRes);
    }

    public void addItem(int id, String title, int colorRes) {
        Menu menu = new Menu();
        menu.itemId = id;
        menu.itemTitle = title;
        menu.textColorRes = colorRes;
        if (mMenuList == null) {
            mMenuList = new ArrayList<Menu>();
        }
        mMenuList.add(menu);
    }

    public void addItem(int id, String title) {
        Menu menu = new Menu();
        menu.itemId = id;
        menu.itemTitle = title;
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
            if (mMenuList.get(i).itemId == id) {
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
        public String itemTitle;

        public int itemId;

        public int textColorRes;
    }

    public interface MenuClickListener {
        public void onMenuItemClick(View itemView, int itemId);
    }

    /**
     * listview适配
     */
    public class MenuAdapter extends ArrayAdapter<Menu> {

        public MenuAdapter(Context context) {
            super(context);
        }

        @SuppressLint("InflateParams")
        @Override
        public View newView(Context context, Menu data, ViewGroup parent, int type) {
            return LayoutInflater.from(context).inflate(R.layout.bottom_item_menu, null);
        }

        @Override
        public void bindView(View view, final int position, Menu data) {
            ((TextView) view.findViewById(R.id.menu_title)).setText(data.itemTitle);
            if (data.textColorRes != 0) {
                ((TextView) view.findViewById(R.id.menu_title)).setTextColor(data.textColorRes);
            }
            view.findViewById(R.id.menu_title).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mMenuList == null || mMenuList.size() <= position) {
                        return;
                    }
                    if (mMenuClickListener != null) {
                        mMenuClickListener.onMenuItemClick(v, mMenuList.get(position).itemId);
                    }
                    dismiss();
                }

            });
        }

    }

}
