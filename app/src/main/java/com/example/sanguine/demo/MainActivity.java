package com.example.sanguine.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.sanguine.demo.acp.Acp;
import com.example.sanguine.demo.acp.AcpListener;
import com.example.sanguine.demo.acp.AcpOptions;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkWebResourceRequest;
import org.xwalk.core.XWalkWebResourceResponse;

import java.io.IOException;
import java.util.List;

public class MainActivity extends XWalkActivity implements View.OnClickListener, SurfaceHolder.Callback {


    private XWalkView mXWalkView;
    private ViewGroup.LayoutParams layoutParams;
    private int oldHeight = 0;
    private int count = 0;
    private Button mButton;
    private Activity mActivity;
    private RelativeLayout mRlBrowser;
    private SurfaceView mSurfaceView;
    private String TAG = getClass().getSimpleName();
    private boolean isPreview = false;
    private Camera camera;
    private Handler handler;

    @Override
    protected void onXWalkReady() {
        mXWalkView.load("http://220.163.112.134:6713/mag/hls/7c7c19d095c349b9be3b5aed36e7dcc8/1/live.m3u8", null);
        mXWalkView.setBackgroundColor(Color.WHITE);
//        setZOrderMediaOverlay(mXWalkView);
//        mXWalkView.setBackgroundColor(Color.RED);
//        try {
//            mXWalkView.setZOrderOnTop(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void setZOrderMediaOverlay(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof SurfaceView) {
//                ((SurfaceView) (child)).setZOrderOnTop(false);
//                Log.d("setZOrderOnTop", "false");
//                ((SurfaceView) (child)).setZOrderMediaOverlay(true);
//                Log.d("setZOrderMediaOverlay", "true");
//                ((SurfaceView) (child)).setBackgroundColor(Color.RED);
                ((SurfaceView) (child)).setZOrderOnTop(true);
//                ((SurfaceView) (child)).setZOrderMediaOverlay(true);

            }
            if (child instanceof ViewGroup) {
                setZOrderMediaOverlay((ViewGroup) child);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        layoutParams = mSurfaceView.getLayoutParams();
//        oldHeight = mSurfaceView.getHeight();
        layoutParams = mXWalkView.getLayoutParams();
        oldHeight = layoutParams.height;
        AndroidBug5497Workaround.assistActivity(this);
    }

    private void initView() {
        handler = new Handler();
        mXWalkView = (XWalkView) findViewById(R.id.xwalk);
        mButton = (Button) findViewById(R.id.button);
        mRlBrowser = (RelativeLayout) findViewById(R.id.rl_browser);

        mXWalkView.setUIClient(new XWalkUIClient(mXWalkView) {

        });

        mXWalkView.setResourceClient(new XWalkResourceClient(mXWalkView) {
            @Override
            public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {
                super.onReceivedLoadError(view, errorCode, description, failingUrl);
                Log.e(TAG, "onReceivedLoadError:errorCode=" + errorCode + ",description=" + description + ",failingUrl" + failingUrl);
            }

            @Override
            public void onReceivedResponseHeaders(XWalkView view, XWalkWebResourceRequest request, XWalkWebResourceResponse response) {
                super.onReceivedResponseHeaders(view, request, response);
                Log.e(TAG, "onReceivedResponseHeaders" + "request=" + request + ",response=" + response);
            }

            @Override
            public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
                super.onReceivedSslError(view, callback, error);
                Log.e(TAG, "onReceivedSslError" + "callback=" + callback + ",error=" + error);

            }
        });


//        mXWalkView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
////                            String js = "javascript: var el = $(\":focus\").;\n" +
////                                    "el.value = \"\";\n";
//////                            String js = "javascript: var el = $(\":focus\");\n" +
//////                                    "var range = el.createTextRange();\n" +
//////                                    "el.selectionStart = -1;\n" +
//////                                    "el.selectionEnd = el.value.length;\n" +
//////                                    "var range = el.createTextRange();\n" +
//////                                    "range.moveStart('character', el.value.length);\n" +
//////                                    "range.collapse(false);\n" +
//////                                    "range.select();";
////                            mXWalkView.load(js, null);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, 500);
//                return false;
//            }
//        });
//        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
//        mSurfaceView.getHolder().addCallback(this);
    }

    public void button(View view) {
        if (count % 2 == 0) {
            oldHeight = 600;
        } else {
            oldHeight = 0;
        }
        count++;
//        Animation animation = new ViewSizeChangeAnimation(mSurfaceView, oldHeight, layoutParams.width);
//        animation.setDuration(500);
//        mSurfaceView.startAnimation(animation);
        Animation animation = new ViewSizeChangeAnimation(mXWalkView, oldHeight, layoutParams.width);
        animation.setDuration(1500);
        mXWalkView.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mXWalkView != null) {
            mXWalkView.onDestroy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mXWalkView != null) {
            mXWalkView.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (mXWalkView != null) {
            mXWalkView.onNewIntent(intent);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Acp.getInstance(mActivity).request(new AcpOptions.Builder().setPermissions(Manifest.permission.CAMERA).build(), new AcpListener() {
            @Override
            public void onGranted() {
                try {
                    camera = Camera.open();//打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera
                    WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);//得到窗口管理器
                    Display display = wm.getDefaultDisplay();//得到当前屏幕
                    Camera.Parameters parameters = camera.getParameters();//得到摄像头的参数
                    parameters.setPreviewSize(display.getWidth(), display.getHeight());//设置预览照片的大小
                    parameters.setPreviewFrameRate(3);//设置每秒3帧
                    parameters.setPictureFormat(PixelFormat.JPEG);//设置照片的格式
                    parameters.setJpegQuality(85);//设置照片的质量
                    parameters.setPictureSize(display.getHeight(), display.getWidth());//设置照片的大小，默认是和   屏幕一样大
//                    camera.setParameters(parameters);
                    camera.setPreviewDisplay(mSurfaceView.getHolder());//通过SurfaceView显示取景画面
                    camera.startPreview();//开始预览
                    isPreview = true;//设置是否预览参数为真
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }

            @Override
            public void onDenied(List<String> permissions) {

            }
        });

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            if (isPreview) {//如果正在预览
                camera.stopPreview();
                camera.release();
            }
        }
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
//            view.getLayoutParams().width = initialWidth + (int) ((targetWidth - initialWidth) * interpolatedTime);

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
