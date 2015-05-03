package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.ui.view.xlist.XListView;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sundy on 15/5/2.
 */
public class OrderSearchFragment extends BaseFragment {

    private final String TAG = "OrderSearchFragment";
    private View v;
    private JSONObject current_item;
    private ArrayList<String> images = new ArrayList<String>();
    private XListView lv_search;

    public OrderSearchFragment() {
    }

    public OrderSearchFragment(JSONObject current_item) {
        this.current_item = current_item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtils.d("OrderSearchFragment--------------->onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.d("OrderSearchFragment--------------->onCreateView");
        v = inflater.inflate(R.layout.d_ordersearch, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btn_search_more).clicked(onClick);

        lv_search = (XListView) aq.id(R.id.lv_search).getView();

    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_search_more:
                    addContent(new FilterFragment());
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        LogUtils.d("OrderSearchFragment--------------->onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.d("OrderSearchFragment--------------->onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.d("OrderSearchFragment--------------->onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtils.d("OrderSearchFragment--------------->onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.d("OrderSearchFragment--------------->onDestroy");
        super.onDestroy();
    }
}
