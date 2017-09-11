package com.cus_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
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

public class PopView extends View {

    public PopView(Context context) {
        this(context, null);
    }

    public PopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int mWidth = 0;
        int mHeight = 0;

        if(widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize; //获取到当前view的宽度

        }

        if(heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }

        setMeasuredDimension(mWidth,mHeight);
//        setPadding(80,80,80,80);
//        setBackgroundColor(Color.parseColor("#45c768"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.parseColor("#2c97de"));
//        p.setStyle(Paint.Style.FILL);

        RectF r = new RectF(0,0,getMeasuredWidth() - 50,getMeasuredHeight() - 50);
        canvas.drawRoundRect(r,10,10,p);

        Path path = new Path();
        path.moveTo(r.width() / 2 - 30,r.height());
        path.lineTo(r.width() / 2, r.height() + 20);
        path.lineTo(r.width() / 2 + 30,r.height());
        path.close();
        canvas.drawPath(path,p);
    }
}

/*
                        Paint p = new Paint();
                        // 设置画笔的颜色
                        p.setColor(Color.parseColor("#2EA4F2"));
                        // 设置画笔的风格：全部填充FILL   只画轮廓STROKE
                        p.setStyle(Paint.Style.STROKE);
                        // 设置画笔的宽度
                        p.setStrokeWidth(8);
                        // 设置是否抗锯齿
                        p.setAntiAlias(true);
                        // 设置文字大小
                        p.setTextSize(30);
                        // 测量字符串的长度
                        p.MeasureText("Hello World");



                        线：
                        //绘制一条从0,0到100,100的线
                        canvas.drawLine(0,0,100,100,p);



                        三角形&多边形是用Path类实现的，Path类提供了点绘制线的功能
                        path.MoveTo(0,0);//给定path的起点
                        path.LineTo(10,10);//往10，10绘制一条路径
                        path.LineTo(5,3);//继续从10，10往5,3绘制一条路径
                        path.close;//将绘制的线形成封闭空间
                        canvas.drawPath(path,p);



                        矩形：
                        //画一个矩形，左上角的坐标为0,0   右下角的坐标为100，50
                        canvas.drawRect(0,0,100,50,p);


                        圆角矩形：
                        //一个矩形
                        RectF rectF = new RectF(0,0,100,50);
                        //画一个圆角矩形，大小为rectF，20，20分表表示左边圆角的半径和右边圆角的半径
                        canvas.drawRoundRect(RectF,20,20,p);



                        圆形
                        //画一个圆，圆心为50，50  半径为100
                        canvas.drawCircle(50,50,100,p);


                        弧形
                        注意这里第二个参数，是从三点钟方向为0°计算，所以想从12点中方向开始绘制，那么就是270°。
                        第四个参数是决定是否经过圆心（自己改变一下这个参数就知道区别了）。
                        //画一个弧，弧所在矩形为rectF  从270°开始，画90° 不经过圆心
                        canvas.drawArc(rectF,270,90,false,p);

*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* */