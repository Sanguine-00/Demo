package org.xwalk.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Sanguine on 2018/1/24.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private boolean mDrawFlag = true;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mDrawFlag = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        super.onDraw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        synchronized (this) {
            if (mDrawFlag) {
                Canvas canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    try {
                        canvas.drawColor(Color.BLUE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            getHolder().unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        synchronized (this) {
            if (mDrawFlag) {
                Canvas canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    try {
                        canvas.drawColor(Color.BLUE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            getHolder().unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        synchronized (this) {
            mDrawFlag = false;
        }
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        super.onDrawForeground(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        invalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
