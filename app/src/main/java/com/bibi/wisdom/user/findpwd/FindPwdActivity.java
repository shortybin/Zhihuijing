package com.bibi.wisdom.user.findpwd;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.utils.VaUtils;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FindPwdActivity extends MVPBaseActivity<FindPwdContract.View, FindPwdPresenter> implements FindPwdContract.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.ll_captcha)
    LinearLayout llCaptcha;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_commit)
    TextView tvCommit;


    private String phone;
    private String code;
    private String password;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1002:
                    int second = msg.arg1;
                    tvGetCode.setText(second + "s");
                    second -= 1;
                    if (second < 1) {
                        tvGetCode.setText("重新获取");
                        tvGetCode.setTextColor(getResources().getColor(R.color.gray1));
                        tvGetCode.setEnabled(true);
                        return;
                    } else {
                        tvGetCode.setTextColor(getResources().getColor(R.color.white));
                        tvGetCode.setEnabled(false);
                    }
                    Message secondMsg = obtainMessage();
                    secondMsg.what = 1002;
                    secondMsg.arg1 = second;
                    sendMessageDelayed(secondMsg, 1000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        tvTopTitle.setText("忘记密码");
    }

    @OnClick({R.id.iv_back, R.id.tv_get_code, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_get_code:
                phone=etPhoneNumber.getText().toString();
                if(VaUtils.isMobileNo(phone)){
                    mPresenter.sendMsg(phone);
                }
                break;
            case R.id.tv_commit:
                if(checkData()){
                    mPresenter.resetPassword(phone,password,code);
                }
                break;
        }
    }

    /**
     * 检查
     */
    private boolean checkData() {
        phone = etPhoneNumber.getText().toString().trim();
        password = etPwd.getText().toString().trim();
        code=etCaptcha.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            RxToast.normal(this, "请输入手机号").show();
            return false;
        }
        if (!VaUtils.isMobileNo(phone)) {
            RxToast.normal(this, "手机号格式不正确").show();
            return false;
        }

        if (TextUtils.isEmpty(code)) {
            RxToast.normal(this, "请输入验证码").show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            RxToast.normal(this, "请输入密码").show();
            return false;
        }
        if (password.length()<6||password.length()>16) {
            RxToast.normal(this, "请输入6-16位密码").show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1002);
    }

    @Override
    public void sendSuccess() {
        RxToast.showToast("发送成功");

        Message msg = handler.obtainMessage();
        msg.what = 1002;
        msg.arg1 = 59;
        msg.sendToTarget();
    }

    @Override
    public void sendFail(String message) {
        RxToast.showToast(message);
    }

    @Override
    public void resetSuccess() {
        RxToast.showToast("设置密码成功");
        finish();
    }

    @Override
    public void resetFailed(String message) {
        RxToast.showToast(message);
    }
}
