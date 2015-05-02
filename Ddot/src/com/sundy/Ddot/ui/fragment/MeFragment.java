package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sundy.Ddot.R;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/2.
 */
public class MeFragment extends Fragment {

    private final String TAG = "MeFragment";
    private View v;
    private JSONObject current_item;
    private ArrayList<String> images = new ArrayList<String>();

    public MeFragment() {
    }

    public MeFragment(JSONObject current_item) {
        this.current_item = current_item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_myinfo, container, false);

        init();

        return v;
    }

    private void init() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
