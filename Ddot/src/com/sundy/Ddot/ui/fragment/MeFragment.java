package com.sundy.Ddot.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidquery.AQuery;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.utils.Utils;

/**
 * Created by sundy on 15/5/2.
 */
public class MeFragment extends BaseFragment {

    private final String TAG = "MeFragment";
    private View v;

    public MeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtils.d("MeFragment--------------->onCreate");
        super.onCreate(savedInstanceState);
        ViewUtils.inject(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.d("MeFragment--------------->onCreateView");
        v = inflater.inflate(R.layout.d_myinfo, container, false);
        aq = new AQuery(v);

        init();
        return v;
    }

    private void init() {
        LogUtils.d("----------->init");

        aq.id(R.id.btn_setting).clicked(onClick);
        aq.id(R.id.relative_info).clicked(onClick);
        aq.id(R.id.relative_bookmark).clicked(onClick);
        aq.id(R.id.btn_logout).clicked(onClick);
        aq.id(R.id.relative_show_model).clicked(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_setting:
                    addContent(new SettingsFragment());
                    break;
                case R.id.relative_info:
                    addContent(new MyInfoFragment());
                    break;
                case R.id.relative_bookmark:
                    addContent(new BookmarkFragment());
                    break;
                case R.id.btn_logout:
                    logout();
                    break;
                case R.id.relative_show_model:

                    break;
            }
        }
    };

    private void logout() {
        SharedPreferences preferences = getActivity().getSharedPreferences(Utils.APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.isLogined, "false");
        editor.commit();
    }

    @Override
    public void onResume() {
        LogUtils.d("MeFragment--------------->onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.d("MeFragment--------------->onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.d("MeFragment--------------->onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtils.d("MeFragment--------------->onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.d("MeFragment--------------->onDestroy");
        super.onDestroy();
    }
}
