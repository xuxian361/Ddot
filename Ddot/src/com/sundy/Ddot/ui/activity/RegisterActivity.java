package com.sundy.Ddot.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.R;
import com.sundy.Ddot.utils.Utils;
import com.sundy.Ddot.vo.User;

/**
 * Created by sundy on 15/5/2.
 */
public class RegisterActivity extends BaseActivity {

    @ViewInject(R.id.btnBack)
    private ImageButton btnBack;


    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_register);

        ViewUtils.inject(this);

    }

    @OnClick({R.id.btnBack, R.id.btn_Register})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btn_Register:
                register();
                finish();
                break;
        }
    }

    private void register() {
        SharedPreferences preferences = getSharedPreferences(Utils.APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.isLogined, "true");
        editor.commit();
    }


}
