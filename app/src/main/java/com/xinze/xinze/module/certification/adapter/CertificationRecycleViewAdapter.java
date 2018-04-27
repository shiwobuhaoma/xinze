package com.xinze.xinze.module.certification.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.certification.bean.CertificationRecycleViewItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 *         认证页面适配器
 */
public class CertificationRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<CertificationRecycleViewItem> mBS;
    private Context mContext;
    private View view;
    /**
     * 布局类型
     */
    private final int ITEM_TYPE_ONE = 1;
    private final int ITEM_TYPE_TWO = 2;

    public CertificationRecycleViewAdapter(Context context, List<CertificationRecycleViewItem> mbs) {
        this.mContext = context;
        this.mBS = mbs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ITEM_TYPE_ONE:
                view = LayoutInflater.from(
                        mContext).inflate(R.layout.certification_rv_item, parent,
                        false);
                holder = new ViewHolder(view);
                break;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(
                        mContext).inflate(R.layout.certification_rv_item2, parent,
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
        CertificationRecycleViewItem certificationRecycleViewItem = mBS.get(position);
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;

            String leftText = certificationRecycleViewItem.getLeftText();
            viewHolder.certificationTvName.setText(leftText);

            boolean showRightArrow = certificationRecycleViewItem.isShowRightArrow();
            if (showRightArrow){
                viewHolder.certificationTvArrow.setVisibility(View.VISIBLE);
            }else{
                viewHolder.certificationTvArrow.setVisibility(View.GONE);
            }

            if (certificationRecycleViewItem.isShowSpace()) {
                viewHolder.certificationVSpace.setVisibility(View.VISIBLE);
            } else {
                viewHolder.certificationVSpace.setVisibility(View.GONE);
            }
            if (certificationRecycleViewItem.isShowBottomLine()) {
                viewHolder.certificationVLine.setVisibility(View.VISIBLE);
            } else {
                viewHolder.certificationVLine.setVisibility(View.GONE);
            }

            if (certificationRecycleViewItem.isShowMiddleText()){
                viewHolder.certificationEt.setVisibility(View.VISIBLE);
                viewHolder.certificationEt.setHint(certificationRecycleViewItem.getMiddleHintText());
            }else{
                viewHolder.certificationEt.setVisibility(View.GONE);
            }
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 viewHolder = (ViewHolder2) holder;

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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.certification_tv_name)
        TextView certificationTvName;
        @BindView(R.id.certification_et)
        EditText certificationEt;
        @BindView(R.id.certification_arrow)
        ImageView certificationTvArrow;
        @BindView(R.id.certification_v_line)
        View certificationVLine;
        @BindView(R.id.certification_v_space)
        View certificationVSpace;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.certification_iv_id)
        ImageView certificationIvId;
        @BindView(R.id.certification_tv_id)
        TextView certificationTvId;
        @BindView(R.id.certification_iv_driver_id)
        ImageView certificationIvDriverId;
        @BindView(R.id.certification_tv_driver_id)
        TextView certificationTvDriverId;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
