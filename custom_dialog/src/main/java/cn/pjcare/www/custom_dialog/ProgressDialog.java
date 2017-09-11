package cn.pjcare.www.custom_dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
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
        this(context,R.style.DialogFullScreen);
    }

    public ProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    /**
     * 初始化
     * */
    private void init(Context context) {
//        DialogProgressBinding binding = DataBindingUtil.inflate(
//                getLayoutInflater(),R.layout.dialog_progress,null,false);
//        binding.iv.setImageResource(R.drawable.loading_anim);
//        setContentView(binding.getRoot());

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_progress, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        iv.setImageResource(R.drawable.loading_anim);
        animationDrawable = (AnimationDrawable) iv.getDrawable();

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(view);
    }

    public void showDialog(){
        show();
        animationDrawable.start();
    }

    public void dismissDialog(){
        dismiss();
        animationDrawable.stop();
    }
}
