package com.bibi.wisdom.main.discover;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.CityBean;
import com.bibi.wisdom.mvp.MVPBaseFragment;
import com.bibi.wisdom.utils.AssetsUtils;
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
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String assetsString = AssetsUtils.getAssetsString(getContext(), "city.json");

                        Gson gson=new Gson();
                        CityBean cityBean = null;
                        List<CityBean> cityBeanList = gson.fromJson(assetsString, new TypeToken<List<CityBean>>(){}.getType());
                        for (int i = 0; i < cityBeanList.size(); i++) {
                            if (cityBeanList.get(i).getSecond_city().equals(city)&&
                                    cityBeanList.get(i).getCity().equals(cityBeanList.get(i).getSecond_city())){
                                cityBean = cityBeanList.get(i);
                            }
                        }

                        Log.d("城市信息", cityBean.toString());
                    }
                }
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
