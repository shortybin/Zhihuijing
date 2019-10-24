package com.bibi.wisdom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bibi.wisdom.main.MainActivity;
import com.bibi.wisdom.user.login.LoginActivity;
import com.bibi.wisdom.utils.UserService;

public class LaunchActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(UserService.isLogin()){
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            }else {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
            }
            finish();
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
        },3000);
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
