package com.example.sanguine.demo.application;

import android.app.Application;

import com.example.sanguine.demo.BuildConfig;
import com.example.sanguine.demo.LogUtils;
import com.example.sanguine.demo.Utils;

import java.io.File;

/**
 * Created by Sanguine on 2018/1/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        initCrosswalk();

        /*常用工具类初始化*/
        Utils.init(this);
        /*日志控件初始化*/
        initLog();
    }

    /**
     * 初始化日志控件
     */
    private void initLog() {
        LogUtils.getConfig()
                .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag("DEMO")// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogUtils.I)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.I)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1);// log 栈深度，默认为 1
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
