package com.test.activity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.test.R;

public class TestVecorDrawableActivity extends AppCompatActivity {


    private ImageView mCpuAniImageView, ivMy,ivSimple,ivSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vecor_drawable);

        mCpuAniImageView = (ImageView) findViewById(R.id.vector_drawable_cpu_ani);
        Drawable drawable = mCpuAniImageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }


        ivMy = (ImageView) findViewById(R.id.iv_my);
        Drawable my = ivMy.getDrawable();
        if (my instanceof Animatable) {
            ((Animatable) my).start();
        }

        ivSimple = (ImageView) findViewById(R.id.iv_simple);
        Drawable simple = ivSimple.getDrawable();
        if (simple instanceof Animatable) {
            ((Animatable) simple).start();
        }

        ivSquare = (ImageView) findViewById(R.id.iv_square);
        Drawable square = ivSquare.getDrawable();
        if (square instanceof Animatable) {
            ((Animatable) square).start();
        }
    }
}
