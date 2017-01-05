package com.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.R;
import com.test.adapter.QuickAdapter;
import com.test.bean.Status;
import com.test.util.DividerItemDecoration;

import java.util.ArrayList;

public class TestFooterActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener{

    private RecyclerView mRecyclerView;
    private QuickAdapter mQuickAdapter;
    ArrayList<Status> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_footer);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_test_footer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        initAdapter();

    }

    private void initAdapter() {

        list = new ArrayList<>();
        Status status;
        for (int i = 0; i <= 9; i++){
            status = new Status("数据" + i);
            list.add(status);
        }

        mQuickAdapter = new QuickAdapter(R.layout.item_recyclerview_test_footer,list);

        mRecyclerView.setAdapter(mQuickAdapter);
        View view = getLayoutInflater().inflate(R.layout.not_loading,(ViewGroup) mRecyclerView.getParent(), false);
//        View view1 = View.inflate(TestFooterActivity.this,R.layout.not_loading,null);
        mQuickAdapter.addFooterView(view);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
