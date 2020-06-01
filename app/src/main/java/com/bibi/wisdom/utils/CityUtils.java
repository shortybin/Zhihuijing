package com.bibi.wisdom.utils;

import android.content.Context;
import android.util.Log;

import com.bibi.wisdom.bean.CityBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CityUtils {

    public static CityBean cityInfo(Context context,String city){
        String assetsString = AssetsUtils.getAssetsString(context, "city.json");

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
        return cityBean;
    }

    public static List<CityBean> getCityList(Context context){
        List<CityBean> list=new ArrayList<>();
        String assetsString = AssetsUtils.getAssetsString(context, "city.json");

        Gson gson=new Gson();
        List<CityBean> cityBeanList = gson.fromJson(assetsString, new TypeToken<List<CityBean>>(){}.getType());

        for (int i = 0; i < cityBeanList.size(); i++) {
            if (cityBeanList.get(i).getCity().equals(cityBeanList.get(i).getSecond_city())){
                list.add(cityBeanList.get(i));
            }
        }
        return list;
    }

}
