
package com.xinze.xinze.module.order.adapter;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xinze.xinze.R;
import com.xinze.xinze.utils.GlideRoundTransform;

/**
 * 新建动态页横向的图片列表适配器
 * 
 * @author anjihang
 */
public class PostImgAdapter extends RecyclerView.Adapter<PostImgAdapter.ViewHolder> {

    private  Context mContext;
    private ArrayList<String> mData;


    private int mImageWidth;

    private Callback mCallback;

    public PostImgAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    /**
     * 设置图片宽度，图片显示为正方形
     */
    public void setImageWidth(int width) {
        mImageWidth = width;
    }

    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_post_image, null), mCallback);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
//        viewHolder.mImage.getSource().setImageFilePath(null);
        RequestOptions myOptions = new RequestOptions()
                .fitCenter()
                .transform(new GlideRoundTransform(mContext, 5));


        viewHolder.mImage.setImageResource(R.drawable.transparent);
        if(i == getItemCount() - 1) {
            viewHolder.mImage.setBackgroundResource(R.mipmap.ico_add_img);
            viewHolder.mDel.setVisibility(View.GONE);
        } else {
            viewHolder.mImage.setBackgroundResource(R.mipmap.default_img);
//            viewHolder.mImage.setImageBitmap(bitmap);
            Glide.with(mContext).load(mData.get(i)).apply(myOptions).into(viewHolder.mImage);
            viewHolder.mDel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mData != null) {
            count = mData.size();
        }
        return count+1;
    }

    public void removeItem(int position) {
        if(mData == null || mData.size() <= position) {
            return;
        }
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(String path, int position) {
        if(mData == null) mData = new ArrayList<String>();
        mData.add(path);
        notifyItemInserted(position);
    }
    
    public ArrayList<String> getData() {
        return mData;
    }

    /**
     * 更新数据
     * 
     * @param data user数组
     */
    public void updateData(ArrayList<String> data) {
        if (data == null){
            return;
        }
        mData = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private ImageView mDel;

        public ViewHolder(View view, final Callback callback) {
            super(view);
            mImage = (ImageView)view.findViewById(R.id.view_post_image_iv);
            mDel = (ImageView)view.findViewById(R.id.view_post_image_del);
            mImage.setLayoutParams(new RelativeLayout.LayoutParams(mImageWidth, mImageWidth));
            mDel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    if (mCallback != null) {
                        callback.onDeleteImage(position);
                    }
                }
            });

            mImage.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    if (position == getItemCount() - 1) {
                        if(mCallback != null) {
                            mCallback.onAddImage();
                        }
                    }
                }
            });
        }
    }

    public interface Callback {
        void onDeleteImage(int position);
        void onAddImage();
    }
    

}
