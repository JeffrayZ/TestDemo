package com.cus_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/***
 * 自定义控件
 *
 *
 *
 * @author Jeffray
 *
 * @created 2017/6/27 14:52
 ***/
public class CustomTitleView extends View {

    private String mTitleText; // 文本
    private int mTitleTextColor; // 文本颜色
    private int mTitleTextSize; // 文本大小
    /**
     * 绘制时控制文本绘制的范围
     * */
    private Rect mBound; // Rect类主要用于表示坐标系中的一块矩形区域，并可以对其做一些简单操作
    private Paint mPaint; // 画笔

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         * */
        TypedArray a = context
                .getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        try {
            for(int i = 0; i < a.getIndexCount(); i++){
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.CustomTitleView_titleText:
                        mTitleText = a.getString(attr);
                        break;
                    case R.styleable.CustomTitleView_titleTextColor:
                        mTitleTextColor = a.getColor(attr, Color.BLACK); // 默认黑色
                        break;
                    case R.styleable.CustomTitleView_titleTextSize:
                        // 默认设置为16sp，TypeValue也可以把sp转化为px
                        mTitleTextSize = a.getDimensionPixelSize(attr,
                                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                        break;
                    default:
                        break;
                }
            }
        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        setMeasuredDimension(width,height); // 设置背景的宽高，也就是当前画布的宽高
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(
                mTitleText,
                getWidth() / 2 - mBound.width() / 2, // 文字左边在屏幕的位置
                getHeight() / 2 + mBound.height() / 2, // 文字baseline在屏幕上的位置
                mPaint);
    }
}

/**
 * 当我们设置为WRAP_CONTENT,或者MATCH_PARENT系统帮我们测量的结果就是MATCH_PARENT的长度。
    所以，当设置了WRAP_CONTENT时，我们需要自己进行测量，即重写onMesure方法”：
    重写之前先了解MeasureSpec的specMode,一共三种类型：

     EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     UNSPECIFIED：表示子布局想要多大就多大，很少使用
 * */
