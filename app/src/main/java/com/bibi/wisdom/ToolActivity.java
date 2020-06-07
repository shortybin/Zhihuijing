package com.bibi.wisdom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bibi.wisdom.main.tool.ToolFragment;
import com.bibi.wisdom.utils.StatusBarUtil;

import butterknife.ButterKnife;

public class ToolActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_tool);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new ToolFragment()).commitNowAllowingStateLoss();
    }
}
