package com.bibi.wisdom.adapter;

import android.content.Context;

import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.FifteenWeahterBean;
import com.bibi.wisdom.utils.DateUtils;
import com.bibi.wisdom.utils.WeatherUtils;

public class FifiteenWeahterAdatper extends CommonRecyclerAdapter<FifteenWeahterBean.DataBean.ForecastBean> {
    public FifiteenWeahterAdatper(Context context) {
        super(context, null, R.layout.fifteen_weather_item);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, FifteenWeahterBean.DataBean.ForecastBean forecastBean) {
        String[] s = forecastBean.getSunrise().split(" ");
        String s1 = DateUtils.dateToWeek(s[0]);
        holder.setTextViewText(R.id.time_text, s[0] + " " + s1);
        holder.setTextViewText(R.id.height_text, "最高" + forecastBean.getTempDay() + "℃");
        holder.setTextViewText(R.id.low_text, "最低" + forecastBean.getTempNight() + "℃");
        holder.setTextViewText(R.id.humidity_text, "湿度" + forecastBean.getWindDirDay());
        WeatherUtils.setTommorwWeatherIcon(holder.getView(R.id.weather_image), forecastBean.getConditionIdDay());
    }
}
