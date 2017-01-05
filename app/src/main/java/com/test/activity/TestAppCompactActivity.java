package com.test.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;

import com.test.R;

public class TestAppCompactActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNormal;
    AppCompatButton btnCompact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_app_compact);

        btnCompact = (AppCompatButton) findViewById(R.id.btn_compact);
        btnCompact.setOnClickListener(this);
        btnNormal = (Button) findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal:
                Snackbar.make(btnNormal,"正常按钮",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_compact:
                Snackbar.make(btnCompact,"Compact按钮",Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
