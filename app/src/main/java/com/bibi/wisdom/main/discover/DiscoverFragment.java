package com.bibi.wisdom.main.discover;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.bibi.wisdom.CityListActivity;
import com.bibi.wisdom.FifteenWeahterActivity;
import com.bibi.wisdom.R;
import com.bibi.wisdom.adapter.VegetablesAdapter;
import com.bibi.wisdom.bean.CityBean;
import com.bibi.wisdom.bean.FifteenWeahterBean;
import com.bibi.wisdom.bean.NowWeahterBean;
import com.bibi.wisdom.bean.VegetablesBean;
import com.bibi.wisdom.bean.base.BaseBean;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.network.HttpUtil;
import com.bibi.wisdom.network.SubscribeHandler;
import com.bibi.wisdom.network.rxjava.ProgressSubscriber;
import com.bibi.wisdom.network.rxjava.SubscriberOnNextListener;
import com.bibi.wisdom.network.weather.WeatherHttp;
import com.bibi.wisdom.utils.CityUtils;
import com.bibi.wisdom.utils.LogUtils;
import com.bibi.wisdom.utils.WeatherUtils;
import com.google.gson.Gson;
import com.vondear.rxtool.view.RxToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

import static android.app.Activity.RESULT_OK;

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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private AMapLocationClient mMLocationClient;
    private List<FifteenWeahterBean.DataBean.ForecastBean> list;
    private FifteenWeahterBean.DataBean data;
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private VegetablesAdapter vegetablesAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void init() {
        ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, 100);
        getVegetablesInfo("");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vegetablesAdapter = new VegetablesAdapter(getContext());
        recyclerView.setAdapter(vegetablesAdapter);
        locationInit();
        citySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityListActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        futureWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FifteenWeahterActivity.class);
                intent.putExtra("data", (Parcelable) data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                String cityName = data.getStringExtra("cityName");
                CityBean cityBean = CityUtils.cityInfo(getContext(), cityName);
                setWeatherInfo(cityBean);
            }
        }
    }

    public void locationInit() {
        mMLocationClient = new AMapLocationClient(getContext());
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                CityBean cityBean = null;
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0 && !TextUtils.isEmpty(aMapLocation.getCity())&&!TextUtils.isEmpty(aMapLocation.getCityCode())) {
                        String city = aMapLocation.getCity();
                        cityBean = CityUtils.cityInfo(getContext(), city);
                    } else {
                        cityBean = CityUtils.cityInfo(getContext(), "北京市");
                    }
                } else {
                    cityBean = CityUtils.cityInfo(getContext(), "北京市");
                }
                setWeatherInfo(cityBean);
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

    public void setWeatherInfo(CityBean cityBean) {
        citySelect.setText(cityBean.getCity() + "->");
        WeatherHttp.getInstance().getFifteenWeahter(cityBean.getLat(), cityBean.getLon(), new ApiCallback() {
            @Override
            public void onFailure(ApiRequest apiRequest, Exception e) {

            }

            @Override
            public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                String body = new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING);
                Gson gson = new Gson();
                FifteenWeahterBean fifteenWeahterBean = gson.fromJson(body, FifteenWeahterBean.class);
                data = fifteenWeahterBean.getData();
                list = data.getForecast();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FifteenWeahterBean.DataBean.ForecastBean todayForecastBean = list.get(1);
                        todayHeightFeel.setText("最高" + todayForecastBean.getTempDay() + "℃");
                        todayLowFeel.setText("最低" + todayForecastBean.getTempNight() + "℃");

                        FifteenWeahterBean.DataBean.ForecastBean tomorrowForecastBean = list.get(2);
                        tomorrowHeight.setText("最高" + tomorrowForecastBean.getTempDay() + "℃");
                        tomorrowLow.setText("最低" + tomorrowForecastBean.getTempNight() + "℃");
                        tomorrowWeatherText.setText(tomorrowForecastBean.getConditionDay() + "·" +
                                tomorrowForecastBean.getWindDirDay() + "·"
                                + tomorrowForecastBean.getWindLevelDay());
                        WeatherUtils.setTommorwWeatherIcon(tomorrowImage, tomorrowForecastBean.getConditionIdDay());
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
                        currentTemperature.setText(conditionBean.getTemp() + "℃/");
                        todayWeatherText.setText(conditionBean.getCondition() + "·" +
                                conditionBean.getWindDir() + "·"
                                + conditionBean.getWindLevel());

                        WeatherUtils.setTodayWeatherIcon(todayWeatherImage, conditionBean.getConditionId());
                    }
                });

            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (TextUtils.equals(permission, Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    RxToast.showToast("需要定位权限");
                }
            }
        }
    }

    public void getVegetablesInfo(String addressCode) {
        Map map=new HashMap<String,String>();
        map.put("addressCode","010110000");
        map.put("latitude","");
        map.put("longitude","");
        map.put("newsDate","20200607");
        map.put("productCode","");
        map.put("typeCode","001");

        Observable<BaseBean<List<VegetablesBean>>> vegetablesInfo = HttpUtil.getInstance().getVegetablesInfo(HttpUtil.getRequestBody(map));
        SubscriberOnNextListener<List<VegetablesBean>> listener = new SubscriberOnNextListener<List<VegetablesBean>>() {
            @Override
            public void onNext(List<VegetablesBean> s) {
                vegetablesAdapter.getData().clear();
                vegetablesAdapter.addData(s);
                LogUtils.d("打印信息" + s);
            }

            @Override
            public void onFail(String err) {
                LogUtils.d("打印信息" + err);
            }
        };
        SubscribeHandler.observeOn(vegetablesInfo, new ProgressSubscriber(listener, getActivity(), false));
    }
}
