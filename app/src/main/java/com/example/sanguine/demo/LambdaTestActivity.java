package com.example.sanguine.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class LambdaTestActivity extends AppCompatActivity {

    /**
     * 点我!
     */
    private TextView mTvLambdaClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda_test);
        initView();
    }

    private void initView() {
        mTvLambdaClick = (TextView) findViewById(R.id.tv_lambda_click);
        mTvLambdaClick.setOnClickListener(v -> {
            Toast.makeText(LambdaTestActivity.this, "lambda_click_one", Toast.LENGTH_SHORT).show();
            Toast.makeText(LambdaTestActivity.this, "lambda_click_two", Toast.LENGTH_SHORT).show();
            Toast.makeText(LambdaTestActivity.this, "lambda_click_three", Toast.LENGTH_SHORT).show();
        });
    }
}
