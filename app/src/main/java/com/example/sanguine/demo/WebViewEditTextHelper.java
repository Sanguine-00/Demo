package com.example.sanguine.demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;


/**
 * @author : Shiyaozu
 * @version : [V1.0.0]
 * @date : 2017/9/11 0011
 * @desc : [该类主要用于解决模板页面Android软键盘遮住输入框的bug]
 */

public class WebViewEditTextHelper {

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private int navigationBarHeight = 0;
    private boolean myLayout = false;
    private Activity mActivity;
    private FrameLayout content;
    private String TAG = getClass().getSimpleName();

    private WebViewEditTextHelper(final Activity activity) {
        mActivity = activity;
        content = (FrameLayout) activity.findViewById(android.R.id.content);//

        int childCount = content.getChildCount();
        for (int i = childCount; i < childCount; i++) {
            View childAt = content.getChildAt(i);
            if (childAt != null) {
                childAt.setBackgroundColor(Color.YELLOW);
            }
        }
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
//                if (myLayout) {
//                    //手动布局的时候直接return
//                    myLayout = false;
//                    return;
//                }
                possiblyResizeChildOfContent(activity);
            }
        });


        try {
//            content.setForeground(mActivity.getResources().getDrawable(R.drawable.shape_white));
        } catch (Exception e) {
            e.printStackTrace();
        }


        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
        try {
            navigationBarHeight = new SystemBarTintManager(activity).getConfig().getNavigationBarHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void assistActivity(Activity activity) {
        new WebViewEditTextHelper(activity);
    }

    private void possiblyResizeChildOfContent(Activity activity) {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            int targetHeight = 0;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference + 75;//usableHeightSansKeyboard - heightDifference
                targetHeight = usableHeightSansKeyboard - heightDifference + 75;//usableHeightSansKeyboard - heightDifference

            } else {
                // keyboard probably just became hidden
                Rect r = new Rect();
                mChildOfContent.getWindowVisibleDisplayFrame(r);
                if (hasSoftKeys(activity.getWindowManager())) {
                    if (navigationBarHeight == 0) {
                        navigationBarHeight = UnitUtil.dip2px(activity, 43);
                    }
                    frameLayoutParams.height = usableHeightSansKeyboard - navigationBarHeight;
                    targetHeight = usableHeightSansKeyboard - navigationBarHeight;
                } else {
                    frameLayoutParams.height = usableHeightSansKeyboard;
                    targetHeight = usableHeightSansKeyboard;
                }

            }
            try {
//                mChildOfContent.setBackgroundColor(Color.YELLOW);
//                mChildOfContent.setForeground(mActivity.getResources().getDrawable(R.drawable.shape_white));
                mChildOfContent.invalidate();
//                LogUtils.tag(TAG).i("mChildOfContent的高度为" + mChildOfContent.getMeasuredHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
//            LogUtils.tag(TAG).i("content的高度为" + content.getMeasuredHeight());
            Animation animation = new ViewSizeChangeAnimation(mChildOfContent, targetHeight, frameLayoutParams.width);
            animation.setDuration(500);
            mChildOfContent.startAnimation(animation);

            //mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        int height = r.bottom - r.top;// 全屏模式下： return r.bottom
        return height;
    }

    /**
     * 判断虚拟导航栏是否开启
     *
     * @param windowManager
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean hasSoftKeys(WindowManager windowManager) {
        Display d = windowManager.getDefaultDisplay();
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        d.getRealMetrics(realDisplayMetrics);
        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);
        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;
        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }

    class ViewSizeChangeAnimation extends Animation {
        int initialHeight;
        int targetHeight;
        int initialWidth;
        int targetWidth;
        View view;

        public ViewSizeChangeAnimation(View view, int targetHeight, int targetWidth) {
            this.view = view;
            this.targetHeight = targetHeight;
            //this.targetWidth = targetWidth;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            view.getLayoutParams().height = initialHeight + (int) ((targetHeight - initialHeight) * interpolatedTime);
            //view.getLayoutParams().width = initialWidth + (int) ((targetWidth - initialWidth) * interpolatedTime);
            view.requestLayout();

        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            this.initialHeight = height;
            this.initialWidth = width;
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }


}
