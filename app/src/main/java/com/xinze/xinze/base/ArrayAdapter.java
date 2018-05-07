
package com.xinze.xinze.base;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 所有Adapter的基类
 * 
 * @author douxiaonan
 * @param <T>
 */
public abstract class ArrayAdapter<T> extends BaseAdapter {
    protected Context mContext;

    protected ArrayList<T> mData;

    protected boolean mDataValid;
    

    public ArrayAdapter(Context context) {
        mContext = context;
        mDataValid = false;
    }

    /**
     * 分页更新页面 --- 注意内容不叠加
     * 
     * @param data 数据源
     */
    public void addAll(ArrayList<T> data) {
        if (data != null) {
            mDataValid = true;
            if (mData != null) {
                mData.clear();
                mData.addAll(data);
            } else {
                mData = data;
            }
            notifyDataSetChanged();
        } else {
            mDataValid = false;
            notifyDataSetInvalidated();
        }
    }

    /**
     * 更新数据,刷新界面
     * 
     * @param data 数据源
     */
    public void updateData(ArrayList<T> data) {
        if (data != null  && data.size() != 0) {
            mDataValid = true;
            mData = data;
            notifyDataSetChanged();
        } else {
            mDataValid = false;
            notifyDataSetInvalidated();
        }
    }

    public ArrayList<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        if (mDataValid && mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mDataValid && mData != null) {
            return mData.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mDataValid && mData != null) {
            return position;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (!mDataValid) {
            throw new IllegalStateException("this should only be called when the data is valid");
        }
        if (position < 0 || position >= mData.size()) {
            throw new IllegalStateException("couldn't get view at this position " + position);
        }
        T data = mData.get(position);
        View v;
        if (convertView == null) {
            v = newView(mContext, data, parent, getItemViewType(position));
        } else {
            v = convertView;
        }
        bindView(v, position, data);
        return v;
    }

    /**
     * 创建view
     * 
     * @param context 上下文
     * @param data 数据
     * @param parent 父控件
     * @param type itemViewType
     * @return 返回创建好的view
     */
    public abstract View newView(Context context, T data, ViewGroup parent, int type);

    /**
     * 绑定数据到view
     * 
     * @param view view
     * @param position 数据的下标
     * @param data 数据
     */
    public abstract void bindView(View view, int position, T data);
}
