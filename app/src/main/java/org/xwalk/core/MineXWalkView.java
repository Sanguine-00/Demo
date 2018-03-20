package org.xwalk.core;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewParent;

/**
 * Created by Sanguine on 2018/3/13.
 */

public class MineXWalkView extends XWalkView {


    private ActionMode mActionMode;
    private ActionMode.Callback mSelectActionModeCallback;
    private GestureDetector mDetector;
    private Context context;

    public MineXWalkView(Context context) {
        super(context);
        this.context = context;
    }

    public MineXWalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MineXWalkView(Context context, Activity activity) {
        super(context, activity);
        this.context = context;
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ViewParent parent = getParent();
        if (parent == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            String name = callback.getClass().toString();
            if (name.contains("SelectActionModeCallback")) {
                mSelectActionModeCallback = callback;
                mDetector = new GestureDetector(context, new CustomGestureListener());
            }
        }
        CustomActionModeCallback mActionModeCallback = new CustomActionModeCallback();
        return parent.startActionModeForChild(this, mActionModeCallback);
    }

    private void getSelectedData() {

        String js = "(function getSelectedText() {" + "var txt;" + "if (window.getSelection) {" + "txt = window.getSelection().toString();" + "} else if (window.document.getSelection) {"
                + "txt = window.document.getSelection().toString();" + "} else if (window.document.selection) {" + "txt = window.document.selection.createRange().text;" + "}"
                + "JSInterface.getText(txt);" + "})()";
        // calling the js function
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript("javascript:" + js, null);
        } else {
            load("javascript:" + js, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Send the event to our gesture detector
        // If it is implemented, there will be a return value
        if (mDetector != null)
            return mDetector.onTouchEvent(event);
        // If the detected gesture is unimplemented, send it to the superclass
        return super.onTouchEvent(event);
    }

    private class CustomActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mActionMode = mode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e("actionmod", mode.getTitle() + "sss");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.e("onActionItemClicked", mode.getTitle() + "sss");
            return false;

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                clearFocus();
            } else {
                if (mSelectActionModeCallback != null) {
                    mSelectActionModeCallback.onDestroyActionMode(mode);
                }
                // 2016 edit by sight
                // 这里 置空 会报 ActionModeImpl.invalidate(ActionBarImpl.java:1012) 空异常
                //mActionMode = null;
            }
        }
    }

    private class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (mActionMode != null) {
                //update  by sight 2016.5.12
                //mActionMode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);

            Log.e("SSAASAD", e.getAction() + "");
        }

    }
}
