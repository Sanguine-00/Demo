package com.example.sanguine.demo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

/**
 * Created by Sanguine on 2018/4/20.
 */

public class MyLinearLayout extends LinearLayout {

    public static int count = 5;
    private Drawable myDrawable;
    private List<View> views = new Vector<>();

    public MyLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Resources resources = getResources();
        if (resources == null) return;

        views.clear();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);
            if (typedArray == null) return;

            Drawable drawable1 = null;
            Drawable drawable2 = null;
            Drawable drawable3 = null;
            for (int i = 0; i < typedArray.getIndexCount(); i++) {
                int attr = typedArray.getIndex(i);
                if (R.styleable.MyLinearLayout_drawable == attr) {
                    myDrawable = (StateListDrawable) typedArray.getResources().getDrawable(typedArray.getResourceId(attr, 0));
                    drawable1 = (StateListDrawable) typedArray.getResources().getDrawable(typedArray.getResourceId(attr, 0));
                    drawable2 = (StateListDrawable) typedArray.getResources().getDrawable(typedArray.getResourceId(attr, 0));
                    drawable3 = (StateListDrawable) typedArray.getResources().getDrawable(typedArray.getResourceId(attr, 0));
                }
            }

//            TextView pointView1 = new TextView(getContext());
//            LayoutParams params = new LayoutParams(UnitUtil.dip2px(context, 50)
//                    , UnitUtil.dip2px(context, 50));
//            params.setMargins(0, 0, 0, 0);
//            params.gravity = Gravity.CENTER;
//            params.setMargins(10, 0, 10, 0);
//            pointView1.setText(1 + "");
//            pointView1.setBackgroundDrawable(drawable1);
//            pointView1.setLayoutParams(params);
//            views.add(pointView1);
//            addView(pointView1);
//
//            TextView pointView2 = new TextView(getContext());
//            params.setMargins(0, 0, 0, 0);
//            params.gravity = Gravity.CENTER;
//            params.setMargins(10, 0, 10, 0);
//            pointView2.setText(2 + "");
//            pointView2.setBackgroundDrawable(drawable2);
//            pointView2.setLayoutParams(params);
//            views.add(pointView2);
//
//            addView(pointView2);
//
//            TextView pointView3 = new TextView(getContext());
//            params.setMargins(0, 0, 0, 0);
//            params.gravity = Gravity.CENTER;
//            params.setMargins(10, 0, 10, 0);
//            pointView3.setText(3 + "");
//            pointView3.setBackgroundDrawable(drawable3);
//            pointView3.setLayoutParams(params);
//            views.add(pointView3);
//
//            addView(pointView3);
            for (int i = 0; i < count; i++) {
                Drawable temp = myDrawable.getConstantState().newDrawable();
                TextView pointView = new TextView(getContext());
                LayoutParams params = new LayoutParams(UnitUtil.dip2px(context, 50)
                        , UnitUtil.dip2px(context, 50));
                params.setMargins(0, 0, 0, 0);
                params.gravity = Gravity.CENTER;
                params.setMargins(10, 0, 10, 0);
                pointView.setText(i + 1 + "");
                pointView.setBackgroundDrawable(temp);
                pointView.setLayoutParams(params);
                if (i == 0) {
                    pointView.setActivated(true);
                } else {
                    pointView.setActivated(false);
                }
                views.add(pointView);

                addView(pointView);
            }

            for (int i = 0; i < count; i++) {
                View view = views.get(i);
                if (view != null) {
                    LogUtils.w("view" + (i + 1) + ".isActivated = " + view.isActivated());
                }
            }


        }
    }

    public void setEnable(int index) {
        LogUtils.w("index = " + index);
        for (int i = 0; i < count; i++) {
            if (views != null && views.size() > i) {
                View view = views.get(i);
                if (i == index) {
                    view.setActivated(true);
                } else {
                    view.setActivated(false);
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public void enable1() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            views.get(0).setActivated(true);

        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public void disable1() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            views.get(0).setActivated(false);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public boolean getEnabled1() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            return views.get(0).isActivated();
        }
        return false;
    }

    public void enable2() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            views.get(1).setActivated(true);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public void disable2() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            views.get(1).setActivated(false);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public boolean getEnabled2() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            return views.get(1).isActivated();
        }
        return false;
    }

    public void enable3() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            views.get(2).setActivated(true);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public void disable3() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            views.get(2).setActivated(false);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            if (view != null) {
                sb.append("view" + (i + 1) + ".isActivated = " + view.isActivated());
                sb.append("\n");
            }
        }
        LogUtils.w(sb.toString());
    }

    public boolean getEnabled3() {
        if (views != null && !views.isEmpty() && views.get(0) != null) {
            return views.get(2).isActivated();
        }
        return false;
    }

    /**
     * 产生指示器的的状态颜色
     */
    private StateListDrawable createSelector() {
        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[]{android.R.attr.state_activated}, getResources().getDrawable(R.drawable.icon_weibo_push));
        bg.addState(new int[]{-android.R.attr.state_activated}, getResources().getDrawable(R.drawable.icon_weibo_pull));
        return bg;
    }


}
