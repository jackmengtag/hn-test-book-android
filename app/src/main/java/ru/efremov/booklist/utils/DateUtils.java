package ru.efremov.booklist.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import ru.efremov.booklist.R;


import java.util.Calendar;
import java.util.Locale;

import ru.efremov.booklist.entity.DateEntity;

/**
 * 日期选择工具类
 */
public class DateUtils {

    private static DateUtils utils = null;
//    private DateChangeListener dataChangeListener = null;

    public static DateUtils getInstance() {
        if (utils == null) {
            synchronized (DateUtils.class) {
                utils = new DateUtils();
            }

        }
        return utils;
    }

    public void show(Context context, DateEntity dateEntity, final DateChangeListener dataChangeListener) {
        if (dateEntity == null) {
            dateEntity = new DateEntity();
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            dateEntity.setYear(calendar.get(Calendar.YEAR));
            dateEntity.setMonth(calendar.get(Calendar.MONTH) + 1);
            dateEntity.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        }
//        this.dataChangeListener = dataChangeListener;

        final Dialog dialog = new Dialog(context, R.style.NoTitleAndNoFrameDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_date, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date);
        datePicker.init(dateEntity.getYear(), dateEntity.getMonth(), dateEntity.getDay(), null);
        view.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        view.findViewById(R.id.dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataChangeListener != null) {
                    dataChangeListener.onAfter(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                }
                dialog.cancel();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    public interface DateChangeListener {
        void onAfter(int year, int month, int day);
    }

}
