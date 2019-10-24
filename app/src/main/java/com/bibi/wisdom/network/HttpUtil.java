package com.bibi.wisdom.network;

import com.bibi.wisdom.BuildConfig;
import com.bibi.wisdom.app.BaseApplication;
import com.bibi.wisdom.bean.UserLoginBean;
import com.bibi.wisdom.network.interceptor.LoggerInterceptor;
import com.bibi.wisdom.utils.DeviceUtils;
import com.bibi.wisdom.utils.UserService;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private static ApiService SERVICE;

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 5;
    private static String BASE_URL = Url.BASE_URL;

    public static ApiService getInstance() {
        if (SERVICE == null) {
            /**
             * 手动创建一个OkHttpClient并设置超时时间
             */
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//                    .cookieJar(new CookieManger(context));//添加cookie

            /**
             * 对所有请求添加请求头(全局header,可局部动态添加header)
             */
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    //参数就要针对body做操作,我这里针对From表单做操作
//                    if (request.body() instanceof MultipartBody) {
//                        // 构造新的请求表单
//                        MultipartBody.Builder builder = new MultipartBody.Builder();
//                        MultipartBody body = (MultipartBody) request.body();
//                        //将以前的参数添加
//                        for (int i = 0; i < body.size(); i++) {
//                            builder.addPart(body.part(i));
//                        }
//                        //追加新的参数
//                        builder.addFormDataPart("imei", DeviceUtils.getPhoneUID(BaseApplication.getInstance()));
//                        request = request.newBuilder().post(builder.build()).build();//构造新的请求体
//                    }
                    String deviceId=DeviceUtils.getPhoneUID(BaseApplication.getInstance());
                    HttpUrl.Builder urlBuilder = request.url().newBuilder();
                    urlBuilder.addQueryParameter("imei",deviceId);
                    String urlString =request.url().toString();
                    if(UserService.isLogin()){
                        UserLoginBean bean=UserService.getUserInfo();
                        urlBuilder.addQueryParameter("token",bean.getAccountInfo().getToken());
                        if(!urlString.contains("findnoticelist")){
                            urlBuilder.addQueryParameter("user_id",bean.getUserInfo().getId());
                        }
                        urlBuilder.addQueryParameter("userId",bean.getUserInfo().getId());
                    }
                    Request.Builder builder=request.newBuilder();
                    builder.url(urlBuilder.build())
                            .addHeader("imei",DeviceUtils.getPhoneUID(BaseApplication.getInstance()));
                    if(UserService.isLogin()){
                        UserLoginBean bean=UserService.getUserInfo();
                        builder.addHeader("token",bean.getAccountInfo().getToken());
                        builder.addHeader("user_id",bean.getUserInfo().getId());
                    }
                    Response response = chain.proceed(builder.build());



                    return response;

                }
            });

            if (BuildConfig.DEBUG) {
                /**
                 * debug模式下打印json
                 */
                LoggerInterceptor loggerInterceptor = new LoggerInterceptor();
                httpClientBuilder.addInterceptor(loggerInterceptor);
            }


            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build().create(ApiService.class);

        }
        return SERVICE;
    }


    public static Request interceptRequest(@NotNull Request request, @NotNull String parameter)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Sink sink = Okio.sink(baos);
        BufferedSink bufferedSink = Okio.buffer(sink);

        /**
         * Write old params
         * */
        request.body().writeTo(bufferedSink);

        /**
         * write to buffer additional params
         * */
        bufferedSink.writeString(parameter, Charset.forName("UTF-8"));

        RequestBody newRequestBody = RequestBody.create(
                request.body().contentType(),
                bufferedSink.buffer().readUtf8()
        );

        return request.newBuilder().post(newRequestBody).build();
    }

}
