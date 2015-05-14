package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.CommentsListAdapter;
import com.sundy.Ddot.adapters.StoreListAdapter;
import com.sundy.Ddot.ui.view.xlist.XListView;
import com.sundy.Ddot.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundy on 15/5/14.
 */
public class CommentsListFragment extends BaseFragment {

    private final String TAG = "CommentsListFragment";
    private View v;
    private XListView lv_comments;
    private CommentsListAdapter adapter;
    private String last_updated_time = "";
    private int curPage = 1;
    private int pageNum = 10;
    private boolean ishasMore = true;
    private boolean isRefreshing = false;
    private List list = new ArrayList();

    public CommentsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_comments, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btnBack).clicked(onClick);
        aq.id(R.id.linear_all).clicked(onClick);

        last_updated_time = getString(R.string.just_now);
        lv_comments = (XListView) aq.id(R.id.lv_comments).getView();
        adapter = new CommentsListAdapter(getActivity(), getActivity().getLayoutInflater());
        lv_comments.setAdapter(adapter);
        lv_comments.setPullLoadEnable(true);
        lv_comments.setPullRefreshEnable(true);
        lv_comments.setXListViewListener(ixListViewListener);

        if (list != null)
            list.clear();
        getComments();

    }

    private void onLoad() {
        lv_comments.stopRefresh();
        lv_comments.stopLoadMore();
        lv_comments.setRefreshTime(last_updated_time);
    }

    private XListView.IXListViewListener ixListViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            ishasMore = true;
            if (isRefreshing)
                return;
            if (list != null)
                list.clear();
            lv_comments.setAdapter(adapter);
            curPage = 1;
            last_updated_time = Utils.getLastUpdatedTime();
            getComments();
            onLoad();
        }

        @Override
        public void onLoadMore() {
            isRefreshing = false;
            if (ishasMore) {
                lv_comments.stopRefresh();
                lv_comments.stopLoadMore();
                if (list.size() / pageNum == curPage - 1)
                    return;
                curPage++;
                getComments();
            }
            onLoad();
        }
    };

    private void getComments() {
        String str = new String("{\n" +
                "    \"Result\": 0, \n" +
                "    \"FF\": [\n" +
                "        {\n" +
                "            \"user_id\": \"45\", \n" +
                "            \"username\": \"Leilei\", \n" +
                "            \"date\": \"2015-5-12 2:45:99\", \n" +
                "            \"content\": \"三星这间店还行吧，Bra~~~\", \n" +
                "            \"head_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"user_id\": \"45\", \n" +
                "            \"username\": \"Sundy Xu\", \n" +
                "            \"date\": \"2015-5-12 2:45:99\", \n" +
                "            \"content\": \"三星这间店还行吧，Bra~~~\", \n" +
                "            \"head_img\": \"http://img6.cache.netease.com/cnews/2015/5/11/201505110752240f45a.jpg\"\n" +
                "        }\n" +
                "    ], \n" +
                "    \"Message\": \"Success\"\n" +
                "}");
        try {
            JSONObject object = new JSONObject(str);
            if (object.has("FF")) {
                JSONArray FF = object.getJSONArray("FF");
                if (FF != null) {
                    if (FF.length() != 0) {
                        for (int i = 0; i < FF.length(); i++) {
                            JSONObject item = (JSONObject) FF.get(i);
                            list.add(item);
                        }
                    }
                }
            }
            LogUtils.d("------>size = " + list.size());
            adapter.setData(list);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    onBackPressed();
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
