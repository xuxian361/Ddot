package com.sundy.Ddot.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.ImagePagerAdapter;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/5.
 */
public class ImageScaleActivity extends BaseActivity {

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    private ArrayList<String> images = new ArrayList<String>();
    @ViewInject(R.id.btnBack)
    private ImageButton btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_image_scale);

        ViewUtils.inject(this);

        try {
            images = getIntent().getStringArrayListExtra("images");
            mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images);
            mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}

