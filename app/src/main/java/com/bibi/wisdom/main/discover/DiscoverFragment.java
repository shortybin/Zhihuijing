package com.bibi.wisdom.main.discover;

import android.util.Log;

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
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.network.weather.WeatherHttp;
import com.bibi.wisdom.utils.AssetsUtils;
import com.bibi.wisdom.utils.CityUtils;
import com.bibi.wisdom.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by shortybin
 * on 2020/4/16
 */
public class DiscoverFragment extends MVPBaseFragment<DiscoverContract.View, DisvcoverPresenter> implements DiscoverContract.View {

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

                WeatherHttp.getInstance().天气预报15天(cityBean.getLat(), cityBean.getLon(), new ApiCallback() {
                    @Override
                    public void onFailure(ApiRequest apiRequest, Exception e) {

                    }

                    @Override
                    public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                        String body = new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING);
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
}
