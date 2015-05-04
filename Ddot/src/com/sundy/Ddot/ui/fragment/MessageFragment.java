package com.sundy.Ddot.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sundy on 15/5/2.
 */
public class MessageFragment extends BaseFragment {

    private final String TAG = "MessageFragment";
    private View v;
    private JSONObject current_item;
    private ArrayList<String> images = new ArrayList<String>();

    public MessageFragment() {
    }

    public MessageFragment(JSONObject current_item) {
        this.current_item = current_item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtils.d("MessageFragment--------------->onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.d("MessageFragment--------------->onCreateView");
        v = inflater.inflate(R.layout.d_message, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btn_contact).clicked(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_contact:
                    addContent(new ContactFragment());
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        LogUtils.d("MessageFragment--------------->onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.d("MessageFragment--------------->onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.d("MessageFragment--------------->onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtils.d("MessageFragment--------------->onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.d("MessageFragment--------------->onDestroy");
        super.onDestroy();
    }
}
