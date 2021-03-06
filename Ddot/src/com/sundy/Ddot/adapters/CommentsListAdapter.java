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
 * Created by sundy on 15/5/14.
 */
public class CommentsListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List list = new ArrayList();

    public CommentsListAdapter() {
    }

    public CommentsListAdapter(Context context, LayoutInflater inflater) {
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
            view = inflater.inflate(R.layout.d_comments_item, viewGroup, false);
            holder = new ViewHolder();
            AQuery aq = new AQuery(view);
            holder.txt_name = aq.id(R.id.txt_name).getTextView();
            holder.txt_content = aq.id(R.id.txt_content).getTextView();
            holder.txt_date = aq.id(R.id.txt_date).getTextView();
            holder.img = aq.id(R.id.img).getImageView();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        try {
            JSONObject item = (JSONObject) list.get(i);
            holder.txt_name.setText(item.getString("user_name"));
            holder.txt_date.setText(item.getString("date"));
            holder.txt_content.setText(item.getString("content"));

            String imgUrl = item.getString("head_img");
            AQuery img_aq = new AQuery(holder.img);
            img_aq.image(imgUrl).clicked(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //To do:显示删除好友、发消息、打电话等操作
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    class ViewHolder {
        TextView txt_name, txt_content, txt_date;
        ImageView img;
    }
}