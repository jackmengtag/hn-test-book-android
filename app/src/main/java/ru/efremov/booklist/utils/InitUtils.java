package ru.efremov.booklist.utils;

import android.content.Context;
import android.text.TextUtils;

//import com.app.liuqi.MyApplication;
//import com.app.liuqi.business.K;
//import com.baidu.mapapi.SDKInitializer;
//import com.umeng.analytics.AnalyticsConfig;
//import com.umeng.analytics.MobclickAgent;
//
//import cn.jpush.android.api.JPushInterface;
//import cn.smssdk.SMSSDK;

/**
 * 初始化工具类
 */
public class InitUtils {
    private static InitUtils utils = null;
    private boolean isInited = false;

    public static InitUtils getInstance() {
        if (utils == null) {
            synchronized (InitUtils.class) {
                utils = new InitUtils();
            }
        }
        return utils;
    }


    public void init(Context context) {
        if (isInited) {
            return;
        }
        isInited = true;

        //初始化文件工具模块
        FileUtils.getInstance().makeInitDir();
        //Log工具开关
        LogUtils.getInstance().setDebug(false);
        //初始化图片工具模块
        BitmapUtils.getInstance().initImageLoader(context);
        //初始化网络工具模块
        HttpUtils.getInstance().setCookieStore(context);
//        //初始化短信验证模块
//        BmobSMS.initialize(context, K.App.sBmobSMSSKey);
        //初始化百度地图模块
//        SDKInitializer.initialize(context);
        //初始化mob短信验证
//        SMSSDK.initSDK(context, K.App.sMobAppKey, K.App.sMobAppSecret);
        //极光推送 设置开启日志,发布时请关闭日志
//        JPushInterface.setDebugMode(false);
        //极光推送 初始化 JPush
//        JPushInterface.init(context);

        //友盟-测试模式
//        MobclickAgent.setDebugMode(false);
        //友盟-设置是否对日志信息进行加密
//        AnalyticsConfig.enableEncrypt(true);

//        MyApplication.USER_NAME = PreferenceUtils.getInstance(context).getStringPreference(K.Preference.sUserName);
//        MyApplication.USER_PHONE = PreferenceUtils.getInstance(context).getStringPreference(K.Preference.sUserPhone);
//        MyApplication.USER_TOKEN = PreferenceUtils.getInstance(context).getStringPreference(K.Preference.sToken, "0");
//        MyApplication.USER_ONEWASH = PreferenceUtils.getInstance(context).getBooleanPreference(K.Preference.sUserOneWash, true);
//        MyApplication.Longitude = PreferenceUtils.getInstance(context).getFloatPreference(K.Preference.dLongitude);
//        MyApplication.Latitude = PreferenceUtils.getInstance(context).getFloatPreference(K.Preference.dLatitude);
//        MyApplication.City = PreferenceUtils.getInstance(context).getStringPreference(K.Preference.sCity);
//        MyApplication.Addr = PreferenceUtils.getInstance(context).getStringPreference(K.Preference.sAddr);
//        MyApplication.CarNum = PreferenceUtils.getInstance(context).getStringPreference(K.Preference.sCarNum);
//        MyApplication.IMEI = MyUtils.getInstance().getPhoneOnlyId(context);
//        if (!TextUtils.isEmpty(MyApplication.USER_TOKEN) && !MyApplication.USER_TOKEN.equals("0")) {
//            MyApplication.IS_LOGIN = true;
//        }
    }
}
