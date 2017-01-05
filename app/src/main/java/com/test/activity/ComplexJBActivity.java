package com.test.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.MyBinding;
import com.test.R;
import com.test.bean.UserBean;

public class ComplexJBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_complex_jb);
        binding.setOut(new UserBean(25+"","Jeffray"));
        binding.setInner(new com.test.bean.bean.UserBean("古北1699","男"));
    }
}
