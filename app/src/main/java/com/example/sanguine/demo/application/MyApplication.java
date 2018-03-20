package com.example.sanguine.demo.application;

import android.app.Application;
import android.util.Log;

import com.example.sanguine.demo.CommonUtil;

import org.xwalk.core.MyXWalkLibraryLoader;

import java.io.File;

/**
 * Created by Sanguine on 2018/1/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        initCrosswalk();
    }

    /**
     * 加载crosswalk内核
     */
//    private void initCrosswalk() {
//        if (CommonUtil.supportArm()) {
//            if (!MyXWalkLibraryLoader.isDecompressed(this)) {
//                String libDir = this.getDir("xwalkcore", 0).toString();
//                File file = new File(libDir);
//                if (file.exists()) {
//                    long startTime = System.currentTimeMillis();
//                    deleteFileDir(file);
//                    long gap = System.currentTimeMillis() - startTime;
//                    Log.d("XWalkActivity", "删除CrossWork解压的文件夹耗时 " + String.valueOf(gap / 1000) + "s" + String.valueOf(gap % 1000) + "ms");
//                }
//                Log.d("XWalkActivity", "Initialize by XWalkActivity");
//                MyXWalkLibraryLoader.startDecompress(new MyDecompressListener(), this);
//            } else {
//                CommonUtil.isCrossWolkInited = true;
//            }
//        }
//    }

//    class MyDecompressListener implements MyXWalkLibraryLoader.DecompressListener {
//
//        @Override
//        public void onDecompressStarted() {
//
//        }
//
//        @Override
//        public void onDecompressCancelled() {
//
//        }
//
//        @Override
//        public void onDecompressCompleted() {
//            //解压插件完成
//            Log.v("Crosswalk", "Crosswalk decompress completed");
//            CommonUtil.isCrossWolkInited = true;
//
//        }
//    }

    /**
     * 递归删除文件及文件夹
     *
     * @param file
     */

    private void deleteFileDir(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                deleteFileDir(childFiles[i]);
            }
            file.delete();
        }
    }


}
