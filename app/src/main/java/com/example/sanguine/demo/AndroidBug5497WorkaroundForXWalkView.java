package com.example.sanguine.demo;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import org.xwalk.core.XWalkView;

/**
 * @author ZGQ
 * @version [V1.0.0]
 * @date 2017/9/1
 * @desc [辅助类, 用于弹出软键盘时, 界面整体向上移动, 而不覆盖输入区域]
 * <p>
 * 作者：腾儿飞
 * 链接：http://www.jianshu.com/p/306482e17080
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class AndroidBug5497WorkaroundForXWalkView {
    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private XWalkView xWalkView;

    private AndroidBug5497WorkaroundForXWalkView(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    public static void assistActivity(Activity activity, XWalkView xWalkView) {
        AndroidBug5497WorkaroundForXWalkView androidBug5497WorkaroundForXWalkView = new AndroidBug5497WorkaroundForXWalkView(activity);
        androidBug5497WorkaroundForXWalkView.xWalkView = xWalkView;
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            if (xWalkView != null) {
                xWalkView.pauseTimers();
            }
            mChildOfContent.requestLayout();
            if (xWalkView != null) {
                xWalkView.resumeTimers();
            }
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);// 全屏模式下： return r.bottom
    }

}

