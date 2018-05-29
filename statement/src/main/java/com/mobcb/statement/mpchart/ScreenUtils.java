package com.mobcb.statement.mpchart;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 关于屏幕的一些参数获取
 * 
 * @author wanggh
 */
public class ScreenUtils {

	Display mDisplay;

	public ScreenUtils(Context context) {
		WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

		mDisplay = wm.getDefaultDisplay();

	}

	public float getScreenHeight() {
		if (mDisplay != null) {
			return mDisplay.getHeight();
		} else {
			return 0f;
		}
	}

	public float getScreenWidth() {
		if (mDisplay != null) {
			return mDisplay.getWidth();
		} else {
			return 0f;
		}
	}

	public int getStatusbarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sbar;
	}
}