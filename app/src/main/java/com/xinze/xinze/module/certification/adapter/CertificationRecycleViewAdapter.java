package com.xinze.xinze.module.certification.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.module.certification.bean.CertificationRecycleViewItem;
import com.xinze.xinze.utils.GlideRoundTransform;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 * 认证页面适配器
 */
public class CertificationRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CertificationRecycleViewItem> mBS;
    private Context mContext;
    private View view;
    /**
     * 布局类型
     */
    private final int ITEM_TYPE_ONE = 1;
    private final int ITEM_TYPE_TWO = 2;

    public interface SaveEditListener {

        void saveEdit(int position, String string);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public CertificationRecycleViewAdapter(Context context, List<CertificationRecycleViewItem> mbs) {
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
                        mContext).inflate(R.layout.certification_rv_item, parent,
                        false);
                holder = new ViewHolder(view, mOnItemClickListener);
                break;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(
                        mContext).inflate(R.layout.certification_rv_item2, parent,
                        false);
                holder = new ViewHolder2(view, mOnItemClickListener);
                break;
            default:
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        CertificationRecycleViewItem certificationRecycleViewItem = mBS.get(position);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;

            String leftText = certificationRecycleViewItem.getLeftText();
            viewHolder.certificationTvName.setText(leftText);
            boolean showRightArrow = certificationRecycleViewItem.isShowRightArrow();
            if (showRightArrow) {
                viewHolder.certificationTvArrow.setVisibility(View.VISIBLE);
            } else {
                viewHolder.certificationTvArrow.setVisibility(View.GONE);
            }
            if (certificationRecycleViewItem.isShowBottomLine()) {
                viewHolder.certificationVLine.setVisibility(View.VISIBLE);
            } else {
                viewHolder.certificationVLine.setVisibility(View.GONE);
            }
            if (certificationRecycleViewItem.isShowSpace()) {
                viewHolder.certificationVSpace.setVisibility(View.VISIBLE);
            } else {
                viewHolder.certificationVSpace.setVisibility(View.GONE);
            }
            if (certificationRecycleViewItem.isShowMiddleText()) {
                viewHolder.certificationEt.setVisibility(View.VISIBLE);
                viewHolder.certificationEt.setHint(certificationRecycleViewItem.getMiddleHintText());
            } else {
                viewHolder.certificationEt.setVisibility(View.GONE);
            }
            if (viewHolder.certificationEt.getTag() instanceof TextWatcher) {
                viewHolder.certificationEt.removeTextChangedListener((TextWatcher) viewHolder.certificationEt.getTag());
            }
            //添加editText的监听事件
            TextSwitcher watcher = new TextSwitcher(viewHolder);
            viewHolder.certificationEt.addTextChangedListener(watcher);
            viewHolder.certificationEt.setTag(watcher);
            if (position == 0) {
                viewHolder.certificationEt.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            }
            if (position == 1) {
                viewHolder.certificationEt.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_CLASS_TEXT);
            }
            viewHolder.certificationEt.setText(certificationRecycleViewItem.getContent());
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 viewHolder = (ViewHolder2) holder;
            RequestOptions myOptions = new RequestOptions()
                    .fitCenter()
                    .transform(new GlideRoundTransform(mContext, 5));
            if (certificationRecycleViewItem.getResoucesId() == R.id.certification_iv_id) {
                String leftBitmap = certificationRecycleViewItem.getLeftBitmap();
                if (leftBitmap != null) {

                    Glide.with(mContext).load(leftBitmap).apply(myOptions).into(viewHolder.certificationIvId);
                }

            } else {
                String rightBitmap = certificationRecycleViewItem.getRightBitmap();

                if (rightBitmap != null) {
                    Glide.with(mContext).load(rightBitmap).apply(myOptions).into(viewHolder.certificationIvDriverId);
                }

            }

        }


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


    //自定义监听事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onViewClick(View view);
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnRecyclerViewItemClickListener mOnItemClickListener;
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

        ViewHolder(View view, OnRecyclerViewItemClickListener onItemClickListener) {
            super(view);
            mOnItemClickListener = onItemClickListener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (int) v.getTag());
            }
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnRecyclerViewItemClickListener mOnItemClickListener;
        @BindView(R.id.certification_iv_id)
        ImageView certificationIvId;
        @BindView(R.id.certification_tv_id)
        TextView certificationTvId;
        @BindView(R.id.certification_iv_driver_id)
        ImageView certificationIvDriverId;
        @BindView(R.id.certification_tv_driver_id)
        TextView certificationTvDriverId;

        ViewHolder2(View view, OnRecyclerViewItemClickListener onItemClickListener) {
            super(view);
            mOnItemClickListener = onItemClickListener;
            ButterKnife.bind(this, view);
            certificationIvId.setOnClickListener(this);
            certificationIvDriverId.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onViewClick(v);
            }
        }
    }

    //自定义EditText的监听类
    class TextSwitcher implements TextWatcher {

        private ViewHolder mHolder;

        private TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            SaveEditListener listener = (SaveEditListener) mContext;
            if (listener != null && mHolder != null && mHolder.itemView != null) {
                if (0 == (int) mHolder.itemView.getTag()) {
                    if (s == null) {
                        return;
                    } else if (TextUtils.isEmpty(s.toString())) {
                        RxToast.showToast("姓名不能为空");
                        return;
                    }
                } else if (1 == (int) mHolder.itemView.getTag()) {
                    if (s == null) {
                        return;
                    } else if (TextUtils.isEmpty(s.toString())) {
                        RxToast.showToast("身份证号码不能为空");
                        return;
                    }
                } else if (2 == (int) mHolder.itemView.getTag()) {
                    if (s == null) {
                        return;
                    } else if (TextUtils.isEmpty(s.toString())) {
                        RxToast.showToast("现居住地不能为空");
                        return;
                    }
                } else if (3 == (int) mHolder.itemView.getTag()) {
                    if (s == null) {
                        return;
                    } else if (TextUtils.isEmpty(s.toString())) {
                        RxToast.showToast("详细地址不能为空");
                        return;
                    }
                }
                mBS.get((int)mHolder.itemView.getTag()).setContent(s.toString());
                listener.saveEdit(Integer.parseInt(mHolder.itemView.getTag().toString()), s.toString());

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理


        }
    }

}
