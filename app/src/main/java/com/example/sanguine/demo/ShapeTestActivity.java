package com.example.sanguine.demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShapeTestActivity extends AppCompatActivity {

    /**
     * 12
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_test);
        initView();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);

        Shape shape = new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                //设置颜色
                paint.setColor(Color.BLUE);
                //设置抗锯齿
                paint.setAntiAlias(true);
                //设置画笔粗细
                paint.setStrokeWidth(1);
                //设置是否为空心
                paint.setStyle(Paint.Style.FILL);
                Rect rect = new Rect(-4,
                        -4,
                        UnitUtil.dip2px(ShapeTestActivity.this, 8),
                        UnitUtil.dip2px(ShapeTestActivity.this, 8));
                canvas.drawRect(rect, paint);
            }
        };
        shape.resize(UnitUtil.dip2px(this, 8), UnitUtil.dip2px(this, 8));
        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.setBounds(0, 0, (int) shape.getWidth(), (int) shape.getHeight());

        mTv.setCompoundDrawables(drawable, null, null, null);
//        mTv.setCompoundDrawablePadding(UnitUtil.dip2px(this, 8));
    }
}
