package com.bibi.wisdom.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.bibi.wisdom.network.weather.WeatherHttp;
import com.bibi.wisdom.utils.cache.ShareData;
import com.uuzuche.lib_zxing.ZApplication;
import com.vondear.rxtool.RxTool;


public class BaseApplication extends ZApplication {
    private static final String TAG = "BaseApplication";
    private static BaseApplication app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        RxTool.init(this);
        ShareData.init(this);

        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("203819285");
        httpParam.setAppSecret("L5dRBm7nNY880I4qLEXoqmqr2KYD8IEl");
        WeatherHttp.getInstance().init(httpParam);
        //init(this);
    }


    public static BaseApplication getInstance() {
        return app;
    }


    public void init(Application app) {
//        ALog.setLevel(ALog.LEVEL_DEBUG);
//        com.aliyun.alink.linksdk.tools.ALog.setLevel(com.aliyun.alink.linksdk.tools.ALog.LEVEL_DEBUG);
//        // 初始化参数配置
//        IoTSmart.InitConfig initConfig = new IoTSmart.InitConfig()
//                // REGION_ALL: 支持连接中国大陆和海外多个接入点，REGION_CHINA_ONLY:直连中国大陆接入点，只在中国大陆出货选这个
//                .setRegionType(REGION_CHINA_ONLY)
//                // 对应控制台上的测试版（PRODUCT_ENV_DEV）和正式版（PRODUCT_ENV_PROD）
//                .setProductEnv(IoTSmart.PRODUCT_ENV_DEV)
//                // 是否打开日志
//                .setDebug(true);
//        GlobalConfig.getInstance().setApiEnv(GlobalConfig.API_ENV_ONLINE);
//        GlobalConfig.getInstance().setBoneEnv(GlobalConfig.BONE_ENV_TEST);
//
//        IoTSmart.getCountryList(new IoTSmart.ICountryListGetCallBack() {
//            @Override
//            public void onSucess(List<IoTSmart.Country> list) {
//                for (int i = 0; i < list.size(); i++) {
//                    Log.d(TAG, "onSucess: " + list.get(i).areaName);
//                    if (list.get(i).areaName.equals("中国大陆")) {
//                        GlobalConfig.getInstance().setCountry(list.get(i), new IoTSmart.ICountrySetCallBack() {
//                            @Override
//                            public void onCountrySet(boolean b) {
//                                // 初始化
//                                IoTSmart.init(app, initConfig);
//                            }
//                        });
//                    }
//                }
//            }
//
//            @Override
//            public void onFail(String s, int i, String s1) {
//
//            }
//        });
    }
}
