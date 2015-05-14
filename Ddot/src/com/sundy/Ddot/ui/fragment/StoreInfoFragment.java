package com.sundy.Ddot.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.androidquery.AQuery;
import com.lidroid.xutils.util.LogUtils;
import com.sundy.Ddot.R;
import com.sundy.Ddot.adapters.ImagaHListAdapter;
import com.sundy.Ddot.ui.activity.ImageScaleActivity;
import com.sundy.Ddot.ui.activity.StoreMapActivity;
import com.sundy.Ddot.utils.ShareDialog;
import it.sephiroth.android.library.widget.HListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sundy on 15/5/2.
 */
public class StoreInfoFragment extends BaseFragment {

    private final String TAG = "StoreInfoFragment";
    private View v;
    private JSONObject current_item;
    private HListView lv_imgs;
    private ImagaHListAdapter adapter;
    private ArrayList<String> images = new ArrayList<String>();

    public StoreInfoFragment() {
    }

    public StoreInfoFragment(JSONObject current_item) {
        this.current_item = current_item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.d_store_detail, container, false);

        aq = new AQuery(v);
        init();

        return v;
    }

    private void init() {
        aq.id(R.id.btnBack).clicked(onClick);
        aq.id(R.id.linear_all).clicked(onClick);
        aq.id(R.id.linear_bookmark).clicked(onClick);
        aq.id(R.id.img_phone).clicked(onClick);
        aq.id(R.id.img_address).clicked(onClick);
        aq.id(R.id.linear_share).clicked(onClick);
        aq.id(R.id.relative_comments).clicked(onClick);

        lv_imgs = (HListView) aq.id(R.id.lv_imgs).getView();
        adapter = new ImagaHListAdapter(getActivity(), getActivity().getLayoutInflater());
        lv_imgs.setAdapter(adapter);
        lv_imgs.setOnItemClickListener(onItemClick);
        try {
            JSONArray imgs = current_item.getJSONArray("images");
            if (imgs != null && imgs.length() != 0) {
                for (int i = 0; i < imgs.length(); i++) {
                    images.add((String) imgs.get(i));
                }
            }
            adapter.setData(images);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private it.sephiroth.android.library.widget.AdapterView.OnItemClickListener onItemClick = new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {
            if (images != null && images.size() != 0) {
                Intent intent = new Intent(getActivity(), ImageScaleActivity.class);
                intent.putStringArrayListExtra("images", images);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        }
    };

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnBack:
                    onBackPressed();
                    break;
                case R.id.linear_bookmark:

                    break;
                case R.id.img_phone:
                    callPhone();
                    break;
                case R.id.img_address:
                    goMap();
                    break;
                case R.id.linear_share:
                    share(view);
                    break;
                case R.id.relative_comments:
                    goComments();
                    break;
            }
        }
    };

    private void goComments() {
        addContent(new CommentsListFragment());
    }

    private void share(View view) {
        try {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("image", "分享图片");
            map.put("title", "分享标题");
            map.put("content", "分享内容");
            map.put("url", "分享URL");
            new ShareDialog(getActivity(), view, map).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goMap() {
        Intent intent = new Intent(getActivity(), StoreMapActivity.class);
        startActivity(intent);
    }

    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + "28791990"));
        startActivity(intent);
    }

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