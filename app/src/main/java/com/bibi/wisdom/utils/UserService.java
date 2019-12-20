package com.bibi.wisdom.utils;

import android.text.TextUtils;

import com.bibi.wisdom.app.BaseApplication;
import com.bibi.wisdom.bean.UserLoginBean;
import com.google.gson.Gson;

/**
 * 用户信息
 *
 * @author G.G.Z
 */
public class UserService {


    private static final String USER_INFO = "userInfo";

    private static SharedPreferencesUtil spUtil=new SharedPreferencesUtil(BaseApplication.getInstance(),"wisdom");



    /**
     * 存储用户信息
     *
     * @param user
     */
    public static void saveUserInfo(UserLoginBean user) {
        if (user == null) {
            return;
        }

        //CustomConstant.TOKEN = user.token;

        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        spUtil.setData(USER_INFO, userJson);

    }
    /**
     * 登出
     *
     */
    public static void logout() {

        spUtil.setData(USER_INFO, "");

    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserLoginBean getUserInfo() {

        String userJson = (String) spUtil.getData(USER_INFO);

        if (TextUtils.isEmpty(userJson)) {
            return null;
        }

        Gson gson = new Gson();

        return  gson.fromJson(userJson, UserLoginBean.class);
    }


    public static boolean isLogin(){
        String userJson = (String) spUtil.getData(USER_INFO);
        return userJson != null && userJson.contains("id");
    }


}
