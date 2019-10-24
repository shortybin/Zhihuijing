package com.bibi.wisdom.main;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.utils.MyFragmentTabHost;
import com.bibi.wisdom.utils.StatusBarUtil;
import com.bibi.wisdom.utils.TabDb;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 首页
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View, TabHost.OnTabChangeListener {
    private MyFragmentTabHost tabHost;
    private String[] tabs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTab();
    }


    private void initTab() {
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager()
                , R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        tabs = TabDb.getTabsTxt();
        for (int i = 0; i < tabs.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabDb.getFragments()[i], null);

            tabHost.setTag(i);
        }

    }


    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.footer_tabs, null);
        ((TextView) view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);
        if (idx == 0) {
            ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.main_color));
            ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImgLight()[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImg()[idx]);
            ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.home_color_unselected));
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {

        updateTab();
    }

    /**
     * 更新底部tab
     */
    private void updateTab() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = view.findViewById(R.id.ivImg);
            if (i == tabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.colorAccent));
                iv.setImageResource(TabDb.getTabsImgLight()[i]);

            } else {
                ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.home_color_unselected));
                iv.setImageResource(TabDb.getTabsImg()[i]);
            }


        }
    }
}
