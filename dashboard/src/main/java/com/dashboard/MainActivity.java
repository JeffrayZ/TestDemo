package com.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDashboard cmv = (MyDashboard) findViewById(R.id.cmv);
        cmv.setProgress(50);
    }
}
