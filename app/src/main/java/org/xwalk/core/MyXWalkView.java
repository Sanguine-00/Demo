package org.xwalk.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * Created by Sanguine on 2018/1/23.
 */

public class MyXWalkView extends MyFrameLayout {
    public static final int RELOAD_NORMAL = 0;
    public static final int RELOAD_IGNORE_CACHE = 1;
    private ArrayList<Object> constructorTypes;
    private ArrayList<Object> constructorParams;
    private ReflectMethod postWrapperMethod;
    private XWalkCoreWrapper coreWrapper;
    private Object bridge;
    private ReflectMethod loadStringStringMethod = new ReflectMethod((Class) null, "load", new Class[0]);
    private ReflectMethod loadAppFromManifestStringStringMethod = new ReflectMethod((Class) null, "loadAppFromManifest", new Class[0]);
    private ReflectMethod reloadintMethod = new ReflectMethod((Class) null, "reload", new Class[0]);
    private ReflectMethod stopLoadingMethod = new ReflectMethod((Class) null, "stopLoading", new Class[0]);
    private ReflectMethod getUrlMethod = new ReflectMethod((Class) null, "getUrl", new Class[0]);
    private ReflectMethod getTitleMethod = new ReflectMethod((Class) null, "getTitle", new Class[0]);
    private ReflectMethod getOriginalUrlMethod = new ReflectMethod((Class) null, "getOriginalUrl", new Class[0]);
    private ReflectMethod getNavigationHistoryMethod = new ReflectMethod((Class) null, "getNavigationHistory", new Class[0]);
    private ReflectMethod addJavascriptInterfaceObjectStringMethod = new ReflectMethod((Class) null, "addJavascriptInterface", new Class[0]);
    private ReflectMethod evaluateJavascriptStringValueCallbackMethod = new ReflectMethod((Class) null, "evaluateJavascript", new Class[0]);
    private ReflectMethod clearCachebooleanMethod = new ReflectMethod((Class) null, "clearCache", new Class[0]);
    private ReflectMethod clearCacheForSingleFileStringMethod = new ReflectMethod((Class) null, "clearCacheForSingleFile", new Class[0]);
    private ReflectMethod hasEnteredFullscreenMethod = new ReflectMethod((Class) null, "hasEnteredFullscreen", new Class[0]);
    private ReflectMethod leaveFullscreenMethod = new ReflectMethod((Class) null, "leaveFullscreen", new Class[0]);
    private ReflectMethod pauseTimersMethod = new ReflectMethod((Class) null, "pauseTimers", new Class[0]);
    private ReflectMethod resumeTimersMethod = new ReflectMethod((Class) null, "resumeTimers", new Class[0]);
    private ReflectMethod onHideMethod = new ReflectMethod((Class) null, "onHide", new Class[0]);
    private ReflectMethod onShowMethod = new ReflectMethod((Class) null, "onShow", new Class[0]);
    private ReflectMethod onDestroyMethod = new ReflectMethod((Class) null, "onDestroy", new Class[0]);
    private ReflectMethod getExtensionManagerMethod = new ReflectMethod((Class) null, "getExtensionManager", new Class[0]);
    private ReflectMethod startActivityForResultIntentintBundleMethod = new ReflectMethod((Class) null, "startActivityForResult", new Class[0]);
    private ReflectMethod onActivityResultintintIntentMethod = new ReflectMethod((Class) null, "onActivityResult", new Class[0]);
    private ReflectMethod onNewIntentIntentMethod = new ReflectMethod((Class) null, "onNewIntent", new Class[0]);
    private ReflectMethod saveStateBundleMethod = new ReflectMethod((Class) null, "saveState", new Class[0]);
    private ReflectMethod restoreStateBundleMethod = new ReflectMethod((Class) null, "restoreState", new Class[0]);
    private ReflectMethod getAPIVersionMethod = new ReflectMethod((Class) null, "getAPIVersion", new Class[0]);
    private ReflectMethod getXWalkVersionMethod = new ReflectMethod((Class) null, "getXWalkVersion", new Class[0]);
    private ReflectMethod setUIClientXWalkUIClientInternalMethod = new ReflectMethod((Class) null, "setUIClient", new Class[0]);
    private ReflectMethod setResourceClientXWalkResourceClientInternalMethod = new ReflectMethod((Class) null, "setResourceClient", new Class[0]);
    private ReflectMethod setBackgroundColorintMethod = new ReflectMethod((Class) null, "setBackgroundColor", new Class[0]);
    private ReflectMethod setUserAgentStringStringMethod = new ReflectMethod((Class) null, "setUserAgentString", new Class[0]);
    private ReflectMethod getUserAgentStringMethod = new ReflectMethod((Class) null, "getUserAgentString", new Class[0]);
    private ReflectMethod setAcceptLanguagesStringMethod = new ReflectMethod((Class) null, "setAcceptLanguages", new Class[0]);
    private ReflectMethod captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod = new ReflectMethod((Class) null, "captureBitmapAsync", new Class[0]);
    private ReflectMethod getSettingsMethod = new ReflectMethod((Class) null, "getSettings", new Class[0]);
    private ReflectMethod setNetworkAvailablebooleanMethod = new ReflectMethod((Class) null, "setNetworkAvailable", new Class[0]);
    private ReflectMethod getRemoteDebuggingUrlMethod = new ReflectMethod((Class) null, "getRemoteDebuggingUrl", new Class[0]);
    private ReflectMethod zoomInMethod = new ReflectMethod((Class) null, "zoomIn", new Class[0]);
    private ReflectMethod zoomOutMethod = new ReflectMethod((Class) null, "zoomOut", new Class[0]);
    private ReflectMethod zoomByfloatMethod = new ReflectMethod((Class) null, "zoomBy", new Class[0]);
    private ReflectMethod canZoomInMethod = new ReflectMethod((Class) null, "canZoomIn", new Class[0]);
    private ReflectMethod canZoomOutMethod = new ReflectMethod((Class) null, "canZoomOut", new Class[0]);
    private ReflectMethod onCreateInputConnectionEditorInfoMethod = new ReflectMethod((Class) null, "onCreateInputConnection", new Class[0]);
    private ReflectMethod setInitialScaleintMethod = new ReflectMethod((Class) null, "setInitialScale", new Class[0]);
    private ReflectMethod setZOrderOnTopbooleanMethod = new ReflectMethod((Class) null, "setZOrderOnTop", new Class[0]);
    private ReflectMethod clearFormDataMethod = new ReflectMethod((Class) null, "clearFormData", new Class[0]);
    private ReflectMethod setSurfaceViewVisibilityintMethod = new ReflectMethod((Class) null, "setSurfaceViewVisibility", new Class[0]);
    private ReflectMethod setDownloadListenerXWalkDownloadListenerInternalMethod = new ReflectMethod((Class) null, "setDownloadListener", new Class[0]);
    private ReflectMethod onTouchEventMotionEventMethod = new ReflectMethod((Class) null, "onTouchEvent", new Class[0]);
    private ReflectMethod setOnTouchListenerOnTouchListenerMethod = new ReflectMethod((Class) null, "setOnTouchListener", new Class[0]);
    private ReflectMethod scrollTointintMethod = new ReflectMethod((Class) null, "scrollTo", new Class[0]);
    private ReflectMethod scrollByintintMethod = new ReflectMethod((Class) null, "scrollBy", new Class[0]);
    private String TAG = getClass().getSimpleName();

    public MyXWalkView(Context context) {
        super(context, (AttributeSet) null);
        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
        this.addView(surfaceView);
        this.constructorTypes = new ArrayList();
        this.constructorTypes.add(Context.class);
        this.constructorParams = new ArrayList();
        this.constructorParams.add(context);
        this.postWrapperMethod = new ReflectMethod(this, "postXWalkViewInternalContextConstructor", new Class[0]);
        this.reflectionInit();
    }

    public MyXWalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!this.isInEditMode()) {
            SurfaceView surfaceView = new SurfaceView(context);
            surfaceView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
            this.addView(surfaceView);
            this.constructorTypes = new ArrayList();
            this.constructorTypes.add(Context.class);
            this.constructorTypes.add(AttributeSet.class);
            this.constructorParams = new ArrayList();
            this.constructorParams.add(context);
            this.constructorParams.add(attrs);
            this.postWrapperMethod = new ReflectMethod(this, "postXWalkViewInternalContextAttributeSetConstructor", new Class[0]);
            this.reflectionInit();
        }
    }

    public MyXWalkView(Context context, Activity activity) {
        super(context, (AttributeSet) null);
        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
        this.addView(surfaceView);
        this.constructorTypes = new ArrayList();
        this.constructorTypes.add(Context.class);
        this.constructorTypes.add(Activity.class);
        this.constructorParams = new ArrayList();
        this.constructorParams.add(context);
        this.constructorParams.add(activity);
        this.postWrapperMethod = new ReflectMethod(this, "postXWalkViewInternalContextActivityConstructor", new Class[0]);
        this.reflectionInit();
    }

    Object getBridge() {
        return this.bridge;
    }

    public void postXWalkViewInternalContextConstructor() {
        this.addView((FrameLayout) this.bridge, new android.widget.FrameLayout.LayoutParams(-1, -1));
        this.removeViewAt(0);
    }

    public void postXWalkViewInternalContextAttributeSetConstructor() {
        this.addView((FrameLayout) this.bridge, new android.widget.FrameLayout.LayoutParams(-1, -1));
        this.removeViewAt(0);
    }

    public void postXWalkViewInternalContextActivityConstructor() {
        this.addView((FrameLayout) this.bridge, new android.widget.FrameLayout.LayoutParams(-1, -1));
        this.removeViewAt(0);
    }

    public void load(String url, String content) {
        this.loadStringStringMethod.invoke(new Object[]{url, content});
    }

    public void loadAppFromManifest(String url, String content) {
        this.loadAppFromManifestStringStringMethod.invoke(new Object[]{url, content});
    }

    public void reload(int mode) {
        this.reloadintMethod.invoke(new Object[]{Integer.valueOf(mode)});
    }

    public void stopLoading() {
        this.stopLoadingMethod.invoke(new Object[0]);
    }

    public String getUrl() {
        return (String) this.getUrlMethod.invoke(new Object[0]);
    }

    public String getTitle() {
        return (String) this.getTitleMethod.invoke(new Object[0]);
    }

    public String getOriginalUrl() {
        return (String) this.getOriginalUrlMethod.invoke(new Object[0]);
    }

    public XWalkNavigationHistory getNavigationHistory() {
        return (XWalkNavigationHistory) this.coreWrapper.getWrapperObject(this.getNavigationHistoryMethod.invoke(new Object[0]));
    }

    public void addJavascriptInterface(Object object, String name) {
        this.addJavascriptInterfaceObjectStringMethod.invoke(new Object[]{object, name});
    }

    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        this.evaluateJavascriptStringValueCallbackMethod.invoke(new Object[]{script, callback});
    }

    public void clearCache(boolean includeDiskFiles) {
        this.clearCachebooleanMethod.invoke(new Object[]{Boolean.valueOf(includeDiskFiles)});
    }

    public void clearCacheForSingleFile(String url) {
        this.clearCacheForSingleFileStringMethod.invoke(new Object[]{url});
    }

    public boolean hasEnteredFullscreen() {
        return ((Boolean) this.hasEnteredFullscreenMethod.invoke(new Object[0])).booleanValue();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        super.onDraw(canvas);
    }

    public void leaveFullscreen() {
        this.leaveFullscreenMethod.invoke(new Object[0]);
    }

    public void pauseTimers() {
        this.pauseTimersMethod.invoke(new Object[0]);
    }

    public void resumeTimers() {
        this.resumeTimersMethod.invoke(new Object[0]);
    }

    public void onHide() {
        this.onHideMethod.invoke(new Object[0]);
    }

    public void onShow() {
        this.onShowMethod.invoke(new Object[0]);
    }

    public void onDestroy() {
        this.onDestroyMethod.invoke(new Object[0]);
    }

    public XWalkExternalExtensionManager getExtensionManager() {
        return (XWalkExternalExtensionManager) this.coreWrapper.getWrapperObject(this.getExtensionManagerMethod.invoke(new Object[0]));
    }

    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        this.startActivityForResultIntentintBundleMethod.invoke(new Object[]{intent, Integer.valueOf(requestCode), options});
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.onActivityResultintintIntentMethod.invoke(new Object[]{Integer.valueOf(requestCode), Integer.valueOf(resultCode), data});
    }

    public boolean onNewIntent(Intent intent) {
        return ((Boolean) this.onNewIntentIntentMethod.invoke(new Object[]{intent})).booleanValue();
    }

    public boolean saveState(Bundle outState) {
        return ((Boolean) this.saveStateBundleMethod.invoke(new Object[]{outState})).booleanValue();
    }

    public boolean restoreState(Bundle inState) {
        return ((Boolean) this.restoreStateBundleMethod.invoke(new Object[]{inState})).booleanValue();
    }

    public String getAPIVersion() {
        return (String) this.getAPIVersionMethod.invoke(new Object[0]);
    }

    public String getXWalkVersion() {
        return (String) this.getXWalkVersionMethod.invoke(new Object[0]);
    }

    public void setUIClient(XWalkUIClient client) {
        if (this.setUIClientXWalkUIClientInternalMethod.isNull()) {
            this.setUIClientXWalkUIClientInternalMethod.setArguments(new Object[]{new ReflectMethod(client, "getBridge", new Class[0])});
            XWalkCoreWrapper.reserveReflectMethod(this.setUIClientXWalkUIClientInternalMethod);
        } else {
            this.setUIClientXWalkUIClientInternalMethod.invoke(new Object[]{client.getBridge()});
        }
    }

    public void setResourceClient(XWalkResourceClient client) {
        if (this.setResourceClientXWalkResourceClientInternalMethod.isNull()) {
            this.setResourceClientXWalkResourceClientInternalMethod.setArguments(new Object[]{new ReflectMethod(client, "getBridge", new Class[0])});
            XWalkCoreWrapper.reserveReflectMethod(this.setResourceClientXWalkResourceClientInternalMethod);
        } else {
            this.setResourceClientXWalkResourceClientInternalMethod.invoke(new Object[]{client.getBridge()});
        }
    }

    public void setBackgroundColor(int color) {
        this.setBackgroundColorintMethod.invoke(new Object[]{Integer.valueOf(color)});
    }

    public void setLayerType(int layerType, Paint paint) {
    }

    public String getUserAgentString() {
        return (String) this.getUserAgentStringMethod.invoke(new Object[0]);
    }

    public void setUserAgentString(String userAgent) {
        this.setUserAgentStringStringMethod.invoke(new Object[]{userAgent});
    }

    public void setAcceptLanguages(String acceptLanguages) {
        this.setAcceptLanguagesStringMethod.invoke(new Object[]{acceptLanguages});
    }

    public void captureBitmapAsync(XWalkGetBitmapCallback callback) {
        this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod.invoke(new Object[]{callback.getBridge()});
    }

    public XWalkSettings getSettings() {
        return (XWalkSettings) this.coreWrapper.getWrapperObject(this.getSettingsMethod.invoke(new Object[0]));
    }

    public void setNetworkAvailable(boolean networkUp) {
        this.setNetworkAvailablebooleanMethod.invoke(new Object[]{Boolean.valueOf(networkUp)});
    }

    public Uri getRemoteDebuggingUrl() {
        return (Uri) this.getRemoteDebuggingUrlMethod.invoke(new Object[0]);
    }

    public boolean zoomIn() {
        return ((Boolean) this.zoomInMethod.invoke(new Object[0])).booleanValue();
    }

    public boolean zoomOut() {
        return ((Boolean) this.zoomOutMethod.invoke(new Object[0])).booleanValue();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void zoomBy(float factor) {
        this.zoomByfloatMethod.invoke(new Object[]{Float.valueOf(factor)});
    }

    public boolean canZoomIn() {
        return ((Boolean) this.canZoomInMethod.invoke(new Object[0])).booleanValue();
    }

    public boolean canZoomOut() {
        return ((Boolean) this.canZoomOutMethod.invoke(new Object[0])).booleanValue();
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return (InputConnection) this.onCreateInputConnectionEditorInfoMethod.invoke(new Object[]{outAttrs});
    }

    public void setInitialScale(int scaleInPercent) {
        this.setInitialScaleintMethod.invoke(new Object[]{Integer.valueOf(scaleInPercent)});
    }

    public void setZOrderOnTop(boolean onTop) {
        this.setZOrderOnTopbooleanMethod.invoke(new Object[]{Boolean.valueOf(onTop)});
    }

    public void clearFormData() {
        this.clearFormDataMethod.invoke(new Object[0]);
    }

    public void setVisibility(int visibility) {
        if (visibility == 4) {
            visibility = 8;
        }

        super.setVisibility(visibility);
        this.setSurfaceViewVisibility(visibility);
    }

    public void setSurfaceViewVisibility(int visibility) {
        if (this.setSurfaceViewVisibilityintMethod.isNull()) {
            this.setSurfaceViewVisibilityintMethod.setArguments(new Object[]{Integer.valueOf(visibility)});
            XWalkCoreWrapper.reserveReflectMethod(this.setSurfaceViewVisibilityintMethod);
        } else {
            this.setSurfaceViewVisibilityintMethod.invoke(new Object[]{Integer.valueOf(visibility)});
        }
    }

    public void setDownloadListener(XWalkDownloadListener listener) {
        this.setDownloadListenerXWalkDownloadListenerInternalMethod.invoke(new Object[]{listener.getBridge()});
    }

    private boolean performLongClickDelegate() {
        return this.performLongClick();
    }

    private boolean onTouchEventDelegate(MotionEvent event) {
        return this.onTouchEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return ((Boolean) this.onTouchEventMotionEventMethod.invoke(new Object[]{event})).booleanValue();
    }

    private void onScrollChangedDelegate(int l, int t, int oldl, int oldt) {
        this.onScrollChanged(l, t, oldl, oldt);
    }

    private void onFocusChangedDelegate(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        this.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    public void setOnTouchListener(View.OnTouchListener l) {
        this.setOnTouchListenerOnTouchListenerMethod.invoke(new Object[]{l});
    }

    public void scrollTo(int x, int y) {
        this.scrollTointintMethod.invoke(new Object[]{Integer.valueOf(x), Integer.valueOf(y)});
    }

    public void scrollBy(int x, int y) {
        this.scrollByintintMethod.invoke(new Object[]{Integer.valueOf(x), Integer.valueOf(y)});
    }

    void reflectionInit() {
        XWalkCoreWrapper.initEmbeddedMode();
        this.coreWrapper = XWalkCoreWrapper.getInstance();
        if (this.coreWrapper == null) {
            XWalkCoreWrapper.reserveReflectObject(this);
        } else {
            int length = this.constructorTypes.size();
            Class<?>[] paramTypes = new Class[length + 1];

            for (int i = 0; i < length; ++i) {
                Object type = this.constructorTypes.get(i);
                if (type instanceof String) {
                    paramTypes[i] = this.coreWrapper.getBridgeClass((String) type);
                    this.constructorParams.set(i, this.coreWrapper.getBridgeObject(this.constructorParams.get(i)));
                } else if (type instanceof Class) {
                    paramTypes[i] = (Class) type;
                } else {
                    assert false;
                }
            }

            paramTypes[length] = Object.class;
            this.constructorParams.add(this);
            ReflectConstructor constructor = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkViewBridge"), paramTypes);
            this.bridge = constructor.newInstance(this.constructorParams.toArray());
            if (this.postWrapperMethod != null) {
                this.postWrapperMethod.invoke(new Object[0]);
            }

            this.loadStringStringMethod.init(this.bridge, (Class) null, "loadSuper", new Class[]{String.class, String.class});
            this.loadAppFromManifestStringStringMethod.init(this.bridge, (Class) null, "loadAppFromManifestSuper", new Class[]{String.class, String.class});
            this.reloadintMethod.init(this.bridge, (Class) null, "reloadSuper", new Class[]{Integer.TYPE});
            this.stopLoadingMethod.init(this.bridge, (Class) null, "stopLoadingSuper", new Class[0]);
            this.getUrlMethod.init(this.bridge, (Class) null, "getUrlSuper", new Class[0]);
            this.getTitleMethod.init(this.bridge, (Class) null, "getTitleSuper", new Class[0]);
            this.getOriginalUrlMethod.init(this.bridge, (Class) null, "getOriginalUrlSuper", new Class[0]);
            this.getNavigationHistoryMethod.init(this.bridge, (Class) null, "getNavigationHistorySuper", new Class[0]);
            this.addJavascriptInterfaceObjectStringMethod.init(this.bridge, (Class) null, "addJavascriptInterfaceSuper", new Class[]{Object.class, String.class});
            this.evaluateJavascriptStringValueCallbackMethod.init(this.bridge, (Class) null, "evaluateJavascriptSuper", new Class[]{String.class, ValueCallback.class});
            this.clearCachebooleanMethod.init(this.bridge, (Class) null, "clearCacheSuper", new Class[]{Boolean.TYPE});
            this.clearCacheForSingleFileStringMethod.init(this.bridge, (Class) null, "clearCacheForSingleFileSuper", new Class[]{String.class});
            this.hasEnteredFullscreenMethod.init(this.bridge, (Class) null, "hasEnteredFullscreenSuper", new Class[0]);
            this.leaveFullscreenMethod.init(this.bridge, (Class) null, "leaveFullscreenSuper", new Class[0]);
            this.pauseTimersMethod.init(this.bridge, (Class) null, "pauseTimersSuper", new Class[0]);
            this.resumeTimersMethod.init(this.bridge, (Class) null, "resumeTimersSuper", new Class[0]);
            this.onHideMethod.init(this.bridge, (Class) null, "onHideSuper", new Class[0]);
            this.onShowMethod.init(this.bridge, (Class) null, "onShowSuper", new Class[0]);
            this.onDestroyMethod.init(this.bridge, (Class) null, "onDestroySuper", new Class[0]);
            this.getExtensionManagerMethod.init(this.bridge, (Class) null, "getExtensionManagerSuper", new Class[0]);
            this.startActivityForResultIntentintBundleMethod.init(this.bridge, (Class) null, "startActivityForResultSuper", new Class[]{Intent.class, Integer.TYPE, Bundle.class});
            this.onActivityResultintintIntentMethod.init(this.bridge, (Class) null, "onActivityResultSuper", new Class[]{Integer.TYPE, Integer.TYPE, Intent.class});
            this.onNewIntentIntentMethod.init(this.bridge, (Class) null, "onNewIntentSuper", new Class[]{Intent.class});
            this.saveStateBundleMethod.init(this.bridge, (Class) null, "saveStateSuper", new Class[]{Bundle.class});
            this.restoreStateBundleMethod.init(this.bridge, (Class) null, "restoreStateSuper", new Class[]{Bundle.class});
            this.getAPIVersionMethod.init(this.bridge, (Class) null, "getAPIVersionSuper", new Class[0]);
            this.getXWalkVersionMethod.init(this.bridge, (Class) null, "getXWalkVersionSuper", new Class[0]);
            this.setUIClientXWalkUIClientInternalMethod.init(this.bridge, (Class) null, "setUIClientSuper", new Class[]{this.coreWrapper.getBridgeClass("XWalkUIClientBridge")});
            this.setResourceClientXWalkResourceClientInternalMethod.init(this.bridge, (Class) null, "setResourceClientSuper", new Class[]{this.coreWrapper.getBridgeClass("XWalkResourceClientBridge")});
            this.setBackgroundColorintMethod.init(this.bridge, (Class) null, "setBackgroundColorSuper", new Class[]{Integer.TYPE});
            this.setUserAgentStringStringMethod.init(this.bridge, (Class) null, "setUserAgentStringSuper", new Class[]{String.class});
            this.getUserAgentStringMethod.init(this.bridge, (Class) null, "getUserAgentStringSuper", new Class[0]);
            this.setAcceptLanguagesStringMethod.init(this.bridge, (Class) null, "setAcceptLanguagesSuper", new Class[]{String.class});
            this.captureBitmapAsyncXWalkGetBitmapCallbackInternalMethod.init(this.bridge, (Class) null, "captureBitmapAsyncSuper", new Class[]{this.coreWrapper.getBridgeClass("XWalkGetBitmapCallbackBridge")});
            this.getSettingsMethod.init(this.bridge, (Class) null, "getSettingsSuper", new Class[0]);
            this.setNetworkAvailablebooleanMethod.init(this.bridge, (Class) null, "setNetworkAvailableSuper", new Class[]{Boolean.TYPE});
            this.getRemoteDebuggingUrlMethod.init(this.bridge, (Class) null, "getRemoteDebuggingUrlSuper", new Class[0]);
            this.zoomInMethod.init(this.bridge, (Class) null, "zoomInSuper", new Class[0]);
            this.zoomOutMethod.init(this.bridge, (Class) null, "zoomOutSuper", new Class[0]);
            this.zoomByfloatMethod.init(this.bridge, (Class) null, "zoomBySuper", new Class[]{Float.TYPE});
            this.canZoomInMethod.init(this.bridge, (Class) null, "canZoomInSuper", new Class[0]);
            this.canZoomOutMethod.init(this.bridge, (Class) null, "canZoomOutSuper", new Class[0]);
            this.onCreateInputConnectionEditorInfoMethod.init(this.bridge, (Class) null, "onCreateInputConnectionSuper", new Class[]{EditorInfo.class});
            this.setInitialScaleintMethod.init(this.bridge, (Class) null, "setInitialScaleSuper", new Class[]{Integer.TYPE});
            this.setZOrderOnTopbooleanMethod.init(this.bridge, (Class) null, "setZOrderOnTopSuper", new Class[]{Boolean.TYPE});
            this.clearFormDataMethod.init(this.bridge, (Class) null, "clearFormDataSuper", new Class[0]);
            this.setSurfaceViewVisibilityintMethod.init(this.bridge, (Class) null, "setSurfaceViewVisibilitySuper", new Class[]{Integer.TYPE});
            this.setDownloadListenerXWalkDownloadListenerInternalMethod.init(this.bridge, (Class) null, "setDownloadListenerSuper", new Class[]{this.coreWrapper.getBridgeClass("XWalkDownloadListenerBridge")});
            this.onTouchEventMotionEventMethod.init(this.bridge, (Class) null, "onTouchEventSuper", new Class[]{MotionEvent.class});
            this.setOnTouchListenerOnTouchListenerMethod.init(this.bridge, (Class) null, "setOnTouchListenerSuper", new Class[]{View.OnTouchListener.class});
            this.scrollTointintMethod.init(this.bridge, (Class) null, "scrollToSuper", new Class[]{Integer.TYPE, Integer.TYPE});
            this.scrollByintintMethod.init(this.bridge, (Class) null, "scrollBySuper", new Class[]{Integer.TYPE, Integer.TYPE});
        }
    }
}
