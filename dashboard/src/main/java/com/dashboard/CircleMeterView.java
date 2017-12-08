package com.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

public class CircleMeterView extends View {
    // 外圆刻度画笔
    private Paint mScalePaint;

    // 外圆刻度数值画笔
    private Paint mScaleTextPaint;

    // 内圆画笔
    private Paint mInsidePaint;

    // 中间数值画笔mTextPaint
    private Paint mTextPaint;

    // 底部文字
    private Paint mTextPaintB;

    // 指针画笔
    private Paint mPointerPaint;

    // View宽
    private float mWidth;

    // View高
    private float mHeight;

    // 外刻度圆进度
    private float mProgress = 0;

    // 内刻度圆进度
    private float mInsideProgress = 0;

    // 中间显示的数值
    private float value = 0;

    private int scaleColor = Color.BLUE;

    private int scaleTextColor = Color.BLACK;

    private int insideCircleColor = Color.BLUE;

    private int textSize = 36;

    private int textColor = Color.BLACK;

    private int pointerColor = Color.RED;

    private Context mContext;

    public CircleMeterView(Context context) {
        this(context,null);
    }

    public CircleMeterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleMeterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initUI();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleMeterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initUI();
    }

    private void initUI() {
        mContext = getContext();

        // 刻度圆画笔
        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setStrokeWidth(3f);
        mScalePaint.setColor(scaleColor);
//        LinearGradient lg = new LinearGradient(0,0,100,100,Color.WHITE,Color.YELLOW, Shader.TileMode.CLAMP);
//        mScalePaint.setShader(lg);
        mScalePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setStrokeCap(Paint.Cap.ROUND);
        mScalePaint.setStyle(Paint.Style.STROKE);

        // 刻度文字画笔
        mScaleTextPaint = new Paint();
        mScaleTextPaint.setAntiAlias(true);
        mScaleTextPaint.setStrokeWidth(3f);
        mScaleTextPaint.setColor(scaleTextColor);
        mScaleTextPaint.setTextSize(24);
        mScaleTextPaint.setStyle(Paint.Style.FILL);

        // 中间值的画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(3f);
        mTextPaint.setTextSize(48);
        mTextPaint.setColor(textColor);
        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint.setStyle(Paint.Style.FILL);

        // 内部扇形刻度画笔
        mInsidePaint = new Paint();
        mInsidePaint.setAntiAlias(true);
        mInsidePaint.setStrokeWidth(3f);
        mInsidePaint.setColor(insideCircleColor);
        mInsidePaint.setStyle(Paint.Style.FILL);

        // 指针画笔
        mPointerPaint = new Paint();
        mPointerPaint.setAntiAlias(true);
        mPointerPaint.setStrokeWidth(3f);
        mPointerPaint.setColor(pointerColor);
        mPointerPaint.setStrokeCap(Paint.Cap.ROUND);
        mPointerPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // 中间值的画笔
        mTextPaintB = new Paint();
        mTextPaintB.setAntiAlias(true);
        mTextPaintB.setStrokeWidth(3f);
        mTextPaintB.setTextSize(48);
        mTextPaintB.setColor(textColor);
        mTextPaintB.setStrokeJoin(Paint.Join.ROUND);
        mTextPaintB.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArcScale(canvas);
        drawArcInside(canvas);
        drawInsideSumText(canvas);
        drawPointer(canvas);
        drawBottomSumText(canvas);
    }

    /**
     * 画外圆和文字刻度
     */
    private void drawArcScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(-45, mWidth / 2, mHeight / 2);

        // 最外圆的线条宽度，避免线条粗时被遮蔽
        float scaleWidth = mScalePaint.getStrokeWidth();

        canvas.drawArc(
                new RectF(scaleWidth, scaleWidth, mWidth - scaleWidth, mHeight - scaleWidth),
                180,
                270,
                false,
                mScalePaint);

        // 定义文字旋转回的角度
        int rotateValue = 30;
        // 总八个等分，每等分5个刻度，所以总共需要40个刻度
        for (int i = 0; i <= 40; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(scaleWidth, mHeight / 2, 20f, mHeight / 2, mScalePaint);
                // 画文字
                String text = String.valueOf(i / 5);
                Rect textBound = new Rect();
                mScaleTextPaint.getTextBounds(text, 0, text.length(), textBound);   // 获取文字的矩形范围
                int textWidth = textBound.right - textBound.left;  // 获得文字宽度
                int textHeight = textBound.bottom - textBound.top;  // 获得文字高度

                canvas.save();
                canvas.translate(15f + textWidth + 15f, mHeight / 2);  // 移动画布的圆点

                if (i == 0) {
                    // 如果刻度为0，则旋转度数为45度
                    canvas.rotate(rotateValue);
                } else {
                    // 大于0的刻度，需要逐渐递减45度
                    canvas.rotate(rotateValue);
                }
                rotateValue = rotateValue - 30;

                canvas.drawText(text, -textWidth / 2, textHeight / 2, mScaleTextPaint);
                canvas.restore();
            } else {
                canvas.drawLine(scaleWidth, mHeight / 2, 10f, mHeight / 2, mScalePaint);
            }
            canvas.rotate(6.75f, mWidth / 2, mHeight / 2);
        }
        canvas.restore();
    }

    /**
     * 画内圆刻度
     */
    private void drawArcInside(Canvas canvas) {
        canvas.save();
        canvas.rotate(-45, mWidth / 2, mHeight / 2);
        for (int i = 0; i < 100; i++) {
            if (mInsideProgress > i) {
                // 大于外圆刻度6时显示红色
                if (i <= 75) {
                    mInsidePaint.setColor(insideCircleColor);
                } else {
                    mInsidePaint.setColor(Color.RED);
                }
            } else {
                mInsidePaint.setColor(Color.LTGRAY);
            }
            canvas.drawLine(60, mHeight / 2, 70, mHeight / 2, mInsidePaint);
            canvas.rotate(2.7f, mWidth / 2, mHeight / 2);
        }

        canvas.restore();
    }

    /**
     * 画内部数值
     */
    private void drawInsideSumText(Canvas canvas) {
        canvas.save();

        if (mInsideProgress > 75) {
            mTextPaint.setColor(Color.RED);
        } else {
            mTextPaint.setColor(Color.BLACK);
        }
        // 获取文字居中显示需要的参数
        String showValue = String.valueOf(value);
        Rect textBound = new Rect();
        mTextPaint.getTextBounds(showValue, 0, showValue.length(), textBound);    // 获取文字的矩形范围
        float textWidth = textBound.right - textBound.left;  // 获得文字宽
        float textHeight = textBound.bottom - textBound.top; // 获得文字高
        canvas.drawText(showValue, mWidth / 2 - textWidth / 2, mHeight / 2 + textHeight + 45, mTextPaint);

        canvas.restore();
    }

    /**
     * 画指针
     */
    private void drawPointer(Canvas canvas) {
        canvas.save();

//        // 旋转到0的位置
//        canvas.rotate(-30, mWidth / 2, mHeight / 2);
//        canvas.rotate(mProgress, mWidth / 2, mHeight / 2);
//        canvas.drawLine(mWidth / 2 - 60, mHeight / 2, mWidth / 2, mHeight / 2, mPointerPaint);
//        canvas.drawCircle(mWidth / 2, mHeight / 2, 60, mPointerPaint);

        canvas.restore();
    }

    /**
     * 画底部数值
     */
    private void drawBottomSumText(Canvas canvas) {
        canvas.save();
        // 获取文字居中显示需要的参数
        String showValue = String.valueOf("优良");
        Rect textBound = new Rect();
        mTextPaint.getTextBounds(showValue, 0, showValue.length(), textBound);    // 获取文字的矩形范围
        float textWidth = textBound.right - textBound.left;  // 获得文字宽
        float textHeight = textBound.bottom - textBound.top; // 获得文字高
        canvas.drawText(
                showValue,
                mWidth / 2 - textWidth / 2,
                mHeight - textHeight,
                mTextPaint);

        canvas.restore();
    }


    /**
     * 设置进度
     */
    public void setProgress(int progress) {

        // 内部刻度的进度
        this.mInsideProgress = progress;

        // 指针显示的进度
        this.mProgress = (float) progress * 2.4f;

        // 设置中间文字显示的数值
        this.value = (float) (progress * 0.08);

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int myWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int myWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int myHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int myHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        // 获取宽度
        if (myWidthSpecMode == MeasureSpec.EXACTLY) {
            mWidth = myWidthSpecSize;
        } else {
            // wrap_content
            mWidth = 120;
        }

        // 获取高度
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = 120;
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }
}
