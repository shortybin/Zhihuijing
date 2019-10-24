package com.bibi.wisdom;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.main.add_device.AddDeviceActivity;
import com.bibi.wisdom.utils.IKeys;
import com.bibi.wisdom.utils.ToastUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.vondear.rxtool.view.RxToast.showToast;

/**
 * 类描述：扫描
 * 创建人：G.G.Z
 * 创建时间：2017/6/29 10:41
 */
public class ScanActivity extends AppCompatActivity implements SensorEventListener {


    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @BindView(R.id.tv_light)
    TextView tvLight;
    @BindView(R.id.iv_light)
    ImageView ivLight;

    private boolean isLightOn = false;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean lightFlag=false; //判断是否已经打开手电筒

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CodeUtils.isLightEnable(true);
            isLightOn = true;
            tvLight.setText("关闭手电筒");
        }
    };

    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
        ButterKnife.bind(this);
        initView();
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
//        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();

        ActivityCompat.requestPermissions(this, PERMISSIONS, 100);
    }


    private void initView() {
        //第一步：获取 SensorManager 的实例
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //第二步：获取 Sensor 传感器类型
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //第四步：注册 SensorEventListener
//        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        if (isLightOn) {
            tvLight.setText("关闭手电筒");
        } else {
            tvLight.setText("打开手电筒");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }


    @OnClick(R.id.iv_light)
    public void onViewClicked() {
        if (isLightOn) {
            CodeUtils.isLightEnable(false);
            isLightOn = false;
            tvLight.setText("打开手电筒");
        } else {
            CodeUtils.isLightEnable(true);
            isLightOn = true;
            tvLight.setText("关闭手电筒");
        }
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent(ScanActivity.this, AddDeviceActivity.class);
            resultIntent.putExtra(IKeys.KEY_ID,result);
            resultIntent.putExtra(IKeys.KEY_FROM,AddDeviceActivity.FROM_ADD);
            ScanActivity.this.startActivity(resultIntent);
            ScanActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            ToastUtil.showToast(ScanActivity.this,"扫码失败，请重试");
        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        if(value<3&&!lightFlag){
            lightFlag=true;
            handler.sendEmptyMessageDelayed(111,1000);
        }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            String permission=permissions[i];
            if(TextUtils.equals(permission,Manifest.permission.CAMERA)){
                if(grantResults[i]== PackageManager.PERMISSION_GRANTED){

                }else {
                    RxToast.showToast("需要拍照权限才能扫码");
                }
            }
        }

    }
}
