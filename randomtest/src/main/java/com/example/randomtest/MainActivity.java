package com.example.randomtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.tv_name);
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++){
            list.add("用户" + i);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(list.get((int) (Math.random()* list.size())));
                handler.postDelayed(this,100);
            }
        },100);
    }
}
