package com.bibi.wisdom.adapter;

import android.content.Context;
import android.view.View;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.CityBean;

import java.util.List;

public class CityListAdapter extends CommonRecyclerAdapter<CityBean> {

    private ListenerWithPosition.OnClickWithPositionListener listener;

    public CityListAdapter(Context context) {
        super(context, null, R.layout.item_city_list);
    }

    public void setOnClickListener(ListenerWithPosition.OnClickWithPositionListener listener){
        this.listener = listener;
    }

    @Override
    public void convert(CommonRecyclerHolder holder, CityBean cityBean) {
        holder.setTextViewText(R.id.city_name, cityBean.getCity());
        holder.setOnClickListener(listener,R.id.item);
    }
}
