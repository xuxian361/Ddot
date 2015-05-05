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
import org.json.JSONArray;
import org.json.JSONException;
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
        aq.id(R.id.btn_scan).clicked(onClick);

        last_updated_time = getString(R.string.just_now);
        lv_search = (XListView) aq.id(R.id.lv_search).getView();
        adapter = new StoreListAdapter(getActivity(), getActivity().getLayoutInflater());
        lv_search.setAdapter(adapter);
        lv_search.setOnItemClickListener(onItemClickListener);
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

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            try {
                if (list != null && list.size() != 0) {
                    JSONObject item = (JSONObject) list.get(i - 1);
                    if (item != null) {
                        addContent(new StoreInfoFragment(item));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void getStores() {
        String str = new String("{\n" +
                "    \"Result\": 0, \n" +
                "    \"FF\": [\n" +
                "        {\n" +
                "            \"store_id\": \"12\", \n" +
                "            \"store_name\": \"HTC服务正佳店\", \n" +
                "            \"store_address\": \"广州市天河区体育中心天河正佳M层S260\", \n" +
                "            \"longitude\": \"114.356738\", \n" +
                "            \"latitude\": \"23.676348\", \n" +
                "            \"store_phone\": \"0752-28791990\", \n" +
                "            \"store_info\": \"广州市天河区体育中心正佳M层HTC旗舰维修点是......\", \n" +
                "            \"store_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"title\": \"HTC One系列免费换膜！！\", \n" +
                "            \"amount\": \"200\", \n" +
                "            \"release_time\": \"2015-5-2 13:45:20\", \n" +
                "            \"check_count\": \"1723\", \n" +
                "            \"booking_count\": \"230\", \n" +
                "            \"rating\": \"4.2\", \n" +
                "            \"comment_name\": \"135****6578\", \n" +
                "            \"comment_count\": \"239\", \n" +
                "            \"comment_content\": \"维修很快，质量还可以，总之还行吧。\", \n" +
                "            \"images\": [\n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\"\n" +
                "            ]\n" +
                "        }, \n" +
                "        {\n" +
                "            \"store_id\": \"12\", \n" +
                "            \"store_name\": \"HTC服务正佳店\", \n" +
                "            \"store_address\": \"广州市天河区体育中心天河正佳M层S260\", \n" +
                "            \"longitude\": \"114.356738\", \n" +
                "            \"latitude\": \"23.676348\", \n" +
                "            \"store_phone\": \"0752-28791990\", \n" +
                "            \"store_info\": \"广州市天河区体育中心正佳M层HTC旗舰维修点是......\", \n" +
                "            \"store_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"title\": \"HTC One系列免费换膜！！\", \n" +
                "            \"amount\": \"200\", \n" +
                "            \"release_time\": \"2015-5-2 13:45:20\", \n" +
                "            \"check_count\": \"1723\", \n" +
                "            \"booking_count\": \"230\", \n" +
                "            \"rating\": \"4.2\", \n" +
                "            \"comment_name\": \"135****6578\", \n" +
                "            \"comment_count\": \"239\", \n" +
                "            \"comment_content\": \"维修很快，质量还可以，总之还行吧。\", \n" +
                "            \"images\": [\n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\"\n" +
                "            ]\n" +
                "        }, \n" +
                "        {\n" +
                "            \"store_id\": \"12\", \n" +
                "            \"store_name\": \"HTC服务正佳店\", \n" +
                "            \"store_address\": \"广州市天河区体育中心天河正佳M层S260\", \n" +
                "            \"longitude\": \"114.356738\", \n" +
                "            \"latitude\": \"23.676348\", \n" +
                "            \"store_phone\": \"0752-28791990\", \n" +
                "            \"store_info\": \"广州市天河区体育中心正佳M层HTC旗舰维修点是......\", \n" +
                "            \"store_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"title\": \"HTC One系列免费换膜！！\", \n" +
                "            \"amount\": \"200\", \n" +
                "            \"release_time\": \"2015-5-2 13:45:20\", \n" +
                "            \"check_count\": \"1723\", \n" +
                "            \"booking_count\": \"230\", \n" +
                "            \"rating\": \"4.2\", \n" +
                "            \"comment_name\": \"135****6578\", \n" +
                "            \"comment_count\": \"239\", \n" +
                "            \"comment_content\": \"维修很快，质量还可以，总之还行吧。\", \n" +
                "            \"images\": [\n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\"\n" +
                "            ]\n" +
                "        }, \n" +
                "        {\n" +
                "            \"store_id\": \"12\", \n" +
                "            \"store_name\": \"HTC服务正佳店\", \n" +
                "            \"store_address\": \"广州市天河区体育中心天河正佳M层S260\", \n" +
                "            \"longitude\": \"114.356738\", \n" +
                "            \"latitude\": \"23.676348\", \n" +
                "            \"store_phone\": \"0752-28791990\", \n" +
                "            \"store_info\": \"广州市天河区体育中心正佳M层HTC旗舰维修点是......\", \n" +
                "            \"store_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"title\": \"HTC One系列免费换膜！！\", \n" +
                "            \"amount\": \"200\", \n" +
                "            \"release_time\": \"2015-5-2 13:45:20\", \n" +
                "            \"check_count\": \"1723\", \n" +
                "            \"booking_count\": \"230\", \n" +
                "            \"rating\": \"4.2\", \n" +
                "            \"comment_name\": \"135****6578\", \n" +
                "            \"comment_count\": \"239\", \n" +
                "            \"comment_content\": \"维修很快，质量还可以，总之还行吧。\", \n" +
                "            \"images\": [\n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "                \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\"\n" +
                "            ]\n" +
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
                case R.id.btn_search_more:
                    addContent(new FilterFragment());
                    break;
                case R.id.btn_scan:
                    addContent(new QRScanerFragment());
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
