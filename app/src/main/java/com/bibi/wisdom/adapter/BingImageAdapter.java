package com.bibi.wisdom.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.BingImageBean;


public class BingImageAdapter extends CommonRecyclerAdapter<BingImageBean> {
    private SparseIntArray array=new SparseIntArray();
    private ListenerWithPosition.OnClickWithPositionListener listener;

    public BingImageAdapter(Context context, ListenerWithPosition.OnClickWithPositionListener listener) {
        super(context, null, R.layout.item_movie);
        this.listener = listener;
    }

    @Override
    public void convert(final CommonRecyclerHolder holder, BingImageBean data) {
        holder.setTextViewText(R.id.tv_name,data.getTitle());

        holder.setOnClickListener(listener,R.id.iv_image);


        Glide.with(mContext)
                .load("https://cn.bing.com"+data.getUrl())
                .into((ImageView) holder.getView(R.id.iv_image));
    }
}
