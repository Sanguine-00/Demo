package com.example.sanguine.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DrawableTestActivity extends AppCompatActivity {


    int count = 0;
    private MyLinearLayout mDrawableLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_test);
        initView();
    }

    public void change(View view) {
        if (mDrawableLl != null) {
            count++;
            mDrawableLl.setEnable(count % MyLinearLayout.count);
        }
    }

    public void change1(View view) {
        if (mDrawableLl != null) {
            if (mDrawableLl.getEnabled1()) {
                mDrawableLl.disable1();
            } else {
                mDrawableLl.enable1();
            }
        }
    }

    public void change2(View view) {
        if (mDrawableLl != null) {
            if (mDrawableLl.getEnabled2()) {
                mDrawableLl.disable2();
            } else {
                mDrawableLl.enable2();
            }
        }
    }

    public void change3(View view) {
        if (mDrawableLl != null) {
            if (mDrawableLl.getEnabled3()) {
                mDrawableLl.disable3();
            } else {
                mDrawableLl.enable3();
            }
        }
    }

    private void initView() {
        mDrawableLl = (MyLinearLayout) findViewById(R.id.drawable_ll);
        mDrawableLl.setEnable(0);
    }
}
