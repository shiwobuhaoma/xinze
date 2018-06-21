package com.xinze.xinze.widget;

import android.content.Context;
import android.graphics.Color;
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
import com.xinze.xinze.widget.adapter.AddressAdapter;
import com.xinze.xinze.widget.bean.Address;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.PUT;

/**
 * 普通货单的地址选择控件
 *
 * @author lxf
 */
public class SelectAddressView extends LinearLayout implements AddressAdapter.OnItemClickListener {

    private Context mContext;

    @BindView(R.id.province)
    RecyclerView province;
    @BindView(R.id.city)
    RecyclerView city;
    @BindView(R.id.country)
    RecyclerView country;


    private AddressAdapter provinceAdapter;
    private AddressAdapter cityAdapter;
    private AddressAdapter countryAdapter;

    private List<Address> provinceData;
    private List<Address> cityData;
    private List<Address> countryData;

    private static int PROVINCE = 0;
    private static int CITY = 1;
    private static int COUNTRY = 2;

    private String provinceStr = "PROVINCE";
    private String cityStr = "CITY";
    private String countryStr = "COUNTRY";


    private int provincePosition = 0;
    private int countryPosition = 0;

    private int giveWhereData = 0;
    private View view;
    private int white;
    private int orange;
    private int black;
    private int gray;

    public SelectAddressView(Context context) {
        this(context, null);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectAddressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        white = Color.parseColor("#ffffff");
        orange = Color.parseColor("#FB9251");
        black = Color.parseColor("#333333");
        gray = Color.parseColor("#f5f5f5");
        view = LayoutInflater.from(context).inflate(R.layout.select_address_view, this);

        ButterKnife.bind(view);

        provinceAdapter = new AddressAdapter(mContext);
        cityAdapter = new AddressAdapter(mContext);
        countryAdapter = new AddressAdapter(mContext);

        province.setLayoutManager(new LinearLayoutManager(mContext));
        city.setLayoutManager(new LinearLayoutManager(mContext));
        country.setLayoutManager(new LinearLayoutManager(mContext));

        province.setAdapter(provinceAdapter);
        city.setAdapter(cityAdapter);
        country.setAdapter(countryAdapter);

        provinceAdapter.setmOnItemClickListener(this, provinceStr);
        cityAdapter.setmOnItemClickListener(this, cityStr);
        countryAdapter.setmOnItemClickListener(this, countryStr);
        String extId = "0";
        initData(extId);
    }

    private void initData(String extId) {
        RetrofitFactory.getInstence().Api().getAreaListByParentIdForSearch(extId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Address>>(mContext) {
                    @Override
                    protected void onSuccees(BaseEntity<List<Address>> t) throws Exception {
                        if (t != null) {
                            if (t.isSuccess()) {
                                if (PROVINCE == giveWhereData) {
                                    provinceData = t.getData();
                                    for (Address address : provinceData) {
                                        address.setBackgroundColor(mContext.getResources().getColor(R.color.gray_background));
                                        address.setTextColor(mContext.getResources().getColor(R.color.themeBlack));
                                    }
                                    provinceAdapter.setData(provinceData);

                                } else if (CITY == giveWhereData) {
                                    cityData = t.getData();
                                    for (Address address : cityData) {
                                        address.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                                        address.setTextColor(mContext.getResources().getColor(R.color.themeBlack));
                                    }
                                    cityAdapter.setData(cityData);
                                    city.setVisibility(VISIBLE);

                                } else if (COUNTRY == giveWhereData) {
                                    countryData = t.getData();
                                    for (Address address : countryData) {
                                        address.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                                        address.setTextColor(mContext.getResources().getColor(R.color.themeBlack));
                                    }
                                    countryAdapter.setData(countryData);
                                    country.setVisibility(VISIBLE);

                                }

                            }
                        }
                    }

                    @Override
                    protected void onFailure(String msg) throws Exception {

                    }
                });
    }

    @Override
    public void click(View view, int position, String mAddress) {
        switch (view.getId()) {
            case R.id.address_item:

                if (provinceStr.equals(mAddress)) {
                    provincePosition = position;
                    if (position == 0){
                        setViewGone();
                        Address address = provinceData.get(position);
                        mOnSelectAddressListener.selectAddress(address.getName(), address.getId());
                        return;
                    }
                    giveWhereData = 1;

                    for (int i = 0; i < provinceData.size(); i++) {
                        Address address = provinceData.get(i);
                        if (i == position) {
                            address.setBackgroundColor(white);
                            address.setTextColor(orange);
                        } else {
                            address.setBackgroundColor(gray);
                            address.setTextColor(black);
                        }
                    }
                    provinceAdapter.setData(provinceData);
                    Address address = provinceData.get(position);
                    String id = address.getId();
                    initData(id);
                    countryAdapter.clearData();
                } else if (cityStr.equals(mAddress)) {
                    giveWhereData = 2;
                    countryPosition = position;
                    if (position == 0){
                        setViewGone();
                        Address address = provinceData.get(provincePosition);
                        mOnSelectAddressListener.selectAddress(address.getName(), address.getId());
                        return;
                    }

                    for (int i = 0; i < cityData.size(); i++) {
                        Address address = cityData.get(i);
                        if (i == position) {
                            address.setTextColor(orange);
                        } else {
                            address.setTextColor(black);
                        }
                        address.setBackgroundColor(white);
                    }
                    cityAdapter.setData(cityData);
                    Address address = cityData.get(position);
                    String id = address.getId();
                    initData(id);
                } else if (countryStr.equals(mAddress)) {
                    if (position == 0){
                        setViewGone();
                        Address address = cityData.get(countryPosition);
                        mOnSelectAddressListener.selectAddress(address.getName(), address.getId());
                        return;
                    }

                    for (int i = 0; i < countryData.size(); i++) {
                        Address address = countryData.get(i);
                        if (i == position) {
                            address.setTextColor(orange);
                        } else {
                            address.setTextColor(black);
                        }
                        address.setBackgroundColor(white);
                    }
                    Address address = countryData.get(position);
                    countryAdapter.setData(countryData);
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

    public void clearState() {
        for (int i = 0;provinceData != null && i < provinceData.size(); i++) {
            Address address = provinceData.get(i);
            address.setBackgroundColor(gray);
            address.setTextColor(black);
        }
        provinceAdapter.setData(provinceData);
        for (int i = 0; cityData != null && i < cityData.size(); i++) {
            Address address = cityData.get(i);
            address.setTextColor(black);
            address.setBackgroundColor(white);
        }
        cityAdapter.setData(cityData);
        for (int i = 0; countryData != null &&i < countryData.size(); i++) {
            Address address = countryData.get(i);
            address.setTextColor(black);
            address.setBackgroundColor(white);
        }
        countryAdapter.setData(countryData);
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
        /**
         * 选择省市区
         *
         * @param name 区的名字
         * @param id   区的id
         */
        void selectAddress(String name, String id);
    }
}
