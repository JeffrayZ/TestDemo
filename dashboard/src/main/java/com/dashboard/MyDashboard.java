package com.dashboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/***
 * 描述信息：
 *
 * ${user}
 *
 * @author Jeffray
 *
 * @created ${date} ${time}
 ***/

public class MyDashboard extends View {
    // 外圆刻度画笔
    private Paint mScalePaint;

    // 内圆画笔
    private Paint mInsidePaint;

    // 中间数值画笔mTextPaint
    private Paint mTextPaint;

    // "分"画笔mTextPaintF
    private Paint mTextPaintF;

    // 底部文字
    private Paint mTextPaintB;

    // View宽
    private float mWidth;

    // View高
    private float mHeight;

    // 外刻度圆进度
    private float mProgress = 0;

    // 内刻度圆进度
    private float mInsideProgress = 0;

    // 中间显示的数值
    private int value = 0;

    private int scaleColor = Color.BLUE;

    private int scaleTextColor = Color.BLACK;

    private int insideCircleColor = Color.BLUE;

    private int textSize = 36;

    private int textColor = Color.BLACK;

    private int pointerColor = Color.RED;

    private Context mContext;

    private Paint bitmapPaint;

    public MyDashboard(Context context) {
        this(context, null);
    }

    public MyDashboard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initUI();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyDashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initUI();
    }

    private void initUI() {
        mContext = getContext();

        // 刻度圆画笔
        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setStrokeWidth(dp2px(1));
//        mScalePaint.setColor(Color.parseColor("#FEAF9E"));
        mScalePaint.setShader(generateSweepGradient());
        mScalePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setStrokeCap(Paint.Cap.ROUND);
        mScalePaint.setStyle(Paint.Style.STROKE);

        // 中间值的画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStrokeWidth(dp2px(1));
        mTextPaint.setTextSize(sp2px(56));
        mTextPaint.setColor(Color.parseColor("#ffdc87"));
        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mTextPaint.setStyle(Paint.Style.FILL);

        // "分"的画笔
        mTextPaintF = new Paint();
        mTextPaintF.setAntiAlias(true);
        mTextPaintF.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaintF.setStrokeWidth(dp2px(1));
        mTextPaintF.setTextSize(sp2px(13));
        mTextPaintF.setColor(Color.parseColor("#ffdc87"));
        mTextPaintF.setStrokeJoin(Paint.Join.ROUND);
        mTextPaintF.setStyle(Paint.Style.FILL);

        // 内部扇形刻度画笔
        mInsidePaint = new Paint();
        mInsidePaint.setAntiAlias(true);
        mInsidePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mInsidePaint.setStrokeWidth(dp2px(1));
        mInsidePaint.setColor(Color.parseColor("#FEAF9E"));
        mInsidePaint.setStyle(Paint.Style.FILL);

        // 底部文字的画笔
        mTextPaintB = new Paint();
        mTextPaintB.setAntiAlias(true);
        mTextPaintB.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaintB.setStrokeWidth(dp2px(1));
        mTextPaintB.setTextSize(sp2px(19));
        mTextPaintB.setColor(Color.WHITE);
        mTextPaintB.setStrokeJoin(Paint.Join.ROUND);
        mTextPaintB.setStyle(Paint.Style.FILL);

        // 图片画笔
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setFilterBitmap(true);
        bitmapPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawArcScale(canvas);
        drawArcInside(canvas);
        drawInsideSumText(canvas);
        drawBottomSumText(canvas);
    }

    /**
     * 画外圆和文字刻度
     */
    private void drawArcScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(-60, mWidth / 2, mHeight / 2);
        // 最外圆的线条宽度，避免线条粗时被遮蔽
        float scaleWidth = mScalePaint.getStrokeWidth();
        canvas.drawArc(
                new RectF(scaleWidth + 12, scaleWidth + 12, mWidth - scaleWidth - 12, mHeight - scaleWidth - 12),
                180,
                300,
                false,
                mScalePaint);

        canvas.restore();
    }

    /**
     * 画内圆刻度
     */
    private void drawArcInside(Canvas canvas) {
        canvas.save();
        canvas.rotate(-60, mWidth / 2, mHeight / 2);
        mInsidePaint.setColor(Color.parseColor("#FEAF9E"));
        for (int i = 0; i <= 100; i++) {
            canvas.drawLine(32f, mHeight / 2, 72f, mHeight / 2, mInsidePaint);
            canvas.rotate(3f, mWidth / 2, mHeight / 2);
        }
        canvas.restore();



        canvas.save();
        canvas.rotate(-60, mWidth / 2, mHeight / 2);

        mInsidePaint.setColor(Color.WHITE);
        if (isAnimFinish) {
            for (int i = 0; i <= mInsideProgress; i++) {
                if(i == mInsideProgress){
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_test_choice);
                    canvas.drawBitmap(
                            bitmap,
                            0,
                            mHeight / 2 - bitmap.getHeight() / 2,
                            bitmapPaint);
                }
                canvas.drawLine(32f, mHeight / 2, 72f, mHeight / 2, mInsidePaint);
                canvas.rotate(3f, mWidth / 2, mHeight / 2);
            }
        } else {
            for (int i = 0; i <= mCreditValue; i++) {
                if(i == mCreditValue){
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_test_choice);
                    canvas.drawBitmap(
                            bitmap,
                            0,
                            mHeight / 2 - bitmap.getHeight() / 2,
                            bitmapPaint);
                }
                canvas.drawLine(32f, mHeight / 2, 72f, mHeight / 2, mInsidePaint);
                canvas.rotate(3f, mWidth / 2, mHeight / 2);
            }
        }

        canvas.restore();
    }

    /**
     * 画内部数值
     */
    private void drawInsideSumText(Canvas canvas) {
        canvas.save();
        // 获取文字居中显示需要的参数
        String showValue = String.valueOf(value);
        Rect textBound = new Rect();
        mTextPaint.getTextBounds(showValue, 0, showValue.length(), textBound);    // 获取文字的矩形范围
        float textWidth = textBound.right - textBound.left;  // 获得文字宽
        float textHeight = textBound.bottom - textBound.top; // 获得文字高
        canvas.drawText(
                showValue,
                mWidth / 2 - textWidth / 2,
                mHeight / 2 + textHeight / 2,
                mTextPaint);
        // 画"分"
        String showValueF = "分";
        Rect textBoundF = new Rect();
        mTextPaintF.getTextBounds(showValueF, 0, showValueF.length(), textBoundF);// 获取文字的矩形范围
        float textWidthF = textBoundF.right - textBoundF.left;  // 获得文字宽
        float textHeightF = textBoundF.bottom - textBoundF.top; // 获得文字高
        canvas.drawText(
                showValueF,
                mWidth / 2 + textWidth / 2 + textHeightF / 2,
                mHeight / 2 + textHeight / 2,
                mTextPaintF);
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
        mTextPaintB.getTextBounds(showValue, 0, showValue.length(), textBound); // 获取文字的矩形范围
        float textWidth = textBound.right - textBound.left;  // 获得文字宽
        float textHeight = textBound.bottom - textBound.top; // 获得文字高
        canvas.drawText(
                showValue,
                mWidth / 2 - textWidth / 2,
                mHeight - textHeight,
                mTextPaintB);
        canvas.restore();
    }


    /**
     * 设置进度
     */
    private int mMin = 0; // 最小值
    private int mMax = 100; // 最大值
    private int mCreditValue = 0; // 得分
    private int mSolidCreditValue = mCreditValue; // 得分(设定后不变)
    /**
     * 由于真实的芝麻信用界面信用值不是线性排布，所以播放动画时若以信用值为参考，则会出现忽慢忽快
     * 的情况（开始以为是卡顿）。因此，先计算出最终到达角度，以扫过的角度为线性参考，动画就流畅了
     */
    private boolean isAnimFinish = true;

    public void setProgress(int progress) {

        // 内部刻度的进度
        this.mInsideProgress = progress;

        // 设置中间文字显示的数值
        this.value = progress;

        setCreditValueWithAnim(progress);

    }

    private void setCreditValueWithAnim(int progress) {
        if (progress < mMin || progress > mMax || !isAnimFinish) {
            return;
        }

        mSolidCreditValue = progress;

        ValueAnimator creditValueAnimator = ValueAnimator.ofInt(0, mSolidCreditValue);
        creditValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCreditValue = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        long delay = 2000;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet
                .setDuration(delay)
                .playTogether(creditValueAnimator);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimFinish = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimFinish = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                isAnimFinish = true;
            }
        });
        animatorSet.start();
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
            mWidth = 140;
        }

        // 获取高度
        if (myHeightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = myHeightSpecSize;
        } else {
            // wrap_content
            mHeight = 140;
        }

        // 设置该view的宽高
        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    private SweepGradient generateSweepGradient() {
        SweepGradient sweepGradient = new SweepGradient(mWidth / 2, mHeight / 2,
                new int[]{Color.argb(0, 255, 255, 255), Color.argb(200, 255, 255, 255)},
                new float[]{0, 360}
        );
        Matrix matrix = new Matrix();
        matrix.setRotate(180 - 1, mWidth / 2, mHeight / 2);
        sweepGradient.setLocalMatrix(matrix);

        return sweepGradient;
    }
}
