package com.test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.R;
import com.test.bean.ItemGridViewBean;

import java.util.ArrayList;

/**
 * Created by modsdom on 2016/6/21.
 */
public class GridViewAdapter extends BaseAdapter{

    private ArrayList<ItemGridViewBean> list;
    private Context context;

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<ItemGridViewBean> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_nsgv_test,null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_test);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemGridViewBean bean = list.get(position);
        holder.tv.setText(bean.getContent());

        if(bean.isFlag()){
            holder.tv.setBackgroundColor(Color.BLACK);
        } else {
            holder.tv.setBackgroundColor(Color.parseColor("#ff00ddff"));
        }
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
