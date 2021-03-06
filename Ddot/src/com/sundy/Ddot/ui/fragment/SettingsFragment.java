package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/2.
 */
public class SettingsFragment extends BaseFragment {

    private final String TAG = "SettingsFragment";
    private View v;

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_settings, container, false);

        aq = new AQuery(v);
        init();
        return v;
    }

    private void init() {
        aq.id(R.id.txt_header_title).text(getString(R.string.settings));
        aq.id(R.id.btnBack).clicked(onClick);
        aq.id(R.id.linear_all).clicked(onClick);

        aq.id(R.id.txt_service_flow).clicked(onClick);
        aq.id(R.id.txt_terms).clicked(onClick);
        aq.id(R.id.txt_normal_question).clicked(onClick);
        aq.id(R.id.txt_contact_us).clicked(onClick);
        aq.id(R.id.txt_about_us).clicked(onClick);


    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    onBackPressed();
                    break;
                case R.id.txt_service_flow:
                    addContent(new HelpInfoFragment(1));
                    break;
                case R.id.txt_terms:
                    addContent(new HelpInfoFragment(2));
                    break;
                case R.id.txt_normal_question:
                    addContent(new HelpInfoFragment(3));
                    break;
                case R.id.txt_contact_us:
                    addContent(new HelpInfoFragment(4));
                    break;
                case R.id.txt_about_us:
                    addContent(new HelpInfoFragment(5));
                    break;
            }
        }
    };

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