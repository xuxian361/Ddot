package com.sundy.Ddot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidquery.AQuery;
import com.sundy.Ddot.R;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundy on 15/5/3.
 */
public class StoreListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List list = new ArrayList();

    public StoreListAdapter() {
    }

    public StoreListAdapter(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;
    }

    public void setData(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.d_store_item, viewGroup, false);
            holder = new ViewHolder();
            AQuery aq = new AQuery(view);
            holder.txt_title = aq.id(R.id.txt_title).getTextView();
            holder.txt_store_name = aq.id(R.id.txt_store_name).getTextView();
            holder.txt_amount = aq.id(R.id.txt_amount).getTextView();
            holder.txt_date = aq.id(R.id.txt_date).getTextView();
            holder.txt_distance = aq.id(R.id.txt_distance).getTextView();
            holder.txt_points = aq.id(R.id.txt_points).getTextView();
            holder.img_store = aq.id(R.id.img_store).getImageView();
            holder.btn_contact = aq.id(R.id.btn_contact).getImageView();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        try {
            JSONObject item = (JSONObject) list.get(i);
            holder.txt_date.setText(item.getString("release_time"));
            holder.txt_title.setText(item.getString("title"));
            holder.txt_store_name.setText(item.getString("store_name"));
            holder.txt_amount.setText(item.getString("amount"));
            holder.txt_distance.setText("230米");
            holder.txt_points.setText(item.getString("rating") + "分 " + item.getString("comment_count") + "评");

            AQuery img_aq = new AQuery(holder.img_store);
            img_aq.image(item.getString("store_img"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    class ViewHolder {
        TextView txt_title, txt_store_name, txt_amount, txt_date, txt_distance, txt_points;
        ImageView img_store, btn_contact;
    }
}
