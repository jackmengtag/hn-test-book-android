package ru.efremov.booklist.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

/**
 * 配置文件工具
 *
 * @author 刘风广
 */
public class PreferenceUtils {
    private final static String PREFERENCE_NAME = "51cct_preference";
    private static PreferenceUtils utils = null;
    private static SharedPreferences preference;

    private PreferenceUtils(Context context) {
        preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
    }

    public static PreferenceUtils getInstance(Context context) {
        context = context.getApplicationContext();
        if (utils == null) {
            synchronized (PreferenceUtils.class) {
                if (utils == null)
                    utils = new PreferenceUtils(context);
            }
        }

        return utils;
    }

    public static PreferenceUtils getSharedPreferences(Context context) {
        context = context.getApplicationContext();
        if (preference == null) {
            preference = context.getSharedPreferences(PREFERENCE_NAME, 0);
        }

        return utils;
    }

    /**
     * 写入String类型的配置
     *
     * @param key
     * @param value
     * @return
     */
    public void setStringPreference(String key, String value) {
        Editor ed = preference.edit();
        ed.putString(key, value);
        ed.commit();
    }

    /**
     * 获取String类型的配置
     *
     * @param key
     * @return
     */
    public String getStringPreference(String key) {
        String str = null;
        if (preference.contains(key)) {
            str = preference.getString(key, null);
        }
        return str;
    }

    /**
     * 获取String类型的配置
     *
     * @param key
     * @param defValue
     * @return
     */
    public String getStringPreference(String key, String defValue) {
        String str = null;
        if (preference.contains(key)) {
            str = preference.getString(key, defValue);
        }

        if (TextUtils.isEmpty(str)) {
            str = defValue;
        }

        return str;
    }

    /**
     * 写入int类型的配置
     *
     * @param key
     * @param value
     * @return
     */
    public void setIntPreference(String key, int value) {
        Editor ed = preference.edit();
        ed.putInt(key, value);
        ed.commit();
    }

    /**
     * 获取int类型的配置
     *
     * @param key
     * @return
     */
    public int getIntPreference(String key) {
        int num = 0;
        if (preference.contains(key)) {
            num = preference.getInt(key, 0);
        }
        return num;
    }

    /**
     * 写入boolean类型的配置
     *
     * @param key
     * @param value
     * @return
     */
    public void setBooleanPreference(String key, boolean value) {
        Editor ed = preference.edit();
        ed.putBoolean(key, value);
        ed.commit();
    }

    /**
     * 获取boolean类型的配置
     *
     * @param key
     * @return
     */
    public boolean getBooleanPreference(String key, boolean defaultValue) {
        boolean is = defaultValue;
        if (preference.contains(key)) {
            is = preference.getBoolean(key, defaultValue);
        }
        return is;
    }

    /**
     * 写入float类型的配置
     *
     * @param key
     * @param value
     * @return
     */
    public void setFloatPreference(String key, float value) {
        Editor ed = preference.edit();
        ed.putFloat(key, value);
        ed.commit();
    }

    /**
     * 获取float类型的配置
     *
     * @param key
     * @return
     */
    public float getFloatPreference(String key) {
        float val = 0;
        if (preference.contains(key)) {
            val = preference.getFloat(key, 0);
        }
        return val;
    }

    /**
     * 写入long类型的配置
     *
     * @param key
     * @param value
     * @return
     */
    public void setLongPreference(String key, long value) {
        Editor ed = preference.edit();
        ed.putLong(key, value);
        ed.commit();
    }

    /**
     * 获取long类型的配置
     *
     * @param key
     * @return
     */
    public long getLongPreference(String key) {
        long val = 0;
        if (preference.contains(key)) {
            val = preference.getLong(key, 0);
        }
        return val;
    }
}
