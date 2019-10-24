package com.bibi.wisdom.main.add_device;

import android.content.Context;

import com.bibi.wisdom.mvp.BasePresenter;
import com.bibi.wisdom.mvp.BaseView;

/**
 *
 */

public class AddDeviceContract {
    interface View extends BaseView {
        void unbindSuccess();

        void unbindFail(String message);


        void modifySuccess();

        void modifyFail(String message);


        void addSuccess();

        void addFail(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void unbindDevice(String userId,String id);


        void modifyDevice(String userId,String id,String deviceName,String price);


        void addDevice(String code,String deviceName,String price);



    }
}
