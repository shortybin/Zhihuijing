package com.bibi.wisdom.app;

import android.app.Application;

import com.bibi.wisdom.utils.cache.ShareData;
import com.uuzuche.lib_zxing.ZApplication;
import com.vondear.rxtool.RxTool;

public class BaseApplication extends ZApplication {
    private static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        RxTool.init(this);
        ShareData.init(this);
//        initImagePicker();
    }


    public static BaseApplication getInstance(){
        return app;
    }


//    private void initImagePicker() {
//        ImagePicker imagePicker = ImagePicker.getInstance();
//        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
//        imagePicker.setShowCamera(true);  //显示拍照按钮
//        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
//        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
//        imagePicker.setSelectLimit(9);    //选中数量限制
//        imagePicker.setMultiMode(false);
//        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
////        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
////        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
////        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
////        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
//    }
}
