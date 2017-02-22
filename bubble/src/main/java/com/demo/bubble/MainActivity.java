package com.demo.bubble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MySinkingView mSinkingView;
    private float percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSinkingView = (MySinkingView) findViewById(R.id.sv_view);
        percent = showText(4000);
        test(percent);
    }

    private float showText(int totalCalorie) {
        if (totalCalorie < 1250) {
//            tvText.setText("不足");
            return 0.1f;
        } else if (totalCalorie < 2000) {
//            tvText.setText("不足");
            return (totalCalorie - 1250) * 1.0f/(2000 - 1250);
        } else if (totalCalorie < 6000) {
//            tvText.setText("适量");
            return (totalCalorie - 2000) * 1.0f/(6000 - 2000);
        } else if (totalCalorie < 12000) {
//            tvText.setText("燃烧");
            return (totalCalorie - 6000) * 1.0f/(12000 - 6000);
        } else if (totalCalorie < 20000) {
//            tvText.setText("爆发");
            return (totalCalorie - 12000) * 1.0f/(20000 - 12000);
        } else {
//            tvText.setText("爆发");
            return 1.0f;
        }
    }

    private void test(final float percents) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                percent = 0;
                while (percent <= 1) {
                    mSinkingView.setPercent(percent);
                    percent += 0.01f;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                percent = percents;
                mSinkingView.setPercent(percent);
                mSinkingView.setStarting(false);
            }
        });
        thread.start();
    }
}
