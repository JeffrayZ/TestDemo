package com.cus_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/***
 * 自定义刻度表
 *
 *
 *
 * @author Jeffray
 *
 * @created 2017/8/7 10:00
 ***/
public class PanelView extends View {
    private int mWidth;
    private int mHeight;

    private Context mContext;
    // 设置文字颜色
    private int mTextColor;
    // 文字的大小
    private int mTextSize;
    // 文字内容
    private String mText = "";
    // 小方块的颜色、刻度的颜色、大圆的颜色
    private int mArcColor;
    // 小圆和指针颜色
    private int mMinCircleColor;
    // 刻度的个数
    private int mTikeCount;
    // 第二个弧的宽度（粗弧）
    private int mScendArcWidth;

    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PanelView, defStyleAttr, 0);
        mArcColor = a.getColor(R.styleable.PanelView_arcColor, Color.parseColor("#5FB1ED"));
        mMinCircleColor = a.getColor(R.styleable.PanelView_pointerColor, Color.parseColor("#C9DEEE"));
        mTikeCount = a.getInt(R.styleable.PanelView_tikeCount, 12);
        mTextSize = a.getDimensionPixelSize(PxUtils.spToPx(R.styleable.PanelView_android_textSize, mContext), 24);
        mText = a.getString(R.styleable.PanelView_android_text);
        mScendArcWidth = 50;
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }else {
            mWidth = PxUtils.dpToPx(200,mContext);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }else {
            mHeight = PxUtils.dpToPx(200,mContext);
        }

        setMeasuredDimension(mWidth,mHeight);
    }
}
