package com.xinze.xinze.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xinze.xinze.R;
import com.xinze.xinze.http.RetrofitFactory;
import com.xinze.xinze.http.config.HeaderConfig;
import com.xinze.xinze.http.entity.BaseEntity;
import com.xinze.xinze.http.observer.BaseObserver;
import com.xinze.xinze.module.find.view.IFindGoodsView;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.widget.adapter.AddressAdapter;
import com.xinze.xinze.widget.bean.Address;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 普通货单的地址选择控件
 *
 * @author lxf
 */
public class SelectAddressView extends LinearLayout implements AddressAdapter.OnItemClickListener {

    private Context mContext;
    private Unbinder mUnbinder;
    @BindView(R.id.province)
    RecyclerView province;
    @BindView(R.id.city)
    RecyclerView city;
    @BindView(R.id.country)
    RecyclerView country;

    private String extId = "0";

    private AddressAdapter provinceAdapter;
    private AddressAdapter cityAdapter;
    private AddressAdapter countryAdapter;

    private List<Address> provinceData;
    private List<Address> cityData;
    private List<Address> countryData;

    private int PROVINCE = 0;
    private int CITY = 1;
    private int COUNTRY = 2;

    private int giveWhereData = 0;
    private View view;

    public SelectAddressView(Context context) {
        this(context, null);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        view = LayoutInflater.from(context).inflate(R.layout.select_address_view, this);

        mUnbinder = ButterKnife.bind(view);

        provinceAdapter = new AddressAdapter(mContext);
        cityAdapter = new AddressAdapter(mContext);
        countryAdapter = new AddressAdapter(mContext);

        province.setLayoutManager(new LinearLayoutManager(mContext));
        city.setLayoutManager(new LinearLayoutManager(mContext));
        country.setLayoutManager(new LinearLayoutManager(mContext));

        province.setAdapter(provinceAdapter);
        city.setAdapter(cityAdapter);
        country.setAdapter(countryAdapter);

        provinceAdapter.setmOnItemClickListener(this, "PROVINCE");
        cityAdapter.setmOnItemClickListener(this, "CITY");
        countryAdapter.setmOnItemClickListener(this, "COUNTRY");

        initData(extId);
    }

    private void initData(String extId) {
        HashMap<String, String> headers = HeaderConfig.getHeaders();
        RetrofitFactory.getInstence().Api().getAreaListByParentIdForSearch(headers, extId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Address>>() {
                    @Override
                    protected void onSuccees(BaseEntity<List<Address>> t) throws Exception {
                        if (t != null) {
                            if (t.isSuccess()) {
                                if (PROVINCE == giveWhereData) {
                                    provinceData = t.getData();
                                    provinceAdapter.setData(provinceData);

                                } else if (CITY == giveWhereData) {
                                    cityData = t.getData();
                                    cityAdapter.setData(cityData);
                                    city.setVisibility(VISIBLE);

                                } else {
                                    countryData = t.getData();
                                    countryAdapter.setData(countryData);
                                    country.setVisibility(VISIBLE);

                                }

                            }
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
    }

    @Override
    public void click(View view, int position, String mAddress) {
        switch (view.getId()) {
            case R.id.address_item:
                int color = getResources().getColor(R.color.white);
                if ("PROVINCE".equals(mAddress)) {
                    giveWhereData = 1;
                    Address address = provinceData.get(position);
                    address.setBackgroundColor(color);
                    provinceAdapter.notifyItemChanged(position);
                    String id = address.getId();
                    String name = address.getName();
                    initData(id);
                    countryAdapter.clearData();
                } else if ("CITY".equals(mAddress)) {
                    giveWhereData = 2;
                    Address address = cityData.get(position);
                    address.setBackgroundColor(color);
                    cityAdapter.notifyItemChanged(position);
                    String id = address.getId();
                    String name = address.getName();
                    initData(id);
                } else if ("COUNTRY".equals(mAddress)) {

                    Address address = countryData.get(position);
                    address.setBackgroundColor(color);
                    countryAdapter.notifyItemChanged(position);
                    String name = address.getName();
                    String id = address.getId();
                    mOnSelectAddressListener.selectAddress(name, id);
                    setViewGone();
                }

                break;
            default:
                break;
        }
    }

    public void setViewVisible() {
        view.setVisibility(VISIBLE);
    }

    public void setViewGone() {
        view.setVisibility(GONE);
    }

    public void setmOnSelectAddressListener(OnSelectAddressListener mOnSelectAddressListener) {
        this.mOnSelectAddressListener = mOnSelectAddressListener;
    }

    public OnSelectAddressListener mOnSelectAddressListener;


    public interface OnSelectAddressListener {
        void selectAddress(String name, String id);
    }
}
