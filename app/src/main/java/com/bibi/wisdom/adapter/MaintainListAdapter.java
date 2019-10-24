package com.bibi.wisdom.adapter;

import android.content.Context;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.bean.MaintainListBean;
import com.bibi.wisdom.utils.DateUtils;

import java.util.Date;

/**
 * 历史，适配器
 */
public class MaintainListAdapter extends CommonRecyclerAdapter<MaintainListBean.UserContactListBean> {
    private ListenerWithPosition.OnClickWithPositionListener listener;


    public MaintainListAdapter(Context context) {
        super(context, null, R.layout.item_contract);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, MaintainListBean.UserContactListBean bean) {
        holder.setTextViewText(R.id.tv_name, bean.getName());
        holder.setTextViewText(R.id.tv_price, bean.getAddress());
        holder.setTextViewText(R.id.tv_phone, bean.getPhone());


//        holder.setOnClickListener(listener, R.id.ll_history);
    }
}
