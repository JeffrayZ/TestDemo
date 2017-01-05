package com.test.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.test.R;

/********
 *
 * @author： Jeffray zhanfeifei1991@163.com
 *
 * @time： 2016/7/6 10:48
 *
 * @description：
 *              实现一个动画更改多个效果：使用 propertyValuesHolder
 *
 * @Copyright： 2016 www.modosdom.com Inc. All rights reserved.
 ********/
public class TestAnimationActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView iv;
    Context mContext;

    RotateAnimation ra;
    TranslateAnimation ta;
    ScaleAnimation sa;
    AlphaAnimation aa;
    AnimationSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        mContext = this;

        initView();
        
        initAnimation();
    }

    /**
     * 初始化动画
     * **/
    private void initAnimation() {
        /**
         * 补间旋转动画
         * */
        // 代码设置
//        ra  = new RotateAnimation(
//                0, // 起始位置的旋转角度
//                30, // 需要旋转的角度
//                // RELATIVE_TO_PARENT 位置是相对于parent；RELATIVE_TO_SELF 位置相对于自身
//                Animation.RELATIVE_TO_SELF,
//                // 起始X
//                0.5f,
//                // 位置是相对于自身
//                Animation.RELATIVE_TO_SELF,
//                // 起始Y
//                0.5f);

        // xml设置
        ra = (RotateAnimation) AnimationUtils.loadAnimation(mContext,R.anim.tween_rotate);

        // 动画重复的次数。0 表示不重复，Animation.INFINITE 表示无限重复
//                ra.setRepeatCount(Animation.INFINITE);
        // 动画重复的模式，只有在setRepeatCount之后才能起作用。REVERSE 表示过来过去，RESTART 表示每次都重头运行。
//                ra.setRepeatMode(Animation.RESTART);
        // 动画延时执行
//        ra.setStartOffset(5000);
        // setFillAfter(true)动画停止在结束那一帧；setFillBefore(true)动画停止在开始那一帧
        ra.setFillAfter(true);
        // BounceInterpolator 弹力球效果
        ra.setInterpolator(new BounceInterpolator());
        // 动画总时长
        ra.setDuration(3000);
        ra.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Snackbar.make(iv,"开始",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Snackbar.make(iv,"结束",Snackbar.LENGTH_SHORT).show();
                ra.cancel();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Snackbar.make(iv,"从来",Snackbar.LENGTH_SHORT).show();
            }
        });


        /**
         * 补间平移动画
         * */
        // 代码设置
//        ta = new TranslateAnimation(
//                Animation.RELATIVE_TO_PARENT, // 设置起点X轴的取值类型（绝对、相对自己、相对父控件）
//                0.0f, // 起点坐标的X轴的值
//                Animation.RELATIVE_TO_PARENT,
//                0.5f, // 终点坐标的X轴的值
//                Animation.RELATIVE_TO_PARENT,
//                0.0f,  // 起点坐标的Y轴的值
//                Animation.RELATIVE_TO_PARENT,
//                0.5f); // 终点坐标的Y轴的值
        // xml实现
        ta = (TranslateAnimation) AnimationUtils.loadAnimation(mContext,R.anim.tween_translate);
        // 动画持续的时间
        ta.setDuration(1000);
        // 设置是否停留在动画结束的位置
        ta.setFillAfter(true);
        // 设置重复播放的次数（不包含第一次的动画）
        ta.setRepeatCount(2);
        // 设置动画倒放
        ta.setRepeatMode(Animation.REVERSE);



        /**
         * 补间缩放动画
         * */
        // 代码实现
//        sa = new ScaleAnimation(
//                1.0f, // X方向初始值
//                3.0f, // X方向缩放后的值
//                1.0f, // Y方向初始值
//                1.5f, // Y方向缩放后的值
//                Animation.RELATIVE_TO_SELF,
//                0.5f, // X轴中心
//                Animation.RELATIVE_TO_SELF,
//                0.5f); // Y轴中心
//        sa.setDuration(3000);
//        sa.setFillAfter(true);

        // xml 实现
        sa = (ScaleAnimation) AnimationUtils.loadAnimation(mContext,R.anim.tween_scale);



        /**
         * 补间渐透明动画
         * */
//        aa = new AlphaAnimation(1.0f,0.1f);
//        aa.setDuration(2000);
//        aa.setFillAfter(true);

        // xml 实现
        aa = (AlphaAnimation) AnimationUtils.loadAnimation(mContext,R.anim.tween_alpha);


        /**
         * 补间组合动画
         * */
        // 代码实现
//        as = new AnimationSet(true);
//        // 平移
//        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 200);
//        // 缩放
//        ScaleAnimation sa = new ScaleAnimation(1.0f, 2.0f, 1.0f, 0.5f,Animation.RELATIVE_TO_SELF,0.5F, Animation.RELATIVE_TO_SELF,0.5F);
//        // 旋转
//        RotateAnimation ra = new RotateAnimation(
//                0, // 起始位置的旋转角度
//                360, // 需要旋转的角度
//                Animation.RELATIVE_TO_SELF,
//                0.5f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f);
//        // 透明
//        AlphaAnimation aa = new AlphaAnimation(1.0f, 0.5f);
//
//        as.addAnimation(ta);
//        as.addAnimation(sa);
//        as.addAnimation(ra);
//        as.addAnimation(aa);
//        as.setDuration(5000);
//        as.setFillAfter(true);

        // xml 实现
        as = (AnimationSet) AnimationUtils.loadAnimation(mContext,R.anim.tween_set);

    }

    /**
     * 初始化控件
     * */
    private void initView() {
        iv  = (ImageView) findViewById(R.id.image);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"点击",Snackbar.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tween_rotate).setOnClickListener(this);
        findViewById(R.id.tween_translate).setOnClickListener(this);
        findViewById(R.id.tween_scale).setOnClickListener(this);
        findViewById(R.id.tween_alpha).setOnClickListener(this);
        findViewById(R.id.tween_set).setOnClickListener(this);
        findViewById(R.id.value_translate).setOnClickListener(this);
        findViewById(R.id.object_rotate).setOnClickListener(this);
        findViewById(R.id.object_scale).setOnClickListener(this);
        findViewById(R.id.object_alpha).setOnClickListener(this);
        findViewById(R.id.object_set).setOnClickListener(this);
        findViewById(R.id.object_random).setOnClickListener(this);
        findViewById(R.id.test_propertyValuesHolder).setOnClickListener(this);
        findViewById(R.id.test_view_animate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        iv.setVisibility(View.VISIBLE);
        Animator animator;
        switch(v.getId()){
            case R.id.tween_rotate:
                iv.setAnimation(ra);
                iv.startAnimation(ra);
                break;
            case R.id.tween_translate:
                iv.setAnimation(ta);
                iv.startAnimation(ta);
                break;
            case R.id.tween_scale:
                iv.setAnimation(sa);
                iv.startAnimation(sa);
                break;
            case R.id.tween_alpha:
                iv.setAnimation(aa);
                iv.startAnimation(aa);
                break;
            case R.id.tween_set:
                iv.setAnimation(as);
                iv.startAnimation(as);
                break;
            case R.id.value_translate:
                /**
                 * 属性平移
                 * */
                // 代码设置
//                ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationY", 0, 200,0);
//                oa.setDuration(2000);
//                oa.start();

                // xml实现
                animator = AnimatorInflater.loadAnimator(mContext,R.animator.object_translate);
                animator.setTarget(iv);
                animator.start();
                break;
            case R.id.object_rotate:
                /**
                 * 属性旋转(立体旋转)
                 * */
                // 代码设置
//                ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"rotationY",0,180);
//                oa.setDuration(2000);
//                oa.start();

                // xml实现
                animator = AnimatorInflater.loadAnimator(mContext,R.animator.object_rotate);
                animator.setTarget(iv);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Snackbar.make(iv,"onAnimationStart",Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Snackbar.make(iv,"onAnimationEnd",Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Snackbar.make(iv,"onAnimationCancel",Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Snackbar.make(iv,"onAnimationRepeat",Snackbar.LENGTH_SHORT).show();
                    }
                });
                animator.start();
                break;
            case R.id.object_scale:
                /**
                 * 属性缩放
                 * */
                // 代码设置
//                ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"scaleX",1.0f,2.0f);
//                oa.setDuration(2000);
//                oa.setAutoCancel(true);
//                oa.start();

                // xml设置
                animator = AnimatorInflater.loadAnimator(mContext,R.animator.object_scale);
                iv.setPivotX(0);
                iv.setPivotY(0);
                iv.invalidate();
                animator.setTarget(iv);
                animator.start();
                break;
            case R.id.object_alpha:
                /**
                 * 属性透明
                 * */
                // 代码设置
//                ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"alpha",1.0f,0.2f);
//                oa.setDuration(2000);
//                oa.setAutoCancel(true);
//                oa.start();

//                // xml设置
                animator = AnimatorInflater.loadAnimator(mContext,R.animator.object_alpha);
                animator.setTarget(iv);
                animator.start();
                break;
            case R.id.object_set:
                /**
                 * 属性组合动画
                 * */
                AnimatorSet aniSet = new AnimatorSet();
//                // 同时执行
////                aniSet.playTogether(
////                        ObjectAnimator.ofFloat(iv, "translationY", 0, 100),
////                        ObjectAnimator.ofFloat(iv, "scaleY", 1.0f, 3.0f),
////                        ObjectAnimator.ofFloat(iv, "rotationY", 0.0f, 180.0f),
////                        ObjectAnimator.ofFloat(iv, "alpha", 1.0f, 0.5f)
////                );
//                // 依次执行
////                aniSet.playSequentially(
////                        ObjectAnimator.ofFloat(iv, "translationY", 0, 100),
////                        ObjectAnimator.ofFloat(iv, "scaleY", 1.0f, 3.0f),
////                        ObjectAnimator.ofFloat(iv, "rotationY", 0.0f, 180.0f),
////                        ObjectAnimator.ofFloat(iv, "alpha", 1.0f, 0.5f));

                // 设置先后顺序
                ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "translationX", 0, 200);
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "scaleY", 1.0f, 3.0f);
                ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "rotationY", 0.0f, 180.0f);
                ObjectAnimator oa4 = ObjectAnimator.ofFloat(iv, "alpha", 1.0f, 0.5f);
                aniSet.play(oa1).with(oa2);
                aniSet.play(oa3).before(oa1);
                aniSet.play(oa4).after(oa2);

                aniSet.setDuration(5000);
                aniSet.start();

                // xml实现
//                animator = AnimatorInflater.loadAnimator(mContext,R.animator.object_set);
//                animator.setTarget(iv);
//                animator.start();
                break;
            case R.id.object_random:
                // 随便一个属性
                rotateyAnimRun(iv);
                break;
            case R.id.test_propertyValuesHolder:
                // 一个动画，多种效果
                propertyValuesHolder(iv);
                break;
            case R.id.test_view_animate:
                viewAnim(iv);
                break;
            default:
                break;
        }
    }

    public void rotateyAnimRun(final View view) {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(view, "jeffray", 1.0F,  0.0F)//
                .setDuration(5000);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }


    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(3000).start();



//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
//        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 0, -200, 0);
//        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).setDuration(1000).start();
    }

    public void viewAnim(final View view) {
        // need API12
        view.animate()//
                .alpha(0)//
                .y(-200).setDuration(2000)
                // need API 12
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(view,"withStartAction",Snackbar.LENGTH_SHORT).show();
                    }
                })
                // need API 16
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(view,"withEndAction",Snackbar.LENGTH_SHORT).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.setY(200);
                                view.setAlpha(1.0f);
                            }
                        });
                    }
                })
                .start();
    }
}
