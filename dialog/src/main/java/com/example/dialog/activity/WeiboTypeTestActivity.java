package com.example.dialog.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.example.dialog.R;
import com.example.dialog.view.WeiboTopTypeDialog;

import java.util.Arrays;
import java.util.List;

public class WeiboTypeTestActivity extends Activity implements DialogInterface.OnCancelListener, WeiboTopTypeDialog.CallBack, View.OnClickListener {

    private WeiboTopTypeDialog topTypeDialog;
    /**
     * 1234142
     */
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_type_test);
        initView();
    }

    private void showTypeDialog() {
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.WeiboType));
        if (topTypeDialog == null) {
            topTypeDialog = new WeiboTopTypeDialog(this, list, list.get(0), this);
            topTypeDialog.setOnCancelListener(this);
        }
        topTypeDialog.show();
        changeDrawable(R.drawable.icon_weibo_push);
    }

    private void changeDrawable(@DrawableRes int res) {
        Drawable drawable = getResources().getDrawable(res);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvTitle.setCompoundDrawables(null, null, drawable, null);
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setOnClickListener(this);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void onItemClick(String tagInfoResult) {
        mTvTitle.setText(tagInfoResult);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_title) {
            showTypeDialog();
        }
    }
}
