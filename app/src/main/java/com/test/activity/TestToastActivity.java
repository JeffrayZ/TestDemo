package com.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.R;

public class TestToastActivity extends AppCompatActivity implements View.OnClickListener{


    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toast);

        initView();
    }

    private void initView() {
        findViewById(R.id.button1).setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast toast;
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(TestToastActivity.this,"普通的Toast",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                toast = Toast.makeText(TestToastActivity.this,"自定义位置", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, (int) btn2.getX(), (int) btn2.getY() + btn2.getHeight()*2);
                toast.show();
                break;
            case R.id.button3:
                toast = Toast.makeText(TestToastActivity.this,"加个图片", Toast.LENGTH_SHORT);
                // 这边只能是LinearLayout
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(getApplicationContext());
                imageCodeProject.setImageResource(R.mipmap.ic_launcher);
                toastView.addView(imageCodeProject, 0);
                // 设置展示位置并展示
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                break;
            case R.id.button4:
                toast = new Toast(TestToastActivity.this);
                View view = View.inflate(TestToastActivity.this,R.layout.toast_view,null);
                view.setBackgroundColor(Color.RED);
                toast.setView(view);
                // 设置展示位置并展示
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                break;
            default:
                break;
        }
    }
}
