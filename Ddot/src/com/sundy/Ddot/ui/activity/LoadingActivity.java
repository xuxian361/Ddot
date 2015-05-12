package com.sundy.Ddot.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import com.androidquery.AQuery;
import com.facebook.AppEventsLogger;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.utils.Constant;
import com.sundy.Ddot.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadingActivity extends Activity {

    private static final int GO_HOME = 998;
    private static final int GO_GUIDE = 999;
    private static final long SPLASH_DELAY_MILLIS = 1000;
    private AQuery aq;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHomeActivity();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_loading);

        //屏幕的信息
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Constant.SCREEN_WIDTH = metrics.widthPixels;
        Constant.SCREEN_HEIGHT = metrics.heightPixels;
        Constant.SCREEN_DENSITY = metrics.density;

        aq = new AQuery(this);
        getBanner();
    }

    private void getBanner() {
        String str = new String("{\n" +
                "    \"Message\": \"Success\", \n" +
                "    \"FF\": {\n" +
                "        \"banner\": \"http://img6.cache.netease.com/cnews/2015/5/11/201505110752240f45a.jpg\"\n" +
                "    }, \n" +
                "    \"Result\": 0\n" +
                "}");
        try {
            JSONObject object = new JSONObject(str);
            if (object.has("FF")) {
                JSONObject FF = object.getJSONObject("FF");
                if (FF != null) {
                    String banner = FF.getString("banner");
                    aq.id(R.id.img).image(banner);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            init();
                        }
                    }, 1000);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        synchronized (this) {
            SharedPreferences preferences = getSharedPreferences(
                    Utils.APP_NAME, MODE_PRIVATE);
            boolean isFirstIn = preferences.getBoolean(Utils.isFirstIn_Ddot, true);
            if (!isFirstIn) {
                mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
            } else {
                mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
            }
        }
    }

    private void goHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(LoadingActivity.this, GuideActivity.class);
        intent.putExtra("isFromSetting", false);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }
}
