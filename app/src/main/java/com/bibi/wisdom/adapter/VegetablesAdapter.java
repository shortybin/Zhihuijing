package com.bibi.wisdom.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.VegetablesBean;

public class VegetablesAdapter extends CommonRecyclerAdapter<VegetablesBean> {
    public VegetablesAdapter(Context context) {
        super(context, null, R.layout.vegetables_item);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, VegetablesBean vegetablesBean) {
        holder.setTextViewText(R.id.address_text,vegetablesBean.getAddressCode());
        holder.setTextViewText(R.id.class_text,vegetablesBean.getProductName());
        holder.setTextViewText(R.id.low_text,getPrice(vegetablesBean.getMinPrice())+"/斤");
        holder.setTextViewText(R.id.height_text,getPrice(vegetablesBean.getMaxPrice())+"/斤");
        holder.setTextViewText(R.id.range_text,getPrice(vegetablesBean.getUpRange())+"/斤");
    }

    private double getPrice(String price){
        if (TextUtils.isEmpty(price)){
            return 0;
        }
        Double aDouble = Double.valueOf(price);
        return aDouble/100;
    }
}
