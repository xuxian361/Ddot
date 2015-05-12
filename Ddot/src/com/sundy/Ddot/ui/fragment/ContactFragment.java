package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.ContactsListAdapter;
import com.sundy.Ddot.adapters.MsgListAdapter;
import com.sundy.Ddot.ui.view.xlist.XListView;
import com.sundy.Ddot.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundy on 15/5/4.
 */
public class ContactFragment extends BaseFragment {

    private final String TAG = "ContactFragment";
    private View v;
    private XListView lv_contact;
    private ContactsListAdapter adapter;
    private String last_updated_time = "";
    private int curPage = 1;
    private int pageNum = 10;
    private boolean ishasMore = true;
    private boolean isRefreshing = false;
    private List list = new ArrayList();


    public ContactFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_contact, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btnBack).clicked(onClick);
        aq.id(R.id.linear_all).clicked(onClick);

        last_updated_time = getString(R.string.just_now);
        lv_contact = (XListView) aq.id(R.id.lv_contact).getView();
        adapter = new ContactsListAdapter(getActivity(), getActivity().getLayoutInflater());
        lv_contact.setAdapter(adapter);
        lv_contact.setOnItemClickListener(onItemClickListener);
        lv_contact.setPullLoadEnable(true);
        lv_contact.setPullRefreshEnable(true);
        lv_contact.setXListViewListener(ixListViewListener);
        lv_contact.setOnItemLongClickListener(onItemLongClick);
        if (list != null)
            list.clear();
        getContacts();
    }

    private void onLoad() {
        lv_contact.stopRefresh();
        lv_contact.stopLoadMore();
        lv_contact.setRefreshTime(last_updated_time);
    }

    private XListView.IXListViewListener ixListViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            ishasMore = true;
            if (isRefreshing)
                return;
            if (list != null)
                list.clear();
            lv_contact.setAdapter(adapter);
            curPage = 1;
            last_updated_time = Utils.getLastUpdatedTime();
            getContacts();
            onLoad();
        }

        @Override
        public void onLoadMore() {
            isRefreshing = false;
            if (ishasMore) {
                lv_contact.stopRefresh();
                lv_contact.stopLoadMore();
                if (list.size() / pageNum == curPage - 1)
                    return;
                curPage++;
                getContacts();
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
                        //To do: 进行聊天操作
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private AdapterView.OnItemLongClickListener onItemLongClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            //To do:显示删除好友、发消息、打电话等操作
            return false;
        }
    };

    private void getContacts() {
        String str = new String("{\n" +
                "    \"Result\": 0, \n" +
                "    \"FF\": [\n" +
                "        {\n" +
                "            \"id\": \"23\", \n" +
                "            \"name\": \"Sundy Xu\", \n" +
                "            \"image\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"qr_code\": \"FJDSI397977FDFE13334FFRJEKJKDJFALEIW\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"id\": \"99\", \n" +
                "            \"name\": \"Leilei\", \n" +
                "            \"image\": \"http://img6.cache.netease.com/cnews/2015/5/11/201505110752240f45a.jpg\", \n" +
                "            \"qr_code\": \"FJDSI397977FDFE13334FFRJEKJKDJFALEIW\"\n" +
                "        }\n" +
                "    ], \n" +
                "    \"Message\": \"Success\"\n" +
                "}\n");
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