package com.bibi.wisdom.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MyViewPager extends ViewPager {

    private boolean intercept=true;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return intercept;
//    }








}
