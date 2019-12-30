package com.bibi.wisdom.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.wireless.security.jaq.JAQException;
import com.alibaba.wireless.security.jaq.SecurityInit;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClientImpl;
import com.aliyun.iot.aep.sdk.apiclient.emuns.Env;
import com.aliyun.iot.aep.sdk.log.ALog;
import com.aliyun.iot.aep.sdk.login.LoginBusiness;
import com.aliyun.iot.aep.sdk.login.oa.OALoginAdapter;
import com.aliyun.iot.push.PushManager;
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

        init(this);
        PushManager.getInstance().init(this, "114d");
    }


    public static BaseApplication getInstance() {
        return app;
    }


    public void init(Application app) {

        // 初始化无线保镖
        try {
            SecurityInit.Initialize(app);
        } catch (JAQException ex) {
            ALog.e(TAG, "security-sdk-initialize-failed", ex);
        } catch (Exception ex) {
            ALog.e(TAG, "security-sdk-initialize-failed", ex);
        }

        // 初始化 IoTAPIClient
        IoTAPIClientImpl.InitializeConfig config = new IoTAPIClientImpl.InitializeConfig();
        config.host = "api.link.aliyun.com";
        config.apiEnv = Env.RELEASE;
        config.authCode = "114d";

        IoTAPIClientImpl impl = IoTAPIClientImpl.getInstance();
        impl.init(app, config);
    }
}
