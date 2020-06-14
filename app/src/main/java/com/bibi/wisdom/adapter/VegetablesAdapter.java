package com.bibi.wisdom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.VegetablesBean;

public class VegetablesAdapter extends CommonRecyclerAdapter<VegetablesBean> {

    private final Context context;
    private String address;

    public VegetablesAdapter(Context context) {
        super(context, null, R.layout.vegetables_item);
        this.context = context;
    }

    public void setAddress(String address){
        this.address = address;
        notifyDataSetChanged();
    }

    @Override
    public void convert(CommonRecyclerHolder holder, VegetablesBean vegetablesBean) {
        holder.setTextViewText(R.id.address_text,address);
        holder.setTextViewText(R.id.class_text,vegetablesBean.getProductName());
        holder.setTextViewText(R.id.low_text,getPrice(vegetablesBean.getMinPrice())+"/斤");
        holder.setTextViewText(R.id.height_text,getPrice(vegetablesBean.getMaxPrice())+"/斤");
        holder.setTextViewText(R.id.range_text,getPrice(vegetablesBean.getUpRange())+"/斤");

        View view = holder.getView(R.id.item);
        if (holder.getAdapterPosition()%2==0){
            view.setBackgroundColor(Color.parseColor("#86c7e1"));
        }else{
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    private double getPrice(String price){
        if (TextUtils.isEmpty(price)){
            return 0;
        }
        Double aDouble = Double.valueOf(price);
        return aDouble/100;
    }
}
