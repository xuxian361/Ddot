package com.sundy.Ddot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/2.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }
}