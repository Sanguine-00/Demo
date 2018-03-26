package com.example.sanguine.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {

    /**
     * 进入模板
     */
    private Button mTvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }


    private void initView() {
        mTvButton = (Button) findViewById(R.id.tv_button);
        mTvButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_button:
//                Intent intent = new Intent(this, WebViewActivity.class);
//                Intent intent = new Intent(this, MainActivity.class);
                Intent intent = new Intent(this, LambdaTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
