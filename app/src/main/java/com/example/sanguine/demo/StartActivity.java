package com.example.sanguine.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobcb.statement.mpchart.activity.BarChartTestActivity;
import com.mobcb.statement.mpchart.activity.LineChartTestActivity;
import com.mobcb.statement.mpchart.activity.PieChartTestActivity;
import com.mobcb.statement.mpchart.activity.member.MemberLineChartActivity;
import com.mobcb.statement.mpchart.activity.member.MemberPieChartActivity;


public class StartActivity extends Activity implements View.OnClickListener {

    /**
     * 进入模板
     */
    private Button mTvButton;
    /**
     * 折线图
     */
    private Button mTvButton2;
    /**
     * 年龄饼图
     */
    private Button mTvButton3;
    /**
     * 性别饼图
     */
    private Button mTvButton4;
    /**
     * 等级饼图
     */
    private Button mTvButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }


    private void initView() {
        mTvButton = (Button) findViewById(R.id.tv_button);
        mTvButton.setOnClickListener(this);
        mTvButton2 = (Button) findViewById(R.id.tv_button2);
        mTvButton2.setOnClickListener(this);
        mTvButton3 = (Button) findViewById(R.id.tv_button3);
        mTvButton3.setOnClickListener(this);
        mTvButton4 = (Button) findViewById(R.id.tv_button4);
        mTvButton4.setOnClickListener(this);
        mTvButton5 = (Button) findViewById(R.id.tv_button5);
        mTvButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_button:
//                Intent intent = new Intent(this, WebViewActivity.class);
//                Intent intent = new Intent(this, MainActivity.class);
//                Intent intent = new Intent(this, LambdaTestActivity.class);
                intent = new Intent(this, WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_button2:
                intent = new Intent(this, MemberLineChartActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_button3:
                intent = new Intent(this, PieChartTestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_button4:
                intent = new Intent(this, LineChartTestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_button5:
                intent = new Intent(this, BarChartTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
