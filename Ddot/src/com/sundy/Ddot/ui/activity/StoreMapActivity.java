package com.sundy.Ddot.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.R;

/**
 * Created by sundy on 15/5/7.
 */
public class StoreMapActivity extends BaseActivity {

    @ViewInject(R.id.btnBack)
    private ImageButton btnBack;

    private MapView mMapView = null;

    public StoreMapActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.d_map);

        ViewUtils.inject(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    @OnClick({R.id.btnBack, R.id.txt_not_member, R.id.btn_login})
    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


}
