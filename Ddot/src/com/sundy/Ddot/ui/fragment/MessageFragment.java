package com.sundy.Ddot.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.MsgListAdapter;
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
public class MessageFragment extends BaseFragment {

    private final String TAG = "MessageFragment";
    private View v;
    private XListView lv_msg;
    private MsgListAdapter adapter;
    private String last_updated_time = "";
    private int curPage = 1;
    private int pageNum = 10;
    private boolean ishasMore = true;
    private boolean isRefreshing = false;
    private List list = new ArrayList();

    public MessageFragment() {
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

        last_updated_time = getString(R.string.just_now);
        lv_msg = (XListView) aq.id(R.id.lv_msg).getView();
        adapter = new MsgListAdapter(getActivity(), getActivity().getLayoutInflater());
        lv_msg.setAdapter(adapter);
        lv_msg.setOnItemClickListener(onItemClickListener);
        lv_msg.setPullLoadEnable(true);
        lv_msg.setPullRefreshEnable(true);
        lv_msg.setXListViewListener(ixListViewListener);

        if (list != null)
            list.clear();
        getMsg();

    }

    private void onLoad() {
        lv_msg.stopRefresh();
        lv_msg.stopLoadMore();
        lv_msg.setRefreshTime(last_updated_time);
    }

    private XListView.IXListViewListener ixListViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            ishasMore = true;
            if (isRefreshing)
                return;
            if (list != null)
                list.clear();
            lv_msg.setAdapter(adapter);
            curPage = 1;
            last_updated_time = Utils.getLastUpdatedTime();
            getMsg();
            onLoad();
        }

        @Override
        public void onLoadMore() {
            isRefreshing = false;
            if (ishasMore) {
                lv_msg.stopRefresh();
                lv_msg.stopLoadMore();
                if (list.size() / pageNum == curPage - 1)
                    return;
                curPage++;
                getMsg();
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
                        String msg_type = item.getString("msg_type");
                        if (msg_type.equals("1")) {

                        } else if (msg_type.equals("2")) {

                        } else if (msg_type.equals("3")) {

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void getMsg() {
        String str = new String("{\n" +
                "    \"Result\": 0, \n" +
                "    \"FF\": [\n" +
                "        {\n" +
                "            \"msg_id\": \"23\", \n" +
                "            \"msg_sender\": \"Sundy Xu\", \n" +
                "            \"msg_content\": \"明天去你店看看\", \n" +
                "            \"msg_date\": \"2015-5-4 12:46:13\", \n" +
                "            \"img\": \"\", \n" +
                "            \"user_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"msg_type\": \"1\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"msg_id\": \"24\", \n" +
                "            \"msg_sender\": \"Owen\", \n" +
                "            \"msg_content\": \"差评！！！\", \n" +
                "            \"msg_date\": \"2015-5-4 12:46:13\", \n" +
                "            \"img\": \"\", \n" +
                "            \"user_img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"msg_type\": \"1\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"msg_id\": \"25\", \n" +
                "            \"msg_sender\": \"\", \n" +
                "            \"msg_content\": \"有版本更新\", \n" +
                "            \"msg_date\": \"2015-5-4 12:46:13\", \n" +
                "            \"img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"user_img\": \"\", \n" +
                "            \"msg_type\": \"2\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"msg_id\": \"26\", \n" +
                "            \"msg_sender\": \"HTC正佳M层S260\", \n" +
                "            \"msg_content\": \"您的维修订单状态有更新，查看最新状态！\", \n" +
                "            \"msg_date\": \"2015-5-4 12:46:13\", \n" +
                "            \"img\": \"http://img2.selfimg.com.cn/uedvoguecms/2015/04/27/1430102131_KqsXS4.jpg\", \n" +
                "            \"user_img\": \"\", \n" +
                "            \"msg_type\": \"3\"\n" +
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
