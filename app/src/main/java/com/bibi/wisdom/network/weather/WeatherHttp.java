//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.bibi.wisdom.network.weather;
import com.alibaba.cloudapi.sdk.client.HttpApiClient;
import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;

import java.io.IOException;


public class WeatherHttp extends HttpApiClient{
    public final static String HOST = "aliv8.data.moji.com";
    static WeatherHttp instance = new WeatherHttp();
    public static WeatherHttp getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }

    public void 短时预报(String lat , String lon , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/shortforecast";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        sendAsyncRequest(request , callback);
    }

    public ApiResponse 短时预报_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/shortforecast";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        return sendSyncRequest(request);
    }
    public void 限行数据(String lat , String lon , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/limit";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        sendAsyncRequest(request , callback);
    }

    public ApiResponse 限行数据_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/limit";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        return sendSyncRequest(request);
    }
    public void 生活指数(String lon , String lat , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/index";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        sendAsyncRequest(request , callback);
    }

    public ApiResponse 生活指数_syncMode(String lon , String lat , String token) {
        String path = "/whapi/json/aliweather/index";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        return sendSyncRequest(request);
    }
    public void 天气预警(String lat , String lon , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/alert";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        sendAsyncRequest(request , callback);
    }

    public ApiResponse 天气预警_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/alert";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);

        return sendSyncRequest(request);
    }

    //未来15天天气
    public void getFifteenWeahter(String lat , String lon , ApiCallback callback) {
        String path = "/whapi/json/aliweather/forecast15days";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , "7538f7246218bdbf795b329ab09cc524" , ParamPosition.BODY , false);
        sendAsyncRequest(request , callback);
    }

    public ApiResponse 天气预报15天_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/forecast15days";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        return sendSyncRequest(request);
    }
    public void 天气预报24小时(String lat , String lon , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/forecast24hours";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        sendAsyncRequest(request , callback);
    }

    public ApiResponse 天气预报24小时_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/forecast24hours";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        return sendSyncRequest(request);
    }
    public void AQI预报5天(String lat , String lon , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/aqiforecast5days";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        sendAsyncRequest(request , callback);
    }

    public ApiResponse AQI预报5天_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/aqiforecast5days";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);



        return sendSyncRequest(request);
    }
    public void getNowWeahter(String lat , String lon , ApiCallback callback) {
        String path = "/whapi/json/aliweather/condition";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , "ff826c205f8f4a59701e64e9e64e01c4" , ParamPosition.BODY , false);
        sendAsyncRequest(request , callback);
    }

    public ApiResponse 天气实况_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/condition";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        return sendSyncRequest(request);
    }
    public void 空气质量指数(String lat , String lon , String token , ApiCallback callback) {
        String path = "/whapi/json/aliweather/aqi";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        sendAsyncRequest(request , callback);
    }

    public ApiResponse 空气质量指数_syncMode(String lat , String lon , String token) {
        String path = "/whapi/json/aliweather/aqi";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("lat" , lat , ParamPosition.BODY , true);
        request.addParam("lon" , lon , ParamPosition.BODY , true);
        request.addParam("token" , token , ParamPosition.BODY , false);
        return sendSyncRequest(request);
    }

    public String getResultString(ApiResponse response){
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }
        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }
}