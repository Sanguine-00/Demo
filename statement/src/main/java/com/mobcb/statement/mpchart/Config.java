package com.mobcb.statement.mpchart;

/**
 * Created by wanggh on 2015/5/25.
 */
public interface Config {
    boolean encryptRsa = false;  // 获取accessToken是否加密
    boolean encryptBody = false; // 业务接口是否加密
    /**
     * 网络加密请求的appkey
     */
    String APP_KEY_USER_ANDROID = "3I63U6MSDIA";  //用户端ANDROID

    /**
     * 网络加密请求的secret
     */
    String[] APP_SECRET_KEY_USER_ANDROID = new String[]{
            "AJ6ID9OGS6HBRUHHFU91UNN2P", "64E9VLSJ61DGGK3ROGVO4C2XK",
            "7319CQN7FATGMQUQD8N201TAH", "89H734RROHIS43FCCO80FP37J",
            "CC9FV2RJRZFOAEF0XVRZKEJ69", "77QJL3534SCI1CI8R4JA1RDCW",
            "6ZODR5TN77FIV579JAK9UPMF2", "BP7QCEJX5FD8FVXFXKMYF4N4V",
            "BPLT5E0FYSKWY04IU81RTNR6D", "DZHELIN6U9EGZWUCLUVSZW7AM",};

    /**
     * 编码
     */
    String API_APP_ENCODING = "UTF-8";

    /**
     * HttpUtils里clientAppName
     */
    String CLIENT_APP_NAME = "memberClient";

    String KEY_DEVICE_ID = "sp.key.deviceId";

    /**
     * 最大错误次数
     */
    int MAX_ERROR = 3;
}
