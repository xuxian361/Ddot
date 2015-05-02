package com.sundy.Ddot.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.R;

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

    @OnClick({R.id.btnBack})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }


}
