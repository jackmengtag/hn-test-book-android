package com.yangna.lbdsp.common;


import android.util.Log;

import com.yangna.lbdsp.common.base.BaseApplication;
import com.yangna.lbdsp.common.utils.AppUtils;
import com.yangna.lbdsp.common.utils.LogUtils;

/**
 * artifact  环境地址管理
 */
public class ServiceConfig {
    /**
     * release模式是否输出日记
     */
    private static boolean RELEASE_DEBUG = false;
    //    private static final String RELEASE_ROOT_URL = "http://wap.gxyrsoft.com";//正式环境根路径 http://47.111.182.88:8084
    //    private static final String RELEASE_ROOT_URL = "http://192.168.0.116:8080";//正式环境根路径 http://47.111.182.88:8084
//     private static final String RELEASE_ROOT_URL = "http://39.108.150.225:8080";//正式环境根路径
//     private static final String RELEASE_ROOT_URL = "http://8.129.110.148:8080";//测试环境根路径
    private static final String RELEASE_ROOT_URL = "http://192.168.2.14:8080/jeecg-boot/";//测试环境根路径 http://47.111.182.88:8084

    //private static final String RELEASE_ROOT_URL = "http://192.168.3.11:84";//本地环境根路径
    private static final String TEST_ROOT_URL = "http://192.168.2.14:8080/jeecg-boot/";//测试环境根路径

    //private static final String DEBUG_ROOT_URL = "http://192.168.3.249:8080";//本地环境根路径
    //private static final String DEBUG_ROOT_URL = "http://192.168.3.15:84";//正式环境根路径
    private static final String DEBUG_ROOT_URL = "http://192.168.2.14:8080/jeecg-boot/";//本地环境根路径+
    //private static final String DEBUG_ROOT_URL = "http://192.168.3.113:8080";//本地环境根路径
    
    public enum Mode {
        DEBUG, TEST, RELEASE
    }

    private static Mode SERVICE_MODE = Mode.DEBUG;

    static {
        SERVICE_MODE = Enum.valueOf(Mode.class, AppUtils.getMetaValue(BaseApplication.getInstance(), "server_mode"));
        Log.i("whx", "SERVICE_MODE:" + SERVICE_MODE);
        if (RELEASE_DEBUG) {
            LogUtils.IS_DEBUG = true;
        } else {
            LogUtils.IS_DEBUG = SERVICE_MODE != Mode.RELEASE; //release模式默认不输出日记
        }
    }

    /**
     * 获取当前环境web/pay/createOrder
     */
    public static String getRootUrl() {
        switch (SERVICE_MODE) {
            default:
//            case DEBUG:
//                return DEBUG_ROOT_URL;
//            case TEST:
//                return TEST_ROOT_URL;
//            case RELEASE:
//                return RELEASE_ROOT_URL;
            case DEBUG:
                return RELEASE_ROOT_URL;
            case TEST:
                return RELEASE_ROOT_URL;
            case RELEASE:
                return RELEASE_ROOT_URL;
        }
    }
}
