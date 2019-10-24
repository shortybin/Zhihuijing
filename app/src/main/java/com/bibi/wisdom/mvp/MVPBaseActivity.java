package com.bibi.wisdom.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.bibi.wisdom.utils.IKeys;
import com.gyf.barlibrary.ImmersionBar;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends RxActivity implements BaseView{
    public T mPresenter;
    protected int screenWidth;
    protected int screenHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
//        ImmersionBar.with(this)
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
////                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
////                .statusBarColor(R.color.status_bar)     //状态栏颜色，不写默认透明色
//                //解决软键盘与底部输入框冲突问题
//                .keyboardEnable(true).init();
//                //状态栏字体是深色，不写默认为亮色
//                .statusBarDarkFont(true, 0.2f)
//                //状态栏颜色，不写默认透明色
//                .statusBarColor(R.color.status_bar)
//                //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
//                .fitsSystemWindows(false).init();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        if (mPresenter!=null)
        mPresenter.detachView();
    }

    @Override
    public Context getContext(){
        return this;
    }

    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * 跳转到指定页面
     *
     * @param clas 指定页面
     */
    public void goPage(Class<? extends Activity> clas) {
        goPage(clas, null);
    }

    /***
     * 跳转到指定页面
     *
     * @param clas 指定页面
     * @param data 传入数据
     */
    protected void goPage(Class<? extends Activity> clas, Bundle data) {
        goPage(clas, data, -1);
    }

    /***
     * 跳转到指定页面
     *
     * @param clas        指定页面
     * @param data        传入数据
     * @param requestCode 请求值
     */
    protected void goPage(Class<? extends Activity> clas, Bundle data, int requestCode) {
        if (clas == null) {
            return;
        }
        Intent intent = new Intent(this, clas);
        if (data != null) {
            intent.putExtra(IKeys.KEY_DATA, data);
        }
        startActivityForResult(intent, requestCode);
    }

    /***
     * 获取intent传入值
     * @return bundle
     */
    protected Bundle getIntentData(){
        Bundle bundle=getIntent().getBundleExtra(IKeys.KEY_DATA);
        if(bundle==null){
            bundle=new Bundle();
        }
        return bundle;
    }

    /***
     * 获取String传入值，如果为空返回空字符串
     * @param key
     * @return
     */
    protected String getBundleStr(String key){
        Bundle bundle=getIntent().getBundleExtra(IKeys.KEY_DATA);
        if(bundle==null){
            return "";
        }
        return bundle.getString(key,"");
    }
}
