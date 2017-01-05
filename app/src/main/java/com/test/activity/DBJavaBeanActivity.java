package com.test.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.R;
import com.test.bean.UserBean;
import com.test.databinding.ActivityDbjavaBeanBinding;

public class DBJavaBeanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ActivityDbjavaBeanBinding 这是自动生成的，自动生成时请以Activity开头
        ActivityDbjavaBeanBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dbjava_bean);
        binding.setUser(new UserBean(25+"","Jeffray"));
    }
}
