package com.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.test.R;
import com.test.bean.ItemRecyclerViewBean;
import com.test.view.NoScrollGridView;

import java.util.ArrayList;

/**
 * Created by modsdom on 2016/6/22.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private ArrayList<ItemRecyclerViewBean> mList;
    private LayoutInflater mInflater;
    private Context context;

    public MyRecyclerAdapter(ArrayList<ItemRecyclerViewBean> mList, LayoutInflater mInflater,Context context) {
        this.mList = mList;
        this.mInflater = mInflater;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rv_test, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.adapter.setList(mList.get(position).getIgvbList());
        holder.nsgv.setAdapter(holder.adapter);

        holder.nsgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mList.get(position).getIgvbList().get(position).setFlag(true);
                holder.adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_rv_head);
            nsgv = (NoScrollGridView) itemView.findViewById(R.id.gv_test);
            adapter = new GridViewAdapter(context);
        }

        TextView tvTitle;
        NoScrollGridView nsgv;
        GridViewAdapter adapter;
    }
}
