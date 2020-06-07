package com.bibi.wisdom.network;

import com.bibi.wisdom.bean.BannerBean;
import com.bibi.wisdom.bean.DeviceInfoBean;
import com.bibi.wisdom.bean.DeviceListBean;
import com.bibi.wisdom.bean.HistoryBean;
import com.bibi.wisdom.bean.MaintainListBean;
import com.bibi.wisdom.bean.NoticeBean;
import com.bibi.wisdom.bean.UserLoginBean;
import com.bibi.wisdom.bean.base.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("user/loginbypwd")
    Observable<BaseBean<UserLoginBean>> loginByPwd2(@Query("phone") String username, @Query("pwd") String pwd);

    //注册发送短信
    @GET("user/sendsms4reg")
    Observable<BaseBean> sendRegisterMsg(@Query("phone") String phone);

    //找回密码发送短信
    @GET("user/sendsms4forget")
    Observable<BaseBean> sendResetMsg(@Query("phone") String phone);

    //首页banner
    @GET("common/homebanners")
    Observable<BaseBean<BannerBean>> getBanner();

    //历史
    @GET("security/productrecord/list")
    Observable<BaseBean<HistoryBean>> getHistoryList();

    //我的设备列表
    @GET("security/userproduct/userproductlist")
    Observable<BaseBean<DeviceListBean>> getDeviceList();

    //我的设备状态
    @GET("security/userproduct/findeqstatustbyid")
    Observable<BaseBean<DeviceInfoBean>> getDeviceInfo(@Query("id") String id);

    //打开设备
    @GET("security/userproduct/openimmediately")
    Observable<BaseBean> openDevice(@Query("id") String id);

    //关闭设备
    @GET("security/userproduct/closeimmediately")
    Observable<BaseBean> closeDevice(@Query("id") String id);

    //解绑设备
    @POST("security/userproduct/unbindproduct")
    Observable<BaseBean> unbindDevice(@Query("productId") String id);

    //绑定
    @POST("security/userproduct/bindproduct")
    Observable<BaseBean> bindDevice(@Query("productCode") String id, @Query("productName") String name, @Query("priceStr") String price);

    //修改设备
    @POST("security/userproduct/modifybindproduct")
    Observable<BaseBean> modifyDevice(@Query("id") String id, @Query("productName") String name, @Query("priceStr") String price);

    //联系人列表
    @GET("security/contact/findcontact")
    Observable<BaseBean<MaintainListBean>> getMaintainList(@Query("type") int type);

    ;

    //公告列表 公告类型，1,2
    @POST("security/notice/findnoticelist")
    Observable<BaseBean<NoticeBean>> getNoticeList(@Query("type") int type);

    //登出
    @GET("user/logout")
    Observable<BaseBean> logout();

    @POST("security/user/accountLogout")
    Observable<BaseBean> delectUser(@Query("phone") String phone);

    //注册
    @POST("user/register")
    Observable<BaseBean<UserLoginBean>> registerUser(@Query("phone") String phone, @Query("pwd") String password, @Query("random") String captcha);

    //重置密码
    @POST("user/forgetbysv")
    Observable<BaseBean> resetPassword(@Query("phone") String phone, @Query("pwd") String password, @Query("random") String captcha);

    @POST("/well/qurey/getVegInfo")
    Observable<String> getVegetablesInfo (@Body RequestBody requestBody);
}
