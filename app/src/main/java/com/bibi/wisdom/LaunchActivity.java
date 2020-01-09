package com.bibi.wisdom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aliyun.iot.aep.sdk.login.ILoginCallback;
import com.aliyun.iot.aep.sdk.login.LoginBusiness;
import com.bibi.wisdom.main.MainActivity;
import com.bibi.wisdom.user.login.LoginActivity;
import com.bibi.wisdom.utils.LogUtils;
import com.bibi.wisdom.utils.UserService;

public class LaunchActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (LoginBusiness.isLogin()) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            } else {
                LoginBusiness.login(new ILoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onLoginFailed(int i, String s) {
                        LogUtils.d("onLoginFailed: 登陆失败");
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(111);
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        handler.removeMessages(111);
        super.onDestroy();
    }
}
