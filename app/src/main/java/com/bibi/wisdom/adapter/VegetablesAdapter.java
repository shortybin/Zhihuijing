package com.bibi.wisdom.adapter;

import android.content.Context;

import com.bibi.wisdom.R;

import java.util.List;

public class VegetablesAdapter extends CommonRecyclerAdapter<String> {
    public VegetablesAdapter(Context context) {
        super(context, null, R.layout.vegetables_item);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, String s) {

    }
}
