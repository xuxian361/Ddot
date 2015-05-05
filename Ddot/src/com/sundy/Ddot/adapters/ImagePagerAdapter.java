package com.sundy.Ddot.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import com.sundy.Ddot.ui.fragment.ImageScaleFragment;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/5.
 */
public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> images = new ArrayList<String>();

    public ImagePagerAdapter(FragmentManager fm, ArrayList<String> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public int getCount() {
        if (images != null)
            return images.size();
        return 0;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageScaleFragment.newInstance(images, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
