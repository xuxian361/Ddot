package com.sundy.Ddot.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.R;
import com.sundy.Ddot.utils.Utils;

/**
 * Created by sundy on 15/5/2.
 */
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.btnBack)
    private ImageButton btnBack;


    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_login);

        ViewUtils.inject(this);

    }

    @OnClick({R.id.btnBack, R.id.txt_not_member, R.id.btn_login})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.txt_not_member:
                goRegister();
                break;
            case R.id.btn_login:
                login();
                finish();
                break;
        }
    }

    private void login() {
        SharedPreferences preferences = getSharedPreferences(Utils.APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.isLogined, "true");
        editor.commit();
    }

    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }


}
