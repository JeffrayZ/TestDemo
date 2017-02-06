package com.transition;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/***
 * Circular Reveal是一个Android L 新增的动画效果
 *
 * 
 *
 * @author Jeffray
 *
 * @created 2017/1/17 16:34
 ***/
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        View view = findViewById(R.id.view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

//                这五个参数分别是：
//                view 操作的视图
//                centerX 动画开始的中心点X
//                centerY 动画开始的中心点Y
//                startRadius 动画开始半径
//                startRadius 动画结束半径

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator ani = null;
                    ani = ViewAnimationUtils.createCircularReveal(
                            view,
                            view.getWidth()/2,
                            view.getHeight()/2,
                            (float) Math.hypot(view.getWidth(),view.getHeight()),
                            0);
                    ani.setInterpolator(new AccelerateDecelerateInterpolator());
                    ani.setDuration(1000);
                    ani.start();

                    ani.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            Log.e("TAG","开始");
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            Log.e("TAG","结束"); // 该动画只会执行这个方法
                            view.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {
                            Log.e("TAG","取消");
                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {
                            Log.e("TAG","重复");
                        }
                    });
                }
            }
        });
    }
}
