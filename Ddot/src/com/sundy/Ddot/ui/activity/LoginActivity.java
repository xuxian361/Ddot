package com.sundy.Ddot.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.R;

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

    @OnClick({R.id.btnBack, R.id.txt_not_member})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.txt_not_member:
                goRegister();
                break;
        }
    }

    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
