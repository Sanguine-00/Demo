//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.xwalk.core;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import junit.framework.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MyXWalkLibraryLoader {
//    private static final String DEFAULT_DOWNLOAD_FILE_NAME = "xwalk_download.tmp";
//    private static final String DOWNLOAD_WITHOUT_NOTIFICATION = "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION";
//    private static final String TAG = "XWalkLib";
//    private static AsyncTask<Void, Integer, Integer> sActiveTask;
//
//    MyXWalkLibraryLoader() {
//    }
//
//    public static boolean isDecompressed(Context context) {
//        return XWalkLibraryDecompressor.isDecompressed(context);
//    }
//
//    public static boolean isSharedLibrary() {
//        return XWalkCoreWrapper.getInstance().isSharedMode();
//    }
//
//    public static boolean isLibraryReady() {
//        return XWalkCoreWrapper.getInstance() != null;
//    }
//
//    public static int getLibraryStatus() {
//        return XWalkCoreWrapper.getCoreStatus();
//    }
//
//    public static void prepareToInit(Activity activity) {
//        XWalkCoreWrapper.handlePreInit(activity.getClass().getName());
//    }
//
//    public static void startDecompress(MyXWalkLibraryLoader.DecompressListener listener, Context context) {
//        (new MyXWalkLibraryLoader.DecompressTask(listener, context)).execute(new Void[0]);
//    }
//
//    public static boolean cancelDecompress() {
//        MyXWalkLibraryLoader.DecompressTask task = (MyXWalkLibraryLoader.DecompressTask)sActiveTask;
//        return task != null && task.cancel(true);
//    }
//
//    public static void startActivate(MyXWalkLibraryLoader.ActivateListener listener, Activity activity) {
//        (new MyXWalkLibraryLoader.ActivateTask(listener, activity)).execute(new Void[0]);
//    }
//
//    public static void startDownload(MyXWalkLibraryLoader.DownloadListener listener, Context context, String url) {
//        (new MyXWalkLibraryLoader.DownloadTask(listener, context, url)).execute(new Void[0]);
//    }
//
//    public static boolean cancelDownload() {
//        MyXWalkLibraryLoader.DownloadTask task = (MyXWalkLibraryLoader.DownloadTask)sActiveTask;
//        return task != null && task.cancel(true);
//    }
//
//    private static class DownloadTask extends AsyncTask<Void, Integer, Integer> {
//        private static final int QUERY_INTERVAL_MS = 100;
//        private static final int MAX_PAUSED_COUNT = 6000;
//        private MyXWalkLibraryLoader.DownloadListener mListener;
//        private Context mContext;
//        private String mDownloadUrl;
//        private DownloadManager mDownloadManager;
//        private long mDownloadId;
//
//        DownloadTask(MyXWalkLibraryLoader.DownloadListener listener, Context context, String url) {
//            this.mListener = listener;
//            this.mContext = context;
//            this.mDownloadUrl = url;
//            this.mDownloadManager = (DownloadManager)context.getSystemService("download");
//        }
//
//        protected void onPreExecute() {
//            Log.d("XWalkLib", "DownloadTask started, " + this.mDownloadUrl);
//            MyXWalkLibraryLoader.sActiveTask = this;
//            String savedFile = "xwalk_download.tmp";
//
//            try {
//                String downloadDir = (new File((new URL(this.mDownloadUrl)).getPath())).getName();
//                if(!downloadDir.isEmpty()) {
//                    savedFile = downloadDir;
//                }
//            } catch (NullPointerException | MalformedURLException var5) {
//                Log.e("XWalkLib", "Invalid download URL " + this.mDownloadUrl);
//                this.mDownloadUrl = null;
//                return;
//            }
//
//            File downloadDir1 = this.mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//            File downloadFile = new File(downloadDir1, savedFile);
//            if(downloadFile.isFile()) {
//                downloadFile.delete();
//            }
//
//            Request request = new Request(Uri.parse(this.mDownloadUrl));
//            request.setDestinationInExternalFilesDir(this.mContext, Environment.DIRECTORY_DOWNLOADS, savedFile);
//            if(this.isSilentDownload()) {
//                request.setNotificationVisibility(2);
//            }
//
//            this.mDownloadId = this.mDownloadManager.enqueue(request);
//            this.mListener.onDownloadStarted();
//        }
//
//        protected Integer doInBackground(Void... params) {
//            if(this.mDownloadUrl == null) {
//                return Integer.valueOf(16);
//            } else {
//                Query query = (new Query()).setFilterById(new long[]{this.mDownloadId});
//                int pausedCount = 0;
//
//                while(true) {
//                    if(!this.isCancelled()) {
//                        try {
//                            Thread.sleep(100L);
//                        } catch (InterruptedException var11) {
//                            return Integer.valueOf(2);
//                        }
//
//                        Cursor cursor = this.mDownloadManager.query(query);
//                        if(cursor == null || !cursor.moveToFirst()) {
//                            continue;
//                        }
//
//                        int totalIdx = cursor.getColumnIndex("total_size");
//                        int downloadIdx = cursor.getColumnIndex("bytes_so_far");
//                        int totalSize = cursor.getInt(totalIdx);
//                        int downloadSize = cursor.getInt(downloadIdx);
//                        if(totalSize > 0) {
//                            this.publishProgress(new Integer[]{Integer.valueOf(downloadSize), Integer.valueOf(totalSize)});
//                        }
//
//                        int statusIdx = cursor.getColumnIndex("status");
//                        int status = cursor.getInt(statusIdx);
//                        if(status != 16 && status != 8) {
//                            if(status == 4) {
//                                ++pausedCount;
//                                if(pausedCount == 6000) {
//                                    return Integer.valueOf(status);
//                                }
//                            }
//                            continue;
//                        }
//
//                        return Integer.valueOf(status);
//                    }
//
//                    return Integer.valueOf(2);
//                }
//            }
//        }
//
//        protected void onProgressUpdate(Integer... progress) {
//            Log.d("XWalkLib", "DownloadTask updated: " + progress[0] + "/" + progress[1]);
//            int percentage = 0;
//            if(progress[1].intValue() > 0) {
//                percentage = (int)((double)progress[0].intValue() * 100.0D / (double)progress[1].intValue());
//            }
//
//            this.mListener.onDownloadUpdated(percentage);
//        }
//
//        protected void onCancelled(Integer result) {
//            this.mDownloadManager.remove(new long[]{this.mDownloadId});
//            Log.d("XWalkLib", "DownloadTask cancelled");
//            MyXWalkLibraryLoader.sActiveTask = null;
//            this.mListener.onDownloadCancelled();
//        }
//
//        protected void onPostExecute(Integer result) {
//            Log.d("XWalkLib", "DownloadTask finished, " + result);
//            MyXWalkLibraryLoader.sActiveTask = null;
//            if(result.intValue() == 8) {
//                Uri error = this.mDownloadManager.getUriForDownloadedFile(this.mDownloadId);
//                this.mListener.onDownloadCompleted(error);
//            } else {
//                int error1 = 1000;
//                if(result.intValue() == 16) {
//                    Query query = (new Query()).setFilterById(new long[]{this.mDownloadId});
//                    Cursor cursor = this.mDownloadManager.query(query);
//                    if(cursor != null && cursor.moveToFirst()) {
//                        int reasonIdx = cursor.getColumnIndex("reason");
//                        error1 = cursor.getInt(reasonIdx);
//                    }
//                }
//
//                this.mListener.onDownloadFailed(result.intValue(), error1);
//            }
//
//        }
//
//        private boolean isSilentDownload() {
//            try {
//                PackageManager packageManager = this.mContext.getPackageManager();
//                PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 4096);
//                return Arrays.asList(packageInfo.requestedPermissions).contains("android.permission.DOWNLOAD_WITHOUT_NOTIFICATION");
//            } catch (NullPointerException | NameNotFoundException var3) {
//                return false;
//            }
//        }
//    }
//
//    private static class ActivateTask extends AsyncTask<Void, Integer, Integer> {
//        MyXWalkLibraryLoader.ActivateListener mListener;
//        Activity mActivity;
//
//        ActivateTask(MyXWalkLibraryLoader.ActivateListener listener, Activity activity) {
//            this.mListener = listener;
//            this.mActivity = activity;
//        }
//
//        protected void onPreExecute() {
//            Log.d("XWalkLib", "ActivateTask started");
//            MyXWalkLibraryLoader.sActiveTask = this;
//            this.mListener.onActivateStarted();
//        }
//
//        protected Integer doInBackground(Void... params) {
//            return XWalkCoreWrapper.getInstance() != null? Integer.valueOf(-1): Integer.valueOf(XWalkCoreWrapper.attachXWalkCore(this.mActivity));
//        }
//
//        protected void onPostExecute(Integer result) {
//            if(result.intValue() == 1) {
//                XWalkCoreWrapper.dockXWalkCore();
//            }
//
//            if(XWalkCoreWrapper.getInstance() != null) {
//                XWalkCoreWrapper.handlePostInit(this.mActivity.getClass().getName());
//            }
//
//            Log.d("XWalkLib", "ActivateTask finished, " + result);
//            MyXWalkLibraryLoader.sActiveTask = null;
//            if(result.intValue() > 1) {
//                this.mListener.onActivateFailed();
//            } else {
//                this.mListener.onActivateCompleted();
//            }
//
//        }
//    }
//
//    private static class DecompressTask extends AsyncTask<Void, Integer, Integer> {
//        MyXWalkLibraryLoader.DecompressListener mListener;
//        Context mContext;
//        boolean mIsCompressed;
//        boolean mIsDecompressed;
//
//        DecompressTask(MyXWalkLibraryLoader.DecompressListener listener, Context context) {
//            this.mListener = listener;
//            this.mContext = context;
//        }
//
//        protected void onPreExecute() {
//            Log.d("XWalkLib", "DecompressTask started");
//            MyXWalkLibraryLoader.sActiveTask = this;
//            this.mIsCompressed = XWalkLibraryDecompressor.isCompressed(this.mContext);
//            if(this.mIsCompressed) {
//                this.mIsDecompressed = XWalkLibraryDecompressor.isDecompressed(this.mContext);
//            }
//
//            if(this.mIsCompressed && !this.mIsDecompressed) {
//                this.mListener.onDecompressStarted();
//            }
//
//        }
//
//        protected Integer doInBackground(Void... params) {
//            return this.mIsCompressed && !this.mIsDecompressed?(!XWalkLibraryDecompressor.decompressLibrary(this.mContext)? Integer.valueOf(1): Integer.valueOf(0)): Integer.valueOf(0);
//        }
//
//        protected void onCancelled(Integer result) {
//            Log.d("XWalkLib", "DecompressTask cancelled");
//            MyXWalkLibraryLoader.sActiveTask = null;
//            this.mListener.onDecompressCancelled();
//        }
//
//        protected void onPostExecute(Integer result) {
//            Log.d("XWalkLib", "DecompressTask finished, " + result);
//            Assert.assertEquals(result.intValue(), 0);
//            MyXWalkLibraryLoader.sActiveTask = null;
//            this.mListener.onDecompressCompleted();
//        }
//    }
//
//    public interface DownloadListener {
//        void onDownloadStarted();
//
//        void onDownloadUpdated(int var1);
//
//        void onDownloadCancelled();
//
//        void onDownloadCompleted(Uri var1);
//
//        void onDownloadFailed(int var1, int var2);
//    }
//
//    public interface ActivateListener {
//        void onActivateStarted();
//
//        void onActivateFailed();
//
//        void onActivateCompleted();
//    }
//
//    public interface DecompressListener {
//        void onDecompressStarted();
//
//        void onDecompressCancelled();
//
//        void onDecompressCompleted();
//    }
}
