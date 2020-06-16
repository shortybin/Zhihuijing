package com.bibi.wisdom.network.rxjava;

import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.util.Log;

import com.bibi.wisdom.user.login.LoginActivity;
import com.bibi.wisdom.utils.UserService;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.bibi.wisdom.R;
import com.bibi.wisdom.bean.base.BaseBean;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.bibi.wisdom.network.rxjava.ApiException.BAD_NETWORK;
import static com.bibi.wisdom.network.rxjava.ApiException.CONNECT_ERROR;
import static com.bibi.wisdom.network.rxjava.ApiException.CONNECT_TIMEOUT;
import static com.bibi.wisdom.network.rxjava.ApiException.PARSE_ERROR;
import static com.bibi.wisdom.network.rxjava.ApiException.UNKNOWN_ERROR;


/**
 * @author RedLi
 * @date 2018/3/21
 */

public class ProgressSubscriber<T extends BaseBean> implements
        ProgressCancelListener, Observer<T> {

    private static final String TAG = ProgressSubscriber.class.getSimpleName();

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    private boolean isShowLoading;
    private Disposable disposable;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context, boolean isShowLoading) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.isShowLoading = isShowLoading;
        this.mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG)
                    .sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG)
                    .sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable s) {
        Log.d(TAG, "onSubscribe: ");
        this.disposable = s;
        if (isShowLoading) {
            showProgressDialog();
        }
    }

    @Override
    public void onNext(T t) {
        Log.d(TAG, "onNext: ");
        dismissProgressDialog();
        /**
         * 这里的话是通过success 或者 code 来判断 可惜豆辫放回数据没有这个字段 就只能通多count来判断了
         */
        if (mSubscriberOnNextListener != null && t.getStatus() == 101) {
            if (t.getData() == null&&t.getDatas()!=null){
                mSubscriberOnNextListener.onNext(t.getDatas());
                return;
            }
            if (t.getData()==null){
                t.setData(new Object());
            }

            mSubscriberOnNextListener.onNext(t.getData());
        } else if (t.getStatus() == 601) {  //登录过期
            onFail(t);
            UserService.logout();
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        } else {
            onFail(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("onError", e.getMessage());
        dismissProgressDialog();
        if (e instanceof SocketException) {
            onException(context, CONNECT_ERROR);
        } else if (e instanceof HttpException) {
            //   HTTP错误
            onException(context, BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(context, CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(context, CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(context, PARSE_ERROR);
        } else {
            onException(context, UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        Log.d(TAG, "onCancelProgress: ");
        //截断信息，下游接受不到信息
        if (!this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    /**
     * 服务器返回数据，但响应码不为200 或者 success 为false
     *
     * @param response 服务器返回的数据
     */

    private void onFail(T response) {
        String message = response.getMsg();
        if (response.getCount() == 0 && mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onFail(message);
//            mSubscriberOnNextListener.onFail("暂无数据");
        }
//        else {
//            mSubscriberOnNextListener.onFail(message);
//        }
    }


    /**
     * 请求异常
     *
     * @param reason
     */
    private void onException(Context context, int reason) {

        switch (reason) {
            case CONNECT_ERROR:
                mSubscriberOnNextListener.onFail(context.getString(R.string.connect_error));
                break;
            case CONNECT_TIMEOUT:
                mSubscriberOnNextListener.onFail(context.getString(R.string.connect_timeout));
                break;
            case BAD_NETWORK:
                mSubscriberOnNextListener.onFail(context.getString(R.string.bad_network));
                break;
            case PARSE_ERROR:
                mSubscriberOnNextListener.onFail(context.getString(R.string.parse_error));
                break;
            case UNKNOWN_ERROR:
            default:
                mSubscriberOnNextListener.onFail(context.getString(R.string.unknown_error));
                break;
        }
    }
}