package com.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.R;

/***
 * 测试databinding
 *
 * 
 *
 * autho Jeffray
 *
 * created 2016/11/2 14:13
 ***/
public class TestDataBindingActivity extends AppCompatActivity {

    String str = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data_binding);

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestDataBindingActivity.this,DBJavaBeanActivity.class);
                startActivity(intent);
            }
        });


//        TextView textView = (TextView) findViewById(R.id.textView4);
//        textView.setText(str != null ?? "哈哈");
    }

    public void complexJavaBean(View view){
        Intent intent = new Intent(TestDataBindingActivity.this,ComplexJBActivity.class);
        startActivity(intent);
    }

    public void testInclude(View view){
        Intent intent = new Intent(TestDataBindingActivity.this,IncludeActivity.class);
        startActivity(intent);
    }
}
