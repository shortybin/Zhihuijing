package com.bibi.wisdom.user.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.R;
import com.bibi.wisdom.WebPageActivity;
import com.bibi.wisdom.bean.UserLoginBean;
import com.bibi.wisdom.main.MainActivity;
import com.bibi.wisdom.mvp.MVPBaseActivity;
import com.bibi.wisdom.user.findpwd.FindPwdActivity;
import com.bibi.wisdom.user.register.RegisterActivity;
import com.bibi.wisdom.utils.UserService;
import com.bibi.wisdom.utils.VaUtils;
import com.bibi.wisdom.view.CommonDialog;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.user_checkbox)
    CheckBox mCheckBox;

    private String phone;
    private String pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTopTitle.setText("登录");
    }

    @Override
    public void loginSuccess(UserLoginBean user) {

        UserService.saveUserInfo(user);
        goPage(MainActivity.class);
        finish();

    }

    @Override
    public void loginFailed(String message) {
        RxToast.showToast(message);
    }


    /**
     * 检查
     */
    private boolean checkData() {
        phone = etPhoneNumber.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();

        if (!mCheckBox.isChecked()) {
            RxToast.normal(this, "请先勾选同意《用户协议与隐私政策》").show();
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            RxToast.normal(this, "请输入手机号").show();
            return false;
        }
        if (!VaUtils.isMobileNo(phone)) {
            RxToast.normal(this, "手机号格式不正确").show();
            return false;
        }

        if (TextUtils.isEmpty(pwd)) {
            RxToast.normal(this, "请输入密码").show();
            return false;
        }


        return true;
    }

    @OnClick({R.id.iv_back, R.id.tv_commit, R.id.tv_forget_pwd, R.id.tv_register, R.id.user_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                goPage(MainActivity.class);
                break;
            case R.id.tv_commit:
                if (checkData()) {
                    mPresenter.login(phone, pwd);
                }
                break;
            case R.id.tv_forget_pwd:
                if (!mCheckBox.isChecked()) {
                    RxToast.normal(this, "请先勾选同意《用户协议与隐私政策》").show();
                } else {
                    goPage(FindPwdActivity.class);
                }
                break;
            case R.id.tv_register:
                if (!mCheckBox.isChecked()) {
                    RxToast.normal(this, "请先勾选同意《用户协议与隐私政策》").show();
                } else {
                    goPage(RegisterActivity.class);
                }
                break;
            case R.id.user_protocol:
                startActivity(new Intent(getContext(), WebPageActivity.class).putExtra("url", "http://www.huahuazn.com/RegisterProtocol.html"));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            goPage(MainActivity.class);
        }
        return super.onKeyDown(keyCode, event);
    }
}
