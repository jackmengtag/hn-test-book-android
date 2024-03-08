package ru.efremov.booklist.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 常用工具类
 */
public class MyUtils {
    private static MyUtils myutils = null;

    private MyUtils() {
    }

    public static MyUtils getInstance() {
        if (myutils == null) {
            synchronized (MyUtils.class) {
                if (myutils == null)
                    myutils = new MyUtils();
            }
        }
        return myutils;
    }

    /**
     * 获取屏幕密度
     *
     * @param activity
     * @return 屏幕密度
     */
    public float getDensity(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        float density = metric.density;
        return density;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return 宽度
     */
    public int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return 宽度
     */
    public int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断wifi是否可用
     *
     * @param context
     * @return
     */
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前时间
     *
     * @param format 时间格式：例如yyyy-MM-dd HH:mm:ss
     * @return 格式化后的时间
     */
    public String getSystemTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(System.currentTimeMillis());
    }

    /**
     * 时间戳转化成时间
     *
     * @param format 时间格式：例如yyyy-MM-dd HH:mm:ss
     * @return 格式化后的时间
     */
    public static String convertTime(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    /**
     * 对比两个时间相差的天数
     *
     * @return 天数
     */
    public long getDayTwoTime(long begin, long end) {
        long beginTime = new Date(begin).getTime();
        long endTime = new Date(end).getTime();

        return (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);
    }

    /**
     * 获取手机SDK版本号
     *
     * @return SDK版本号
     */
    public int getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取APP应用版本号
     *
     * @param context
     * @return
     */
    public String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取手机唯一标识
     *
     * @return
     */
    public String getPhoneOnlyId(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 安装APK
     *
     * @return
     */
    public void installApk(Context context, String filename) {
        File file = new File(filename);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }

    /**
     * 将字符串的编码改为UTF-8
     */
    public String encodeToUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串的编码改为GBK
     */
    public String encodeToGBK(String str) {
        try {
            return URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 打电话
     *
     * @param context
     * @param phoneStr 电话号码
     */
    public void call(Context context, String phoneStr) {
        Uri uri = Uri.parse("tel:" + phoneStr);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 跳转到activity
     *
     * @param clazz
     * @param isFinish 是否finish掉本Activity
     */
    public void openActivity(Context context, Class<?> clazz, boolean isFinish) {
        openActivity(context, clazz, null, isFinish);
    }

    /**
     * 跳转到activity
     *
     * @param clazz
     * @param extras   附带Bundle
     * @param isFinish 是否finish掉本Activity
     */
    public void openActivity(Context context, Class<?> clazz, Bundle extras, boolean isFinish) {
        Intent intent = new Intent(context, clazz);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
        if (isFinish)
            ((Activity) context).finish();
    }

    /**
     * 获取距离
     *
     * @param distance 距离单位是m
     * @return (m/km)
     */
    public String getDistanceStr(int distance) {
        String distanceStr = null;
        if (distance > 1000) {
            distanceStr = String.valueOf(distance / 1000) + "." + String.valueOf((distance % 1000) / 100) + "km";
        } else {
            distanceStr = String.valueOf(distance) + "m";
        }
        return distanceStr;
    }

    /**
     * uuid
     *
     * @return
     */
    public String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 保留两位小数
     *
     * @return
     */
    public float retain2Decimals(float f) {
        f = (float) (Math.round(f * 100)) / 100;
        return f;
    }


    /**
     * 友盟-设备信息
     *
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
