package com.gesture;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ImageDoubleActivity extends Activity {

	/*
	 * 实现图片的双击放大与双击缩小
	 * */
	ImageView iv;
	
	GestureDetector gd;
	
	boolean flag = true; //假设flag为true---放大，false---缩小
	
	Bitmap source ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		iv = (ImageView)findViewById(R.id.imageView1);
		
		source = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
		/*
		 * 获取iv的双击事件
		 * */
		gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
			@Override
			public boolean onDoubleTap(MotionEvent e) {
				// TODO Auto-generated method stub
				if (flag) {
					//放大
					/*第一种实现方式：
					 * 通过放大ImageView的显示区域，并让图片缩放为跟ImageView同等大小
					 * */
//					iv.setScaleX(5);
//					iv.setScaleY(5);
//					iv.setScaleType(ScaleType.FIT_XY);
					
					/*
					 * 第二种方式：
					 * 改变图片的 大小
					 * */
					/*
					 * 1. 原始图片
					 * 2，3. 从原始图片那个点开始截取
					 * 4，5. 要从起始点开始截出多宽多高的距离
					 * 6. Matrix  矩阵  （实现缩放的关键）
					 * 7.最好设置为true， 这样的话，缩放后的图片会比较清晰
					 * */
					Matrix matrix = new Matrix();
					/*
					 * 1，2.水平方向与垂直方向上的缩放倍数
					 * 3，4. 缩放所围绕的中心点
					 * */
					matrix.setScale(5, 5);
					Bitmap bitm = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
					iv.setImageBitmap(bitm);
					
					flag = false;
				} else {
					//缩小
//					iv.setScaleX(1);
//					iv.setScaleY(1);
//					iv.setScaleType(ScaleType.FIT_XY);
					
					
					iv.setImageResource(R.mipmap.ic_launcher);
					flag = true;
				}
				return super.onDoubleTap(e);
			}
		});
		
		/*
		 * 需要先给控件添加支持点击功能的特点
		 * */
		iv.setClickable(true);
		iv.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return gd.onTouchEvent(event);
			}
		});
		
	}
}
