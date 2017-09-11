package com.cus_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SimpleLineChart mSimpleLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final CirclePercentView circlePercentView = (CirclePercentView) findViewById(R.id.circleView);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int n = (int)(Math.random()*100);
//                circlePercentView.setPercent(n);
//            }
//        });

        mSimpleLineChart = (SimpleLineChart) findViewById(R.id.slc);
        String[] xItem = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] yItem = {"10k","20k","30k","40k","50k"};
        mSimpleLineChart.setXItem(xItem);
        mSimpleLineChart.setYItem(yItem);
        HashMap<Integer,Integer> pointMap = new HashMap();
        for(int i = 0;i < xItem.length;i++){
            pointMap.put(i, (int) (Math.random()*5));
        }
        mSimpleLineChart.setData(pointMap);
    }
}
