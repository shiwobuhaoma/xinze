package com.xinze.xinze.module.line.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.regular.modle.Route;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineListAdapter extends RecyclerView.Adapter<LineListAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<Route> mRoutes ;
    private OnClickListener mOnClickListener;

    public LineListAdapter(Context context) {
        this.mContext = context;
        mRoutes = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.line_list_item, parent, false);
        return   new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        Route route = mRoutes.get(position);
        holder.lineStart.setText(route.getFrom_area_name());
        holder.lineEnd.setText(route.getTo_area_name());
    }

    @Override
    public int getItemCount() {
        return mRoutes == null ? 0 : mRoutes.size();
    }

    @Override
    public void onClick(View v) {
        mOnClickListener.onClick(v,(int)v.getTag());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.line_start)
        TextView lineStart;
        @BindView(R.id.line_middle)
        ImageView lineMiddle;
        @BindView(R.id.line_end)
        TextView lineEnd;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickListener{
        void onClick(View view,int position);
    }

    public void setOnItemClickListener(OnClickListener onClickListener ){
        this.mOnClickListener = onClickListener;
    }

    public void setData(List<Route> routes){
        this.mRoutes = routes;
        notifyDataSetChanged();
    }
}
