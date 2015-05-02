package com.sundy.Ddot.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import com.sundy.Ddot.utils.Utils;

/**
 * Created by sundy on 15/5/2.
 */
public class BaseActivity extends FragmentActivity {

    protected Context ctx;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
    }

    protected void hideSoftInputView() {
        Utils.hideSoftInputView(this);
    }

    protected void setSoftInputMode() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
