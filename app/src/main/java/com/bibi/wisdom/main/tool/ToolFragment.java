package com.bibi.wisdom.main.tool;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.utils.ToastUtil;
import com.bigkoo.pickerview.TimePickerView;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxTimeTool;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 工具
 */

public class ToolFragment extends MVPBaseFragment<ToolContract.View, ToolPresenter> implements ToolContract.View {

    public static final int TYPE_START=111;
    public static final int TYPE_END=112;

    @BindView(R.id.tv_date_start)
    TextView tvDateStart;
    @BindView(R.id.tv_date_end)
    TextView tvDateEnd;
    @BindView(R.id.tv_price)
    EditText tvPrice;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.tv_cost)
    TextView tvCost;
    Unbinder unbinder;

    private Date startDate;
    private Date endDate;
    private String price;

    private int hours=0;

    private double totalCost;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_tool;
    }

    @Override
    protected void init() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_date_start, R.id.tv_date_end, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date_start:
                showPicker(TYPE_START);
                break;
            case R.id.tv_date_end:
                if(startDate==null){
                    ToastUtil.showToast(getActivity(),"请先选择开始时间");
                    return;
                }
                showPicker(TYPE_END);
                break;
            case R.id.tv_commit:
                if(checkDate()){
                    calculateCost();
                }
                break;
        }
    }


    private boolean checkDate(){
        price=tvPrice.getText().toString();
        if(startDate==null){
            ToastUtil.showToast(getActivity(),"开始日期不能为空");
            return false;
        }

        if(endDate==null){
            ToastUtil.showToast(getActivity(),"结束日期不能为空");
            return false;
        }
        if(TextUtils.isEmpty(price)){
            ToastUtil.showToast(getActivity(),"单价不能为空");
            return false;
        }


        return true;
    }


    private void calculateCost(){
        long duration=endDate.getTime()-startDate.getTime();
        hours= (int) (duration/(1000*3600));
        if(hours==0)
            hours=1;

        BigDecimal b1 = new BigDecimal(price);
        BigDecimal b2 = new BigDecimal(hours);

        totalCost=b1.multiply(b2).doubleValue();

        tvDuration.setText("使用时长："+hours+"小时");
        tvCost.setText("账单金额："+totalCost+"元");

    }


    private void showPicker(final int type){
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if(type==TYPE_START){
                    startDate=date;
                    tvDateStart.setText(RxTimeTool.date2String(date));
                }else {
                    if(date.getTime()<=startDate.getTime()){
                        ToastUtil.showToast(getActivity(),"结束时间不能小于开始时间");
                        return;
                    }

                    endDate=date;
                    tvDateEnd.setText(RxTimeTool.date2String(date));
                }

            }
        })
                .setType(new boolean[]{true, true, true, true, true, false}).build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
}
