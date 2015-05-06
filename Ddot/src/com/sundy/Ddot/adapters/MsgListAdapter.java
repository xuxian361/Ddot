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
 * Created by sundy on 15/5/4.
 */
public class MsgListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List list = new ArrayList();

    public MsgListAdapter() {
    }

    public MsgListAdapter(Context context, LayoutInflater inflater) {
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
            view = inflater.inflate(R.layout.d_msg_item, viewGroup, false);
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
            holder.txt_date.setText(item.getString("msg_date"));
            holder.txt_name.setText(item.getString("msg_sender"));
            holder.txt_content.setText(item.getString("msg_content"));

            String imgUrl = item.getString("img");
            String userImgUrl = item.getString("user_img");

            AQuery img_aq = new AQuery(holder.img);

            String msg_type = item.getString("msg_type");
            if (msg_type.equals("1")) {
                img_aq.image(userImgUrl);
            } else if (msg_type.equals("2")) {
                img_aq.image(userImgUrl);
            } else if (msg_type.equals("3")) {
                img_aq.image(imgUrl);
            } else if (msg_type.equals("4")) {
                img_aq.image(imgUrl);
            }

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
