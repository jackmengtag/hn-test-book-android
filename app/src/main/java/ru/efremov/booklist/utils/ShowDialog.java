package ru.efremov.booklist.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.liuqi.R;

/**
 * dialog工具类
 */
public class ShowDialog {
    private static ShowDialog showDialog = null;
    private Dialog dialog;
    private Animation mRotateAnimation;

    /**
     * 旋转动画时长
     */
    private final int ROTATION_ANIMATION_DURATION = 1200;

    public static ShowDialog getInstance() {
        if (showDialog == null) {
            synchronized (ShowDialog.class) {
                showDialog = new ShowDialog();
            }
        }
        return showDialog;
    }

    /**
     * 正在加载dialog
     *
     * @param context
     */
    public void showLoading(Context context) {
        showLoading(context, true, null);
    }

    /**
     * 正在加载dialog
     *
     * @param context
     * @param cancelable 是否可以点击空白处或按返回键关闭dialog
     */
    public void showLoading(Context context, boolean cancelable) {
        showLoading(context, cancelable, null);
    }

    /**
     * 正在加载dialog
     *
     * @param context
     * @param tips    提示
     */
    public void showLoading(Context context, String tips) {
        showLoading(context, true, tips);
    }


    /**
     * 正在加载dialog
     *
     * @param context
     * @param cancelable 是否可以点击空白处或按返回键关闭dialog
     * @param tips       提示
     */
    public void showLoading(Context context, boolean cancelable, String tips) {
        dialog = new Dialog(context, R.style.LoadingDialog);
        dialog.setCancelable(cancelable);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading_layout, null);
        ImageView loadingImg = (ImageView) view.findViewById(R.id.dialog_loading_img);
        if (!TextUtils.isEmpty(tips)) {
            ((TextView) view.findViewById(R.id.dialog_loading_tv)).setText(tips);
        }

        mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
        loadingImg.startAnimation(mRotateAnimation);

        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 有确定按钮的dialog
     *
     * @param context
     * @param title   标题
     */
    public void showSure(Context context, String title, View.OnClickListener onClickListener) {
        dialog = new Dialog(context, R.style.NoTitleAndNoFrameDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sure, null);
        ((TextView) view.findViewById(R.id.dialog_title)).setText(title);
        view.findViewById(R.id.dialog_sure).setOnClickListener(onClickListener);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 自定义view的dialog
     *
     * @param context
     * @param view
     */
    public void showDialog(Context context, View view) {
        showDialog(context, view, true);
    }

    /**
     * 自定义view的dialog
     *
     * @param context
     * @param view
     * @param cancelable 是否可以取消
     */
    public void showDialog(Context context, View view, boolean cancelable) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        dialog = new Dialog(context, R.style.NoTitleAndNoFrameDialog);
        dialog.setCancelable(cancelable);
        dialog.setContentView(view);
        dialog.show();
    }


    /**
     * 检查版本更新
     */
    public Dialog updateDialog(Context context) {

        dialog = new Dialog(context, R.style.NoTitleAndNoFrameDialog);
        dialog.setContentView(R.layout.dialog_update_tips);


        return dialog;
    }

    public void cancel() {
        try {
            if (dialog != null) {
                dialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
