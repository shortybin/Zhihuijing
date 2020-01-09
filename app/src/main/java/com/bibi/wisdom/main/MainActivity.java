package com.bibi.wisdom.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.aliyun.alink.business.devicecenter.api.add.DeviceInfo;
import com.aliyun.alink.business.devicecenter.api.discovery.DiscoveryType;
import com.aliyun.alink.business.devicecenter.api.discovery.IDeviceDiscoveryListener;
import com.aliyun.alink.business.devicecenter.api.discovery.LocalDeviceMgr;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.iot.aep.component.router.Router;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClient;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClientFactory;
import com.aliyun.iot.aep.sdk.apiclient.callback.IoTCallback;
import com.aliyun.iot.aep.sdk.apiclient.callback.IoTResponse;
import com.aliyun.iot.aep.sdk.apiclient.emuns.Scheme;
import com.aliyun.iot.aep.sdk.apiclient.request.IoTRequest;
import com.aliyun.iot.aep.sdk.apiclient.request.IoTRequestBuilder;
import com.aliyun.iot.aep.sdk.log.ALog;
import com.aliyun.iot.link.ui.component.LinkToast;
import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.DeviceInfoBean;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.utils.LogUtils;
import com.bibi.wisdom.utils.MyFragmentTabHost;
import com.bibi.wisdom.utils.StatusBarUtil;
import com.bibi.wisdom.utils.TabDb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

    // 接收配网结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (1 == requestCode) {
            if (Activity.RESULT_OK != resultCode) {
                // 配网失败
                return;
            }

            String productKey = data.getStringExtra("productKey");
            String deviceName = data.getStringExtra("deviceName");
            // 配网成功
        }

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

    private void bindVirturalToUser(String pk, String dn) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("productKey", pk);
        maps.put("deviceName", dn);
        IoTRequestBuilder builder = new IoTRequestBuilder()
                .setPath("/thing/virtual/binduser")
                .setApiVersion("1.0.0")
                .setAuthType("iotAuth")
                .setParams(maps);

        IoTRequest request = builder.build();

        IoTAPIClient ioTAPIClient = new IoTAPIClientFactory().getClient();
        ioTAPIClient.send(request, new IoTCallback() {
            @Override
            public void onFailure(IoTRequest ioTRequest, Exception e) {

            }

            @Override
            public void onResponse(IoTRequest ioTRequest, IoTResponse response) {
                final int code = response.getCode();
                final String localizeMsg = response.getLocalizedMsg();
                listByAccount();
            }
        });
    }

    private void listByAccount() {
        Map<String, Object> maps = new HashMap<>();
        IoTRequestBuilder builder = new IoTRequestBuilder()
                .setPath("/uc/listBindingByAccount")
                .setScheme(Scheme.HTTPS)
                .setApiVersion("1.0.2")
                .setAuthType("iotAuth")
                .setParams(maps);

        IoTRequest request = builder.build();

        IoTAPIClient ioTAPIClient = new IoTAPIClientFactory().getClient();
//        data:{"code":401,"id":"85691179-9b20-4558-80e7-bb0068a3527b","localizedMsg":"Request authentication error","message":"request auth error."}
        ioTAPIClient.send(request, new IoTCallback() {
            @Override
            public void onFailure(IoTRequest ioTRequest, Exception e) {
            }

            @Override
            public void onResponse(IoTRequest ioTRequest, IoTResponse response) {
                final int code = response.getCode();
                final String localizeMsg = response.getLocalizedMsg();

                Object data = response.getData();
                if (data == null) {
                    return;
                }
                if (!(data instanceof JSONObject)) {
                    return;
                }
                try {
                    JSONObject jsonObject = (JSONObject) data;
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    List<DeviceInfoBean> deviceInfoBeanList = JSON.parseArray(jsonArray.toString(), DeviceInfoBean.class);
                    for (int i = 0; i < deviceInfoBeanList.size(); i++) {
                        LogUtils.d(deviceInfoBeanList.get(i).toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
