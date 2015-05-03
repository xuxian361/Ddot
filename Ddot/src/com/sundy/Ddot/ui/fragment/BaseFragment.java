package com.sundy.Ddot.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;

/**
 * Created by sundy on 15/5/2.
 */
public class BaseFragment extends Fragment {

    Context ctx;
    protected AQuery aq;

    public BaseFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ctx = getActivity();
    }

    public void addContent(Fragment fragment) {
        if (fragment == null) {
            return;
        } else {
            if (fragment.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment).commit();
            } else {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.content_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    public void onBackPressed() {
        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getActivity().getSupportFragmentManager().popBackStack();
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
