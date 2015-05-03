package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.StoreListAdapter;
import com.sundy.Ddot.ui.view.xlist.XListView;
import com.sundy.Ddot.utils.Utils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundy on 15/5/2.
 */
public class OrderSearchFragment extends BaseFragment {

    private final String TAG = "OrderSearchFragment";
    private View v;
    private XListView lv_search;
    private StoreListAdapter adapter;
    private String last_updated_time = "";
    private int curPage = 1;
    private int pageNum = 10;
    private boolean ishasMore = true;
    private boolean isRefreshing = false;
    private List list = new ArrayList();

    public OrderSearchFragment() {
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

        last_updated_time = getString(R.string.just_now);
        lv_search = (XListView) aq.id(R.id.lv_search).getView();
        adapter = new StoreListAdapter();
        lv_search.setAdapter(adapter);
        lv_search.setOnItemClickListener((AdapterView.OnItemClickListener) adapter);
        lv_search.setPullLoadEnable(true);
        lv_search.setPullRefreshEnable(true);
        lv_search.setXListViewListener(ixListViewListener);

        if (list != null)
            list.clear();
        getStores();
    }

    private void onLoad() {
        lv_search.stopRefresh();
        lv_search.stopLoadMore();
        lv_search.setRefreshTime(last_updated_time);
    }

    private XListView.IXListViewListener ixListViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            ishasMore = true;
            if (isRefreshing)
                return;
            if (list != null)
                list.clear();
            lv_search.setAdapter(adapter);
            curPage = 1;
            last_updated_time = Utils.getLastUpdatedTime();
            getStores();
            onLoad();
        }

        @Override
        public void onLoadMore() {
            isRefreshing = false;
            if (ishasMore) {
                lv_search.stopRefresh();
                lv_search.stopLoadMore();
                if (list.size() / pageNum == curPage - 1)
                    return;
                curPage++;
                getStores();
            }
            onLoad();
        }
    };

    private void getStores() {
        
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
