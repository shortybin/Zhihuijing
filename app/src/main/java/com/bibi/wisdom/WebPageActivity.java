package com.bibi.wisdom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.utils.CustomUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebPageActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;

    private String url;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        url = getIntent().getStringExtra("url");
//        String title = getIntent().getStringExtra("title");
//        if (!TextUtils.isEmpty(title))
//            tvTopTitle.setText(title);
//        else {
//            tvTopTitle.setText("智慧井");
//        }
        CustomUtils.configureWebView(mWebView);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTopTitle.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

        mWebView.loadUrl(url);
    }

//    private void initRefresh() {
//        mPtrFrame.setLastUpdateTimeKey(getClass().getSimpleName());
//        mPtrFrame.setPtrHandler(mPtrHandler);
//    }
//
//    private PtrHandler mPtrHandler = new PtrHandler() {
//        @Override
//        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//            return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//        }
//
//        @Override
//        public void onRefreshBegin(PtrFrameLayout frame) {
//            mWebView.loadUrl(url);
//        }
//    };

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if (mWebView.canGoBack()) {
//            mWebView.goBack();
//        }else {
//            super.onBackPressed();
//        }
//    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    onBackPressed();
                }
                //overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @OnClick({R.id.iv_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_top_title:

                break;
        }
    }
}
