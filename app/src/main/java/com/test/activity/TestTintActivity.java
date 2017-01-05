package com.test.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.R;

import java.lang.reflect.Field;

public class TestTintActivity extends AppCompatActivity {

    private ImageView iv1, iv2,iv3;
    private EditText edittext1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tint);

        iv1 = (ImageView) findViewById(R.id.imageView2);
        iv2 = (ImageView) findViewById(R.id.imageView3);

        // 下面这段代码，iv1和iv2都会变色，原因是用的同一个图片，谷歌做的图片内存优化
//        final Drawable originBitmapDrawable = getResources().getDrawable(R.mipmap.ic_group_black_24dp);


        // 下面这段代码，只是iv1 变色，iv2不变
        // 那有人就要问了，卧槽 你这么做 不是把谷歌给我们做好的图片内存优化方案给损坏了么，其实这种担心是多余的.
        // 这个http://android-developers.blogspot.hk/2009/05/drawable-mutations.html
        // 这个地址会告诉你 其实我们做 只是把单独的受到影响的那部分 内存给单独拿出来了，其他没受到影响的还是共享的数据！
        // 换句话说 我们内存里 会另外存放的就是一些纯的标志位 之类的 类似于状态值这种东西。大部分的内存还是公用的！
        final Drawable originBitmapDrawable = getResources().getDrawable(R.mipmap.ic_group_black_24dp).mutate();
        // 给iv1着色
        iv1.setImageDrawable(tintDrawable(originBitmapDrawable, ColorStateList.valueOf(Color.GREEN)));


        edittext1 = (EditText) findViewById(R.id.edittext);
        final Drawable background = edittext1.getBackground();
        // edittext着色
        edittext1.setBackground(tintDrawable(background, ColorStateList.valueOf(Color.GREEN)));
        // 游标颜色
        invokeEditTextCallCursorDrawable(edittext1,Color.GREEN);

        editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setBackground(tintDrawable(editText2.getBackground(), getResources().getColorStateList(R.color.edittext_tint_colors)));
    }

    /**
     * tint 向下兼容方法
     */
    public Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    /**
     * 参数就是要反射修改光标的edittext对象，以及颜色
     * */
    private void invokeEditTextCallCursorDrawable(EditText et,int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            // 看源码知道 这个变量不是public的 所以要设置下这个可访问属性
            fCursorDrawableRes.setAccessible(true);
            //取得 editext对象里的mCursorDrawableRes 属性的值 看源码知道这是一个int值
            int mCursorDrawableRes = fCursorDrawableRes.getInt(et);
            //下面的代码 是通过获取mEditor对象 然后再通过拿到的mEditor对象来获取最终我们的mCursorDrawable这个光标的drawable
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(et);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            if (mCursorDrawableRes <= 0) {
                return;
            }
            //到这里 我们终于拿到了默认主题下 edittext的光标的那个小图标的drawable
            Drawable cursorDrawable = et.getContext().getResources().getDrawable(mCursorDrawableRes);
            if (cursorDrawable == null) {
                return;
            }
            //既然都拿到了这个drawble 那就修改他。
            Drawable tintDrawable = tintDrawable(cursorDrawable, ColorStateList.valueOf(color));
            //前面贴出的mCursorDrawable源码 可以知道 这是一个二维数组。所以我们要构造出一个全新的二维数组
            Drawable[] drawables = new Drawable[]{tintDrawable, tintDrawable};
            //然后再通过反射 把这个二维数组的值 放到editor里面 即可！
            fCursorDrawable.set(editor, drawables);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
