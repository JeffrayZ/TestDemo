package com.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.R;
import com.test.adapter.ConstraintlayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_constraint_layout);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++){
            list.add(i);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ConstraintlayoutAdapter adapter = new ConstraintlayoutAdapter(R.layout.constraintlayout2,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);
    }
}
