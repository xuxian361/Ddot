package com.sundy.Ddot.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.view.ViewHelper;
import com.sundy.Ddot.adapters.MainViewPagerAdapter;
import com.sundy.Ddot.R;
import com.sundy.Ddot.ui.fragment.MeFragment;
import com.sundy.Ddot.ui.fragment.MessageFragment;
import com.sundy.Ddot.ui.fragment.OrderSearchFragment;
import com.sundy.Ddot.utils.Utils;
import jazzyviewpager.JazzyViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sundy on 15/5/2.
 */
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.jazzyPager)
    private JazzyViewPager jazzyPager;
    List<Map<String, View>> tabViews = new ArrayList<Map<String, View>>();
    public TabHost tabHost;
    private int tabCount = 3;
    private MainViewPagerAdapter adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_main);
        ViewUtils.inject(this);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("0").setIndicator(createTab(getString(R.string.message), 0)).setContent(android.R.id.tabcontent));
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator(createTab(getString(R.string.search), 1)).setContent(android.R.id.tabcontent));
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator(createTab(getString(R.string.me), 2)).setContent(android.R.id.tabcontent));
        // 点击tabHost 来切换不同的消息
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int index = Integer.parseInt(tabId);
                tabHost.getTabContentView().setVisibility(View.GONE);// 隐藏content
                setTabSelectedState(index, tabCount);
            }
        });
        tabHost.setCurrentTab(0);

        initAdapter();
        initJazzyPager(JazzyViewPager.TransitionEffect.Standard);
    }

    private void initAdapter() {
        MessageFragment msgFragment = new MessageFragment();
        OrderSearchFragment searchFragment = new OrderSearchFragment();
        MeFragment meFragment = new MeFragment();
        fragmentList.add(msgFragment);
        fragmentList.add(searchFragment);
        fragmentList.add(meFragment);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList);
    }

    private void initJazzyPager(JazzyViewPager.TransitionEffect effect) {
        jazzyPager.setTransitionEffect(effect);
        jazzyPager.setAdapter(adapter);
        jazzyPager.setFadeEnabled(true);
        jazzyPager.setSlideCallBack(new JazzyViewPager.SlideCallback() {
            @Override
            public void callBack(int position, float positionOffset) {
                Map<String, View> map = tabViews.get(position);
                ViewHelper.setAlpha(map.get("selected"), positionOffset);
                ViewHelper.setAlpha(map.get("normal"), 1 - positionOffset);
            }
        });
        jazzyPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    SharedPreferences preferences = getSharedPreferences(Utils.APP_NAME, Context.MODE_PRIVATE);
                    String isLogined = preferences.getString(Utils.isLogined, "");
                    if (isLogined.equals("false") || isLogined.equals("")) {
                        goLogin();
                        tabHost.setCurrentTab(0);
                        return;
                    }
                }
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
            }

            @Override
            public void onPageScrollStateChanged(int paramInt) {
            }
        });
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private View createTab(String tabLabelText, int tabIndex) {
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.main_tabwidget_layout, null);
        TextView normalTV = (TextView) tabIndicator.findViewById(R.id.normalTV);
        TextView selectedTV = (TextView) tabIndicator.findViewById(R.id.selectedTV);
        normalTV.setText(tabLabelText);
        selectedTV.setText(tabLabelText);
        ImageView normalImg = (ImageView) tabIndicator.findViewById(R.id.normalImg);
        ImageView selectedImg = (ImageView) tabIndicator.findViewById(R.id.selectedImage);
        switch (tabIndex) {
            case 0:
                normalImg.setImageResource(R.drawable.d_msg);
                selectedImg.setImageResource(R.drawable.d_msg);
                break;
            case 1:
                normalImg.setImageResource(R.drawable.d_search);
                selectedImg.setImageResource(R.drawable.d_search);
                break;
            case 2:
                normalImg.setImageResource(R.drawable.d_me);
                selectedImg.setImageResource(R.drawable.d_me);
                break;
        }
        View normalLayout = tabIndicator.findViewById(R.id.normalLayout);
        normalLayout.setAlpha(1f);// 透明度显示
        View selectedLayout = tabIndicator.findViewById(R.id.selectedLayout);
        selectedLayout.setAlpha(0f);// 透明的隐藏
        Map<String, View> map = new HashMap<String, View>();
        map.put("normal", normalLayout);
        map.put("selected", selectedLayout);
        tabViews.add(map);
        return tabIndicator;
    }

    private void setTabSelectedState(int index, int tabCount) {
        for (int i = 0; i < tabCount; i++) {
            if (i == index) {
                tabViews.get(i).get("normal").setAlpha(0f);
                tabViews.get(i).get("selected").setAlpha(1f);
            } else {
                tabViews.get(i).get("normal").setAlpha(1f);
                tabViews.get(i).get("selected").setAlpha(0f);
            }
        }
        jazzyPager.setCurrentItem(index, false);// false表示 代码切换 pager 的时候不带scroll动画
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTabSelectedState(tabHost.getCurrentTab(), tabCount);
    }


}
