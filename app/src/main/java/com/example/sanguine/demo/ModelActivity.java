package com.example.sanguine.demo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sanguine.demo.acp.Acp;
import com.example.sanguine.demo.acp.AcpListener;
import com.example.sanguine.demo.acp.AcpOptions;

import java.util.List;

public class ModelActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        initView();
        showModel();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }

    private void showModel(){
        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.READ_PHONE_STATE).build(), new AcpListener() {
            @Override
            public void onGranted() {
                mTv.setText("手机型号是:"+android.os.Build.MODEL);
            }

            @Override
            public void onDenied(List<String> permissions) {

                mTv.setText("不给权限看个卵子!!");

            }
        });
    }
}
