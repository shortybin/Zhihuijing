package com.bibi.wisdom.main.discover;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.CityBean;
import com.bibi.wisdom.bean.FifteenWeahterBean;
import com.bibi.wisdom.bean.NowWeahterBean;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.network.weather.WeatherHttp;
import com.bibi.wisdom.utils.CityUtils;
import com.bibi.wisdom.utils.WeatherUtils;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shortybin
 * on 2020/4/16
 */
public class DiscoverFragment extends MVPBaseFragment<DiscoverContract.View, DisvcoverPresenter> implements DiscoverContract.View {

    @BindView(R.id.city_select)
    TextView citySelect;
    @BindView(R.id.future_weather)
    TextView futureWeather;
    @BindView(R.id.now_weather_title)
    TextView nowWeatherTitle;
    @BindView(R.id.current_temperature)
    TextView currentTemperature;
    @BindView(R.id.today_weather)
    ConstraintLayout todayWeather;
    @BindView(R.id.tomorrow_image)
    ImageView tomorrowImage;
    @BindView(R.id.tomorrow_text)
    TextView tomorrowText;
    @BindView(R.id.tomorrow_height)
    TextView tomorrowHeight;
    @BindView(R.id.tomorrow_low)
    TextView tomorrowLow;
    @BindView(R.id.tomorrow_weather)
    ConstraintLayout tomorrowWeather;
    @BindView(R.id.today_height_feel)
    TextView todayHeightFeel;
    @BindView(R.id.today_low_feel)
    TextView todayLowFeel;
    @BindView(R.id.today_weather_text)
    TextView todayWeatherText;
    Unbinder unbinder;
    @BindView(R.id.tomorrow_weather_text)
    TextView tomorrowWeatherText;
    @BindView(R.id.today_weather_image)
    ImageView todayWeatherImage;

    private AMapLocationClient mMLocationClient;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void init() {
        locationInit();
    }

    public void locationInit() {
        mMLocationClient = new AMapLocationClient(getContext());
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                CityBean cityBean = null;
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        cityBean = CityUtils.cityInfo(getContext(), city);
                    } else {
                        cityBean = CityUtils.cityInfo(getContext(), "北京市");
                    }
                } else {
                    cityBean = CityUtils.cityInfo(getContext(), "北京市");
                }

                WeatherHttp.getInstance().getFifteenWeahter(cityBean.getLat(), cityBean.getLon(), new ApiCallback() {
                    @Override
                    public void onFailure(ApiRequest apiRequest, Exception e) {

                    }

                    @Override
                    public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                        String body = new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING);
                        Gson gson = new Gson();
                        FifteenWeahterBean fifteenWeahterBean = gson.fromJson(body, FifteenWeahterBean.class);
                        List<FifteenWeahterBean.DataBean.ForecastBean> list = fifteenWeahterBean.getData().getForecast();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                FifteenWeahterBean.DataBean.ForecastBean todayForecastBean = list.get(0);
                                todayHeightFeel.setText("最高" + todayForecastBean.getTempDay() + "℃");
                                todayLowFeel.setText("最低" + todayForecastBean.getTempNight() + "℃");

                                FifteenWeahterBean.DataBean.ForecastBean tomorrowForecastBean = list.get(1);
                                tomorrowHeight.setText("最高" + tomorrowForecastBean.getTempDay() + "℃");
                                tomorrowLow.setText("最低" + tomorrowForecastBean.getTempNight() + "℃");
                                tomorrowWeatherText.setText(tomorrowForecastBean.getConditionDay() + "·" +
                                        tomorrowForecastBean.getWindDirDay() + "·"
                                        + tomorrowForecastBean.getWindLevelDay());
                                WeatherUtils.setTommorwWeatherIcon(tomorrowImage,tomorrowForecastBean.getConditionIdDay());
                            }
                        });
                    }
                });

                WeatherHttp.getInstance().getNowWeahter(cityBean.getLat(), cityBean.getLon(), new ApiCallback() {
                    @Override
                    public void onFailure(ApiRequest apiRequest, Exception e) {

                    }

                    @Override
                    public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                        String body = new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING);
                        Gson gson = new Gson();
                        NowWeahterBean nowWeahterBean = gson.fromJson(body, NowWeahterBean.class);
                        NowWeahterBean.DataBean.ConditionBean conditionBean = nowWeahterBean.getData().getCondition();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                currentTemperature.setText(conditionBean.getHumidity() + "℃/");
                                todayWeatherText.setText(conditionBean.getCondition() + "·" +
                                        conditionBean.getWindDir() + "·"
                                        + conditionBean.getWindLevel());

                                WeatherUtils.setTodayWeatherIcon(tomorrowImage,conditionBean.getConditionId());
                            }
                        });

                    }
                });
            }
        };
        mMLocationClient.setLocationListener(mLocationListener);

        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
        mMLocationClient.setLocationOption(mLocationOption);
        mMLocationClient.startLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMLocationClient.stopLocation();
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
}
