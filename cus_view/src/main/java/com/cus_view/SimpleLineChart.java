package com.cus_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/***
 * 折线图
 *
 *
 *
 * @author Jeffray
 *
 * @created 2017/7/6 14:20
 ***/
public class SimpleLineChart extends View {
    private int mWidth;
    private int mHeight;
    // X轴的文字
    private String[] mXAxis = {};
    // Y轴的文字
    private String[] mYAxis = {};
    // 点的集合
    private HashMap<Integer, Integer> mPointMap;
    // Y轴字体的大小
    private float mYAxisFontSize = 24;
    // 没有数据的时候的内容
    private String mNoDataMsg = "no data";
    // 线的颜色
    private int mLineColor = Color.parseColor("#00BCD4");
    //点的半径
    private float mPointRadius = 8;
    //线条的宽度
    private float mStrokeWidth = 6.0f;

    public SimpleLineChart(Context context) {
        this(context,null);
    }

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleLineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 初始化
     * */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        } else if(widthMode == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("width must be EXACTLY,you should set like android:width=\"200dp\"");
        }

        if(heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        } else if(heightMode == MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("height must be EXACTLY,you should set like android:height=\"200dp\"");
        }

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mXAxis.length == 0 || mYAxis.length == 0){
            throw new IllegalArgumentException("X or Y items is null");
        }

        //画坐标线的轴
        Paint axisPaint = new Paint();
        axisPaint.setAntiAlias(true);
        axisPaint.setStyle(Paint.Style.FILL);
        axisPaint.setTextSize(mYAxisFontSize);
        axisPaint.setColor(Color.parseColor("#3F51B5"));
        if(mPointMap == null || mPointMap.size() == 0){ // 如果没有数据
            int textLength = (int) axisPaint.measureText(mNoDataMsg);
            canvas.drawText(mNoDataMsg,mWidth / 2 - textLength / 2,mHeight / 2,axisPaint);
        } else { // 如果有数据
            // 画Y轴

            // 存放每个Y轴的坐标
            int[] yPoints = new int[mYAxis.length];
            // 计算Y轴每个刻度的间距
            int yInterval = (int) ((mHeight - mYAxisFontSize - 2) / (mYAxis.length));
            for(int i = 0; i < mYAxis.length; i++){
                canvas.drawText(mYAxis[i],0,mYAxisFontSize + i * yInterval,axisPaint);
                yPoints[i] = (int) (mYAxisFontSize + i * yInterval);
            }


            // 画X轴

            //x轴的刻度集合
            // 存放每个X轴的坐标
            int[] xPoints = new int[mXAxis.length];
            // 计算Y轴开始的原点坐标
            int xItemX = (int) axisPaint.measureText(mYAxis[1]);
            //X轴偏移量
            int xOffset = 50;
            //计算x轴 刻度间距
            int xInterval = (int) ((mWidth - xOffset) / (mXAxis.length));
            //获取X轴刻度Y坐标
            int xItemY = (int) (mYAxisFontSize + mYAxis.length * yInterval);
            for(int i = 0; i < mXAxis.length; i++){
                canvas.drawText(mXAxis[i], i * xInterval + xItemX + xOffset, xItemY, axisPaint);
                xPoints[i] = (int) (i * xInterval + xItemX + axisPaint.measureText(mXAxis[i]) / 2 + xOffset + 2);
            }


            //画点
            Paint pointPaint = new Paint();
            pointPaint.setColor(mLineColor);
            pointPaint.setStyle(Paint.Style.FILL);

            Paint linePaint = new Paint();
            linePaint.setColor(mLineColor);
            linePaint.setAntiAlias(true);
            //设置线条宽度
            linePaint.setStrokeWidth(mStrokeWidth);

            for (int i = 0; i < mXAxis.length; i++) {
                if (mPointMap.get(i) == null) {
                    throw new IllegalArgumentException("PointMap has incomplete data!");
                }
                //画点
                canvas.drawCircle(xPoints[i], yPoints[mPointMap.get(i)], mPointRadius, pointPaint);
            if (i > 0) {
                canvas.drawLine(xPoints[i - 1], yPoints[mPointMap.get(i - 1)], xPoints[i], yPoints[mPointMap.get(i)], linePaint);
            }
            }
        }

    }


    /**
     * 设置Y轴文字
     * @param yItem
     */
    public void setYItem(String[] yItem){
        mYAxis = yItem;
    }

    /**
     * 设置X轴文字
     * @param xItem
     */
    public void setXItem(String[] xItem){
        mXAxis = xItem;
    }

    /**
     * 设置map数据
     * @param data
     */
    public void setData(HashMap<Integer,Integer> data){
        mPointMap = data;
        invalidate();
    }
}
