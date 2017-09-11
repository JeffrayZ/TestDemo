package com.cus_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/***
 * 描述信息：
 *
 * ${user}
 *
 * @author Jeffray
 *
 * @created ${date} ${time}
 ***/

public class WaveView extends View {

    private int mWaveCount;
    private int mWaveWidth;
    private float mWaveHeight;
    private float diameter;
    private int mMode;
    private int mColor;
    private float mRectWidth;
    private float mRectHeight;
    private static final int MODE_TRIANGLE = -2;
    private Path path;
    private Paint p;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveView, defStyleAttr, 0);
        mWaveCount = a.getInt(R.styleable.WaveView_waveCount, 10);
        mWaveWidth = a.getInt(R.styleable.WaveView_waveWith, 20);
        mMode = a.getInt(R.styleable.WaveView_mode, -2);
        mColor = a.getColor(R.styleable.WaveView_waveColor, Color.parseColor("#2c97de"));
        a.recycle();
        path = new Path();
        p = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int mWidth;
        int mHeight;
        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        } else {
            mWidth = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    400,
                    getResources().getDisplayMetrics());
        }
        mRectWidth = (float) (mWidth * 0.8);

        if(heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        } else {
            mHeight = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    100,
                    getResources().getDisplayMetrics());
        }
        mRectHeight = (float) (mHeight * 0.8);

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        p.setAntiAlias(true);
        p.setColor(mColor);
        // 计算每个三角行的高
        mWaveHeight = mRectHeight / mWaveCount;
        // 绘制矩形
        float padding = (getMeasuredWidth() - mRectWidth) / 2;
        canvas.drawRect(padding,padding,mRectWidth + padding, mRectHeight + padding,p);

        if(mMode == MODE_TRIANGLE){
            // 绘制左边波浪线
            float startX = padding;
            float startY = padding;
            path.moveTo(startX,startY);
            for(int i = 0; i < mWaveCount;i++){
                path.lineTo(startX - mWaveWidth, startY + i * mWaveHeight + (mWaveHeight / 2));
                path.lineTo(startX,startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path,p);

            // 绘制右边波浪线
            startX = padding + mRectWidth;
            startY = padding;
            path = new Path();
            path.moveTo(startX,startY);
            for(int i = 0; i < mWaveCount;i++){
                path.lineTo(startX + mWaveWidth, startY + i * mWaveHeight + (mWaveHeight / 2));
                path.lineTo(startX,startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path,p);
        } else {
            // 直径
            diameter = mRectHeight / mWaveCount;
            // 绘制右边波浪
            float startX = padding + mRectWidth;
            float startY = padding;
            for(int i = 0; i < mWaveCount; i++){
                canvas.drawCircle(startX,startY + i * diameter + (diameter / 2),diameter / 2,p);
            }

            // 绘制左边波浪
            startX = padding;
            startY = padding;
            for(int i = 0; i < mWaveCount; i++){
                canvas.drawCircle(startX,startY + i * diameter + (diameter / 2),diameter / 2,p);
            }
        }
    }
}
