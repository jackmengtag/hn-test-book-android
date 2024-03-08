package ru.efremov.booklist.utils;

import android.content.Context;
import android.view.Gravity;

import com.app.liuqi.widget.ToastView;

/**
 * Toast工具类
 */
public class ToastUtils {

    private static ToastUtils toastUtils = null;

    public static ToastUtils getInstance() {
        if (toastUtils == null) {
            synchronized (ToastUtils.class) {
                toastUtils = new ToastUtils();
            }
        }
        return toastUtils;
    }

    /**
     * 吐司
     *
     * @param text
     */
    public void showToast(Context context, String text) {
        ToastView toast = new ToastView(context, text, false);
        toast.show();
    }

    /**
     * 吐司 （底部）
     *
     * @param text
     */
    public void showBottomToast(Context context, String text) {
        ToastView toast = new ToastView(context, text, false);
        toast.setGravity(Gravity.BOTTOM, 0, 10);
        toast.show();
    }

    /**
     * 吐司 （屏幕中间）
     *
     * @param text
     */
    public void showCenterToast(Context context, String text) {
        ToastView toast = new ToastView(context, text, false);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 吐司 （屏幕顶部）
     *
     * @param text
     */
    public void showTopToast(Context context, String text) {
        ToastView toast = new ToastView(context, text, false);
        toast.setGravity(Gravity.TOP, 0, 10);
        toast.show();
    }

}
