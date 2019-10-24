package com.bibi.wisdom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.utils.DeviceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends AppCompatActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        tvTopTitle.setText("关于我们");
        tvVersion.setText(DeviceUtils.getCurrentAppVersionName(this));
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
