package com.sundy.Ddot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.androidquery.AQuery;
import com.sundy.Ddot.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sundy on 15/5/3.
 */
public class ImagaHListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List list = new ArrayList();

    public ImagaHListAdapter() {
    }

    public ImagaHListAdapter(Context context, LayoutInflater inflater) {
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
            view = inflater.inflate(R.layout.d_img_item, viewGroup, false);
            holder = new ViewHolder();
            AQuery aq = new AQuery(view);
            holder.img = aq.id(R.id.img).getImageView();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String url = (String) list.get(i);
        AQuery img_aq = new AQuery(holder.img);
        img_aq.image(url);
        return view;
    }

    class ViewHolder {
        ImageView img;
    }
}
