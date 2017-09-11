package com.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends Activity {

	/*
	 * 实现手势最基本的使用方式
	 * 
	 * 当想要针对页面或者某控件去进行手势事件的监控的话
	 * 1. 针对页面或控件先获取对应的TouchEvent事件
	 * 2. 初始化手势对象
	 * 3. 将touch事件交由手势对象处理
	 * */
	GestureDetector gd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		gd = new GestureDetector(this, new GestureDetector.OnGestureListener() {
//			//手指抬起（一旦长按，或者滑动后抬手不运行此方法）
//			@Override
//			public boolean onSingleTapUp(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onSingleTapUp");
//				return false;
//			}
//			
//			//短按
//			@Override
//			public void onShowPress(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onShowPress");
//			}
//			//手指触摸时的滚动
//			/*
//			 * 1. 滑动起点
//			 * 2. 当前滑动话的点
//			 * 3. 水平方向上滑动的偏移距离 
//			 * 4. 垂直方向上滑动的偏移距离 
//			 * */
//			@Override
//			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//					float distanceY) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onScroll");
//				return false;
//			}
//			//长按
//			@Override
//			public void onLongPress(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onLongPress");
//			}
//			//快速松手后的惯性滑动
//			/*
//			 * 3. 水平方向上的滑动速度
//			 * 4. 垂直方向上的滑动速度
//			 * 速度单位：px/s
//			 * */
//			@Override
//			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//					float velocityY) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onFling  "+velocityX+"  "+velocityY);
//				return false;
//			}
//			//一旦手指按下，就会运行此方法
//			@Override
//			public boolean onDown(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onDown");
//				return false;
//			}
//		});
//		
//		//想要添加双击的话
//		gd.setOnDoubleTapListener(new OnDoubleTapListener() {
//			//单击事件
//			/*
//			 * onSingTabUp方法是在第一次手指抬起的时候，不过是单击还是双击，都运行
//			 * onSingleTapConfirmed 只有在单击事件手指抬起后运行
//			 * */
//			@Override
//			public boolean onSingleTapConfirmed(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onSingleTapConfirmed");
//				return false;
//			}
//			//双击（在第二次点击的按下和抬起的时候分别运行一次）
//			@Override
//			public boolean onDoubleTapEvent(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onDoubleTapEvent"+e.getAction());
//				return false;
//			}
//			//双击(在第二次点击下去的时候运行)
//			@Override
//			public boolean onDoubleTap(MotionEvent e) {
//				// TODO Auto-generated method stub
//				Log.i("=====", "==========onDoubleTap"+e.getAction());
//				return false;
//			}
//		});
		
		
		
		/*
		 * 当只想要获取手势中的部分监听的时候
		 * */
		gd = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				// TODO Auto-generated method stub
				Log.i("=====", "==========onDoubleTap"+e.getAction());
				
				return super.onDoubleTap(e);
				
			}

			@Override
			public boolean onDown(MotionEvent e) {
				Log.i("=====", "==========onDown"+e.getAction());
				return super.onDown(e);
			}

			@Override
			public void onLongPress(MotionEvent e) {
				Log.i("=====", "==========onLongPress"+e.getAction());
				super.onLongPress(e);
			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				Log.i("=====", "==========onFling"+e1.getAction());
				return super.onFling(e1, e2, velocityX, velocityY);
			}

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return super.onSingleTapUp(e);
			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				Log.i("=====", e1.getX()+ "   " + e1.getY());
				Log.i("=====", e2.getX()+ "   " + e2.getY());
				Log.i("=====", distanceX+"");
				Log.i("=====", distanceY+"");
				return super.onScroll(e1, e2, distanceX, distanceY);
			}

			@Override
			public void onShowPress(MotionEvent e) {
				Log.i("=====", "==========onShowPress"+e.getAction());
				super.onShowPress(e);
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				Log.i("=====", "==========onDoubleTapEvent"+e.getAction());
				return super.onDoubleTapEvent(e);
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				Log.i("=====", "==========onSingleTapConfirmed"+e.getAction());
				return super.onSingleTapConfirmed(e);
			}

			@Override
			public boolean onContextClick(MotionEvent e) {
				Log.i("=====", "==========onContextClick"+e.getAction());
				return super.onContextClick(e);
			}
		}) ;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 将touch事件交由手势对象处理
		//将touch事件的返回值修改为通过手势对象调用onTouchEvent方法
		/*
		 * 交给手势处理后，一旦触摸过程中符合手势中的特点，那么就会对应的运行
		 * 该手势对象接口中的相应方法
		 * */
		return gd.onTouchEvent(event);
	}
}
