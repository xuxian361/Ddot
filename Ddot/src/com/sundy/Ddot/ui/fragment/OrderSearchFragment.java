package com.sundy.Ddot.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.AppController;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.StoreListAdapter;
import com.sundy.Ddot.ui.view.xlist.XListView;
import com.sundy.Ddot.utils.Constant;
import com.sundy.Ddot.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
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
        aq.id(R.id.btn_brand).clicked(onClick);
        aq.id(R.id.btn_model).clicked(onClick);
        aq.id(R.id.btn_parts).clicked(onClick);
        aq.id(R.id.btn_store_address).clicked(onClick);


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
                case R.id.btn_brand:
                    getBrands();
                    break;
                case R.id.btn_model:
                    getModels();
                    break;
                case R.id.btn_parts:
                    getFixedParts();
                    break;
            }
        }
    };

    private void getFixedParts() {
        String tag_json_obj = "json_fixed_parts_list";
        String url = Constant.HTTP_BASE + "/fixPartManager/getFixPart.do";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.has("FF")) {
                                JSONArray FF = object.getJSONArray("FF");
                                if (FF != null) {
                                    if (FF.length() != 0) {
                                        String arr[] = new String[FF.length()];
                                        for (int i = 0; i < FF.length(); i++) {
                                            JSONObject item = (JSONObject) FF.get(i);
                                            String brand_name = item.getString("name");
                                            if (brand_name != null) {
                                                arr[i] = brand_name;
                                            }
                                        }
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(getString(R.string.choose_fixed_parts))
                                                .setSingleChoiceItems(arr, 0,
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                Log.e("sundy", "---------->which=" + which);
                                                            }
                                                        }
                                                )
                                                .setNegativeButton(getString(R.string.cancel), null)
                                                .show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void getStores() {
        String tag_json_obj = "json_store_list";
        String url = Constant.HTTP_BASE + "/storeManager/getStoreList.do";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
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
                            adapter.setData(list);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void getBrands() {
        String tag_json_obj = "json_brands_list";
        String url = Constant.HTTP_BASE + "/bandManager/getBrand.do";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.has("FF")) {
                                JSONArray FF = object.getJSONArray("FF");
                                if (FF != null) {
                                    if (FF.length() != 0) {
                                        String arr[] = new String[FF.length()];
                                        for (int i = 0; i < FF.length(); i++) {
                                            JSONObject item = (JSONObject) FF.get(i);
                                            String brand_name = item.getString("brand_name");
                                            if (brand_name != null) {
                                                arr[i] = brand_name;
                                            }
                                        }
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(getString(R.string.choose_brand))
                                                .setSingleChoiceItems(arr, 0,
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                Log.e("sundy", "---------->which=" + which);
                                                            }
                                                        }
                                                )
                                                .setNegativeButton(getString(R.string.cancel), null)
                                                .show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void getModels() {
        String tag_json_obj = "json_models_list";
        String url = Constant.HTTP_BASE + "/modelManager/getModel.do?brandId=2";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.has("FF")) {
                                JSONArray FF = object.getJSONArray("FF");
                                if (FF != null) {
                                    if (FF.length() != 0) {
                                        String arr[] = new String[FF.length()];
                                        for (int i = 0; i < FF.length(); i++) {
                                            JSONObject item = (JSONObject) FF.get(i);
                                            String brand_name = item.getString("name");
                                            if (brand_name != null) {
                                                arr[i] = brand_name;
                                            }
                                        }
                                        new AlertDialog.Builder(getActivity())
                                                .setTitle(getString(R.string.choose_model))
                                                .setSingleChoiceItems(arr, 0,
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                                Log.e("sundy", "---------->which=" + which);
                                                            }
                                                        }
                                                )
                                                .setNegativeButton(getString(R.string.cancel), null)
                                                .show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

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
