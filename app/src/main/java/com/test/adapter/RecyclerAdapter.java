package com.test.adapter;

import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.R;
import com.test.bean.ItemGridViewBean;
import com.test.bean.ItemRecyclerViewBean;
import com.test.view.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by modsdom on 2016/6/21.
 */
public class RecyclerAdapter extends BaseQuickAdapter<ItemRecyclerViewBean> {
    NoScrollGridView nsgv;
    ArrayList<ItemGridViewBean> list;


    private ArrayList<ItemRecyclerViewBean> mList;

    public RecyclerAdapter(View contentView, List data) {
        super(contentView, data);
    }

    public RecyclerAdapter(List data) {
        super(data);
    }

    public RecyclerAdapter(int layoutResId, List data) {
        super(layoutResId, data);

        this.mList = (ArrayList<ItemRecyclerViewBean>) data;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final ItemRecyclerViewBean itemRecyclerViewBean) {
        baseViewHolder.setText(R.id.tv_rv_head,itemRecyclerViewBean.getTitle());


        final GridViewAdapter adapter = new GridViewAdapter(mContext);
        adapter.setList(itemRecyclerViewBean.getIgvbList());

        baseViewHolder.setAdapter(R.id.gv_test,adapter);
        baseViewHolder.setOnItemClickListener(R.id.gv_test, new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemRecyclerViewBean.getIgvbList().get(position).setFlag(true);
//                mList.get(baseViewHolder.getLayoutPosition()).getIgvbList().get(position).setFlag(true);
                adapter.notifyDataSetChanged();
            }
        });



//       nsgv = baseViewHolder.getView(R.id.gv_test);
//        nsgv.setAdapter(adapter);
//
//        nsgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                itemRecyclerViewBean.getIgvbList().get(position).setFlag(true);
//                adapter.notifyDataSetChanged();
//            }
//        });
    }
}


//
//        list = itemRecyclerViewBean.getIgvbList();
//
//        for (ItemGridViewBean bean: list) {
//            Log.i("TAG", bean.getContent()+"========"+bean.isFlag());
//        }
//      view.findViewById(R.id.tv_test).setBackgroundColor(Color.BLACK);