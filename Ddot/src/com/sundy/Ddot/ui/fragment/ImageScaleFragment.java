package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.androidquery.AQuery;
import com.sundy.Ddot.R;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/5.
 */
public class ImageScaleFragment extends Fragment {
    private int position = 0;
    private ArrayList<String> list = new ArrayList<String>();

    public static ImageScaleFragment newInstance(ArrayList<String> images, int position) {
        final ImageScaleFragment f = new ImageScaleFragment();

        final Bundle args = new Bundle();
        args.putInt("position", position);
        args.putStringArrayList("images", images);
        f.setArguments(args);

        return f;
    }

    public ImageScaleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
        list = getArguments().getStringArrayList("images");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.image_scale_detail,
                container, false);
        try {
            ImageView mImageView = (ImageView) v.findViewById(R.id.imageView);
            AQuery aq = new AQuery(mImageView);
            aq.image(list.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

}