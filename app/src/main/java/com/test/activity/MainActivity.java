package com.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        registrListener();

    }

    private void registrListener() {
        findViewById(R.id.test_recycleriew).setOnClickListener(this);
        findViewById(R.id.test_line).setOnClickListener(this);
        findViewById(R.id.test_footer).setOnClickListener(this);
        findViewById(R.id.test_toast).setOnClickListener(this);
        findViewById(R.id.test_layouttransition).setOnClickListener(this);
        findViewById(R.id.test_json).setOnClickListener(this);
        findViewById(R.id.btn_test_edittext).setOnClickListener(this);
        findViewById(R.id.test_rx_android).setOnClickListener(this);
        findViewById(R.id.test_appcompact).setOnClickListener(this);
        findViewById(R.id.test_toggle_button).setOnClickListener(this);
        findViewById(R.id.test_appbar_layout).setOnClickListener(this);
        findViewById(R.id.test_backgroundhint).setOnClickListener(this);
        findViewById(R.id.test_android_animation).setOnClickListener(this);
        findViewById(R.id.test_valueAnimator).setOnClickListener(this);
        findViewById(R.id.test_viewpager).setOnClickListener(this);
        findViewById(R.id.test_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.test_vector_drawable).setOnClickListener(this);
        findViewById(R.id.test_soft_keybord).setOnClickListener(this);
        findViewById(R.id.test_tint).setOnClickListener(this);
        findViewById(R.id.btn_header).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.test_footer:
                intent = new Intent(this,TestFooterActivity.class);
                startActivity(intent);
                break;
            case R.id.test_line:
                intent = new Intent(this,ShapeActivity.class);
                startActivity(intent);
                break;
            case R.id.test_recycleriew:
                intent = new Intent(this,RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.test_toast:
                intent = new Intent(this,TestToastActivity.class);
                startActivity(intent);
                break;
            case R.id.test_layouttransition:
                intent = new Intent(this,TestLayoutTransitionActivity.class);
                startActivity(intent);
                break;
            case R.id.test_json:
                intent = new Intent(this,TestJsonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_test_edittext:
                intent = new Intent(this,TestEditTextActivity.class);
                startActivity(intent);
                break;
            case R.id.test_rx_android:
                intent = new Intent(this,TestRxAndroidActivity.class);
                startActivity(intent);
                break;
            case R.id.test_appcompact:
                intent = new Intent(this,TestAppCompactActivity.class);
                startActivity(intent);
                break;
            case R.id.test_toggle_button:
                intent = new Intent(this,TesttoggleButton.class);
                startActivity(intent);
                break;
            case R.id.test_appbar_layout:
                intent = new Intent(this,TestAppBarLayoutActivity.class);
                startActivity(intent);
            case R.id.test_backgroundhint:
                intent = new Intent(this,TestBackgroundHintActivity.class);
                startActivity(intent);
                break;
            case R.id.test_android_animation:
                intent = new Intent(this,TestAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.test_valueAnimator:
                intent = new Intent(this,ValueAnimAcitivity.class);
                startActivity(intent);
                break;
            case R.id.test_viewpager:
                intent = new Intent(this,TestViewpagerActivity.class);
                startActivity(intent);
                break;
            case R.id.test_bottom_sheet:
                intent = new Intent(this,TestBottomSheetActivity.class);
                startActivity(intent);
                break;
            case R.id.test_vector_drawable:
                intent = new Intent(this,TestVecorDrawableActivity.class);
                startActivity(intent);
                break;
            case R.id.test_soft_keybord:
                intent = new Intent(this,TestSoftKeybordActivity.class);
                startActivity(intent);
                break;
            case R.id.test_tint:
                intent = new Intent(this,TestTintActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_header:
                intent = new Intent(this,RecyclerViewHeaderActivity.class);
                startActivity(intent);
                break;
            case R.id.button7:
                intent = new Intent(this,TestDataBindingActivity.class);
                startActivity(intent);
                break;
            case R.id.button11:
                intent = new Intent(this,TestConstraintLayoutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
