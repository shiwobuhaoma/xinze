package com.xinze.xinze.module.select;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinze.xinze.R;
import com.xinze.xinze.module.transport.module.Car;

import java.util.List;

public class SelectCarAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private  Context mContext;
    private List<Car> mCarList;
    public SelectCarAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_rv_item, parent, false);
        view.setOnClickListener(this);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCarList == null ? 0 : mCarList.size();
    }

    public void setData(List<Car> carList){
        mCarList = carList;
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {

    }
}
