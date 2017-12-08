package com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余天然 on 16/7/14 下午1:27
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private Context context;
    private List<SimpleBean> items = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public SimpleAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_simple, viewGroup, false);
        return new SimpleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, int position) {
        SimpleBean item = items.get(position);
        viewHolder.ivIcon.setImageResource(item.getIcon());
        viewHolder.tvName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<SimpleBean> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected final static class SimpleViewHolder extends RecyclerView.ViewHolder {
        protected ImageView ivIcon;
        protected TextView tvName;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            this.ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }

    }
}
