package com.test.transform;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by modsdom on 2016/7/7.
 */
public class MyPageTransformerOne implements ViewPager.PageTransformer{

    private static float defaultScale = (float) 14 / (float) 15;

    @Override
    public void transformPage(View page, float position) {

        Log.i("TAG",position+"");

        if (position < -1) { // [-Infinity,-1)
            page.setScaleX(defaultScale);
            page.setScaleY(defaultScale);
        } else if (position <= 0) { // [-1,0]
            page.setScaleX((float) 1 + position / (float) 15);
            page.setScaleY((float) 1 + position / (float) 15);

        } else if (position <= 1) { // (0,1]
            page.setScaleX((float) 1 - position / (float) 15);
            page.setScaleY((float) 1 - position / (float) 15);
        } else { // (1,+Infinity]
            page.setScaleX(defaultScale);
            page.setScaleY(defaultScale);
        }
    }
}
