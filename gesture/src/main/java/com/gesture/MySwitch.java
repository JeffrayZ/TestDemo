package com.gesture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MySwitch extends View {
	
	private Bitmap bgImg, btnImg;
	private Paint mPaint;
	private float cx;
	private float btnLeft; // 记录滑块左上角坐标点的X轴的坐标值
	private boolean isMove = false; // 记录滑块是否正在滑动
	private OnCheckedChangeListener listener;
	private boolean isOpen = false;
	
	public interface OnCheckedChangeListener {
		public abstract void onCheckedChanged(MySwitch view, boolean isOpen);
	}
	
	public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
		this.listener = listener;
	}
	
	public MySwitch(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public MySwitch(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MySwitch(Context context) {
		this(context, null);
	}
	
	private void initView() {
		/*
		 * 动态设置图片源
		 * */
		bgImg = BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
		btnImg = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button_background);
		// 画笔
		mPaint = new Paint();
	}
	
	/**
	 * 测量当前控件的大小
	 * */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = bgImg.getWidth();
		int height = bgImg.getHeight();
		setMeasuredDimension(width, height);
	}	
	
	/**
	 * 绘制当控件时显示的内容
	 * */
	@Override
	protected void onDraw(Canvas canvas) {
		Log.i("haha", "onDraw");
		
		canvas.drawBitmap(bgImg, 0, 0, mPaint);
		if(isMove){
			Log.i("haha", "move");
			// 判断并修正btnLeft的值
			if(btnLeft < 0){
				btnLeft = 0;
			} else if(btnLeft >bgImg.getWidth() - btnImg.getWidth()){
				btnLeft = bgImg.getWidth() - btnImg.getWidth();
			}
		} else {
			Log.i("haha", "unmove");
			if(btnLeft + btnImg.getWidth()/2 <= bgImg.getWidth()/2){
				btnLeft = 0;
				isOpen = false;
			} else {
				btnLeft = bgImg.getWidth() - btnImg.getWidth();
				isOpen = true;
			}
			registerListener();
		}
		
		canvas.drawBitmap(btnImg, btnLeft, 0, mPaint);
		
	}

	private void registerListener() {
		if(listener != null){
			listener.onCheckedChanged(this, isOpen);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			cx = event.getX();
			isMove = true;
			break;
		case MotionEvent.ACTION_MOVE:
			float dx = event.getX() - cx;
			btnLeft = btnLeft + dx;
			cx = event.getX();
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isMove = false;
			invalidate();
			break;
		default:
			break;
		}
		
		return true;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
}
