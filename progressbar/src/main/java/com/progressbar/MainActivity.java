package com.progressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AppCompatDialog dialog = new AppCompatDialog(this, R.style.loading_dialog);
//        dialog.setContentView(R.layout.loading_progress);
//
//        TextView titleTxtv = (TextView) dialog.findViewById(R.id.loading_text);
//        titleTxtv.setText("正在加载");
//        dialog.show();

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.showDialog();
    }
}
