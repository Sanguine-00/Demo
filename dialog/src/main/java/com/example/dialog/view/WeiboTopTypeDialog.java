package com.example.dialog.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dialog.R;
import com.example.library.UnitUtil;

import java.util.List;

/**
 * Created by Sanguine on 2018/2/9.
 */

public class WeiboTopTypeDialog extends Dialog implements View.OnClickListener, DialogInterface.OnShowListener {
    private List<String> list;

    private Context mContext;
    private LinearLayout mLlTagContent;
    private CallBack callBack;//点击item的回调
    private View mPlaceHolder;
    private View mHsvContent;
    private Handler mHanlder;
    private String choosen;

    public WeiboTopTypeDialog(@NonNull Context context, List<String> list, String choosen, CallBack callBack) {
        super(context, R.style.TopToBottomDialogStyle);
        this.list = list;
        this.choosen = choosen;
        this.mContext = context;
        this.callBack = callBack; // dialog 停留在顶部
        this.mHanlder = new Handler();
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) (UnitUtil.getScreenHeight(context) - UnitUtil.dip2px(context, 69));
        window.setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_weibo_top_type_dialog);
        initView();
        initData();
    }

    private void initView() {
        mLlTagContent = (LinearLayout) findViewById(R.id.ll_type_content);
        mHsvContent = findViewById(R.id.hsv_content);
        mPlaceHolder = findViewById(R.id.place_holder);
        mPlaceHolder.setOnClickListener(this);
    }

    @Override
    public void show() {
        super.show();
        if (mHsvContent != null) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mHsvContent, "translationY", -mHsvContent.getHeight(), -mHsvContent.getHeight() / 2, 0);
            objectAnimator.setDuration(300);
            objectAnimator.start();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mHanlder.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(mContext, "run方法中线程是:" + Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
//                if (mHsvContent != null) {
//                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mHsvContent, "translationY", -mHsvContent.getHeight(), -mHsvContent.getHeight() / 2, 0);
//                    objectAnimator.setDuration(1500);
//                    objectAnimator.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            Toast.makeText(mContext, "动画结束", Toast.LENGTH_SHORT).show();
//                            super.onAnimationEnd(animation);
//                        }
//
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//                            Toast.makeText(mContext, "动画开始", Toast.LENGTH_SHORT).show();
//                            super.onAnimationStart(animation);
//                        }
//
//                        @Override
//                        public void onAnimationStart(Animator animation, boolean isReverse) {
//                            Toast.makeText(mContext, "动画开始", Toast.LENGTH_SHORT).show();
//                            super.onAnimationStart(animation);
//
//                        }
//                    });
//                    objectAnimator.start();
//                }
//            }
//        });
    }

    @Override
    public void cancel() {
        if (mHsvContent != null) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mHsvContent, "translationY", 0, -mHsvContent.getHeight() / 2, -mHsvContent.getHeight());
            objectAnimator.setDuration(300);
            objectAnimator.start();
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    WeiboTopTypeDialog.super.cancel();
                }
            });
        } else {
            super.cancel();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
    }

    private void initData() {
        setOnShowListener(null);
        mLlTagContent.removeAllViews();
        if (list != null && list.size() > 0) {
            for (final String tag : list) {
                if (tag != null) {
                    final RadioButton view = (RadioButton) LayoutInflater.from(mContext).inflate(R.layout.view_weibo_top_type_dialog_item, null, false);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (callBack != null) {
                                callBack.onItemClick(tag);
                            }
                            cancel();
                            choosen = tag;
                            initData();
                        }
                    });
                    if (choosen != null && choosen.equals(tag)) {
                        view.setChecked(true);
                    } else {
                        view.setChecked(false);
                    }
                    view.setText(tag);
                    view.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams params =
                            new LinearLayout.LayoutParams(view.getMeasuredWidth(), view.getMeasuredHeight());
                    params.width = UnitUtil.dip2px(mContext, 75);
                    params.height = UnitUtil.dip2px(mContext, 30);
                    params.gravity = Gravity.CENTER_VERTICAL;
                    params.setMargins(0, 0, UnitUtil.dip2px(mContext, 11.5f), 0);
                    view.setLayoutParams(params);
                    mLlTagContent.addView(view);
                }
            }
        }
        setCancelable(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.place_holder) {
            cancel();
        }
    }

    public void setChoosen(String choosen) {
        this.choosen = choosen;
    }

    @Override
    public void onShow(DialogInterface dialog) {
//        if (mHsvContent != null) {
//            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mHsvContent, "translationY", -mHsvContent.getHeight(), -mHsvContent.getHeight() / 2, 0);
//            objectAnimator.setDuration(1500);
//            objectAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    Toast.makeText(mContext, "动画结束", Toast.LENGTH_SHORT).show();
//                    super.onAnimationEnd(animation);
//                }
//
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    Toast.makeText(mContext, "动画开始", Toast.LENGTH_SHORT).show();
//                    super.onAnimationStart(animation);
//                }
//
//                @Override
//                public void onAnimationStart(Animator animation, boolean isReverse) {
//                    Toast.makeText(mContext, "动画开始", Toast.LENGTH_SHORT).show();
//                    super.onAnimationStart(animation);
//
//                }
//            });
//            objectAnimator.start();
//        }
    }

    public static interface CallBack {
        void onItemClick(String tagInfoResult);
    }
}
