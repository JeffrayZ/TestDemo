package com.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.R;
import com.test.adapter.RecyclerAdapter;
import com.test.bean.ItemGridViewBean;
import com.test.bean.ItemRecyclerViewBean;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    ItemGridViewBean igvb;
    ItemRecyclerViewBean irvb;

    ArrayList<ItemGridViewBean> contentList1 = new ArrayList<ItemGridViewBean>();
    ArrayList<ItemGridViewBean> contentList2 = new ArrayList<ItemGridViewBean>();
    ArrayList<ItemGridViewBean> contentList3 = new ArrayList<ItemGridViewBean>();
    ArrayList<ItemGridViewBean> contentList4 = new ArrayList<ItemGridViewBean>();
    ArrayList<ItemGridViewBean> contentList5 = new ArrayList<ItemGridViewBean>();
    ArrayList<ItemGridViewBean> contentList6 = new ArrayList<ItemGridViewBean>();
    ArrayList<ItemGridViewBean> contentList7 = new ArrayList<ItemGridViewBean>();

    ArrayList<ItemRecyclerViewBean> list = new ArrayList<ItemRecyclerViewBean>();

    RecyclerView rv;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initTestDate();
        initView();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_test);
        adapter = new RecyclerAdapter(R.layout.item_rv_test,list);
        rv.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        rv.setAdapter(adapter);
    }

    private void initTestDate() {
        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList1.add(igvb);
        }

        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList2.add(igvb);
        }
        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList3.add(igvb);
        }
        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList4.add(igvb);
        }
        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList5.add(igvb);
        }
        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList6.add(igvb);
        }
        for (int i = 0; i <= 3; i++){
            igvb = new ItemGridViewBean("内容"+i);
            igvb.setFlag(false);
            contentList7.add(igvb);
        }

        for (int i = 0; i <= 6; i++){
            if( i == 0){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList1);
            }
            if( i == 1){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList2);
            }
            if( i == 2){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList3);
            }
            if( i == 3){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList4);
            }
            if( i == 4){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList5);
            }
            if( i == 5){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList6);
            }
            if( i == 6){

                irvb = new ItemRecyclerViewBean("标题"+i,contentList7);
            }
            list.add(irvb);
        }
    }
}
