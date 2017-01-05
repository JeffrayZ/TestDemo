package com.test.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.test.R;
import com.test.bean.UserBean;
import com.test.databinding.ActivityIncludeBinding;
import com.test.listener.TextClick;

public class IncludeActivity extends AppCompatActivity implements TextClick{
    ActivityIncludeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_include);
        binding.setMyListener(this);
        binding.setClickText("其实你不行");

        binding.includeEtInput.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                UserBean userBean = new UserBean(78+"",s.toString());
                binding.setMyUser(userBean);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onTextClick(View v) {
        Toast.makeText(this,binding.includeEtInput.etInput.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}
