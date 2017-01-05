package com.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.R;
import com.test.transform.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class TestViewpagerActivity extends AppCompatActivity {

    private Context mContext;
    private ViewPager viewPager;
    private int[] mImgIds = new int[]{R.mipmap.image_one,
            R.mipmap.image_two, R.mipmap.image_three, R.mipmap.image_four};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewpager);
        mContext = this;
        initData();
        initView();

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(mImageViews.get(position));
                return mImageViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {

                container.removeView(mImageViews.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                return mImgIds.length;
            }
        });

//        viewPager.setPageTransformer(true,new DepthPageTransformer());
        viewPager.setPageTransformer(false,new ZoomOutPageTransformer());
//        viewPager.setPageTransformer(false,new MyPageTransformerOne());
//        viewPager.setPageTransformer(false,new MyPageTransformerTwo());
//        viewPager.setPageTransformer(true,new MyPageTransformerThree());
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData() {
        for (int imgId : mImgIds) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }
}
