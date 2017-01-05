package com.test.transform;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by modsdom on 2016/7/7.
 */
public class MyPageTransformerTwo implements ViewPager.PageTransformer {

    private static final float ROT_MAX = 20.0f;
    private float mRot;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setRotation(0);
        } else if (position <= 1) {
            if (position < 0) {
                mRot = (ROT_MAX * position);
                page.setPivotX(page.getMeasuredWidth() * 0.5f);
                page.setPivotY(page.getMeasuredHeight());
                page.setRotation(mRot);
            } else {
                mRot = (ROT_MAX * position);
                page.setPivotX(page.getMeasuredWidth() * 0.5f);
                page.setPivotY(page.getMeasuredHeight());
                page.setRotation(mRot);
            }
        } else {
            page.setRotation(0);
        }
    }
}
