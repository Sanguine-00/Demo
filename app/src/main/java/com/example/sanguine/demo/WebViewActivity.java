package com.example.sanguine.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends Activity implements View.OnClickListener {


    private Activity mActivity;
    private WebView mWebView;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        AndroidBug5497Workaround.assistActivity(this);
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_view);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mWebView.getSettings().setDefaultTextEncodingName("gbk");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.setVisibility(View.VISIBLE);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://m.baidu.com");
    }

    public void button(View view) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    protected void onNewIntent(Intent intent) {
    }

}
