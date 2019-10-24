package com.bibi.wisdom.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.utils.DateUtils;

import java.util.Date;

/**
 * 设备列表，适配器
 */
public class DeviceAdapter extends CommonRecyclerAdapter<DeviceListBean.UserproductlistBean> {
    private ListenerWithPosition.OnClickWithPositionListener listener;


    public DeviceAdapter(Context context,ListenerWithPosition.OnClickWithPositionListener listener) {
        super(context, null, R.layout.item_device);
        this.listener=listener;
    }

    @Override
    public void convert(CommonRecyclerHolder holder, DeviceListBean.UserproductlistBean bean) {
        holder.setTextViewText(R.id.tv_name, "设备名称："+bean.getProductName());
        holder.setTextViewText(R.id.tv_price, "设备编号：："+bean.getProductCode());



        holder.setOnClickListener(listener, R.id.ll_device);
    }
}
