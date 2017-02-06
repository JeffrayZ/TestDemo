package com.transition;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Window;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 代码实现，跟xml文件实现一个道理
         * */
        // 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // 设置一个exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(2000);
            explode.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    Log.e("TAG","onTransitionStart"); // 执行
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    Log.e("TAG","onTransitionEnd"); // 执行
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    Log.e("TAG","onTransitionCancel");
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    Log.e("TAG","onTransitionPause");
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    Log.e("TAG","onTransitionResume");
                }
            });
            getWindow().setEnterTransition(explode);
            getWindow().setReturnTransition(new Slide());
        }

        setContentView(R.layout.activity_3);
    }
}



