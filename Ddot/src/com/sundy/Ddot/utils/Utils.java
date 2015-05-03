package com.sundy.Ddot.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sundy on 15/5/2.
 */
public class Utils {

    public static final String APP_NAME = "Ddot";
    public static final String isLogined = "isLogined";

    public static void hideSoftInputView(Activity activity) {
        if (activity.getWindow().getAttributes().softInputMode !=
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                manager.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static String getLastUpdatedTime() {
        String str = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //获取当前时间
            Date curDate = new Date(System.currentTimeMillis());
            str = formatter.format(curDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
