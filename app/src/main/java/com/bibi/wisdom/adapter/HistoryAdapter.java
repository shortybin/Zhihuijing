package com.bibi.wisdom.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;


import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 历史，适配器
 */
public class HistoryAdapter extends CommonRecyclerAdapter<HistoryBean.ListBean> {
    private ListenerWithPosition.OnClickWithPositionListener listener;


    public HistoryAdapter(Context context) {
        super(context, null, R.layout.item_history);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, HistoryBean.ListBean bean) {
        holder.setTextViewText(R.id.tv_name, "设备名称："+bean.getProductName());
        holder.setTextViewText(R.id.tv_price, "计价单位："+bean.getPrice()+"/小时");

        String startTime=DateUtils.formatTimeToStringYearHourMinute(new Date(bean.getCreateTime()));

        holder.setTextViewText(R.id.tv_start_time, "开始时间："+ startTime);
        if(bean.getUpdateTime()>0){
            holder.setTextViewText(R.id.tv_end_time, "结束时间："+ DateUtils.formatTimeToStringYearHourMinute(new Date(bean.getUpdateTime())));
        }


        holder.setTextViewText(R.id.tv_duration, "使用时长："+bean.getTimeLongFormat()+"小时");
        holder.setTextViewText(R.id.tv_cost, "交易金额："+bean.getSumPriceStr()+"元");

//        holder.setOnClickListener(listener, R.id.ll_history);
    }
}
