package com.progressbar;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/***
 *
 *
 *
 *
 * @author Jeffray
 *
 * @created 2017/7/4 16:51
 ***/
public class ProgressDialog extends AppCompatDialog {
    private AnimationDrawable animationDrawable;

    protected ProgressDialog(@NonNull Context context) {
        this(context,R.style.loading_dialog);
    }

    public ProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    /**
     * 初始化
     * */
    ImageView iv;
    Animation animation;
    private void init(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.loading_progress, null);

        iv = (ImageView) view.findViewById(R.id.iv);
        animation = AnimationUtils.loadAnimation(context,R.anim.rotate);
//        iv.setImageResource(R.drawable.loading_anim);
//        animationDrawable = (AnimationDrawable) iv.getDrawable();

//        setCancelable(false);
//        setCanceledOnTouchOutside(false);

        setContentView(view);
    }

    public void showDialog(){
        show();
        iv.startAnimation(animation);
//        animationDrawable.start();
    }

    public void dismissDialog(){
        dismiss();
//        animationDrawable.stop();
        iv.clearAnimation();

    }
}
