package com.test.util;

import android.app.Dialog;
import android.content.Context;

import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

public class MyFileAsyncHttpResponseHandler extends FileAsyncHttpResponseHandler {

	private Boolean isProgressNeeded;
	private int resId;
	private Dialog mProgressbarDialog;
	private Context context;
	private Object obj;
	
	public MyFileAsyncHttpResponseHandler(Context context) {
		super(context);
		this.context = context;
		this.isProgressNeeded = false;
	}

	@Override
	public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, File file) {
//		if (isProgressNeeded) {
//			mProgressbarDialog.dismiss();
//		}
	}

	@Override
	public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, File file) {
//		if (isProgressNeeded) {
//			mProgressbarDialog.dismiss();
//		}
	}

	public MyFileAsyncHttpResponseHandler(Context context, int resId) {
		super(context);
		this.context = context;
		this.isProgressNeeded = true;
		this.resId = resId;
	}
	
	public MyFileAsyncHttpResponseHandler(Context context, File file) {
		super(file);
		this.context = context;
		this.isProgressNeeded = false;
	}
	
	public MyFileAsyncHttpResponseHandler(Context context, File file, int resId) {
		super(file);
		this.context = context;
		this.isProgressNeeded = true;
		this.resId = resId;
	}
	
	public MyFileAsyncHttpResponseHandler(Context context, File file, Object obj) {
		super(file);
		this.context = context;
		this.obj = obj;
		isProgressNeeded = false;
	}
	
	@Override
	public void onStart() {
//		if (isProgressNeeded) {
//			mProgressbarDialog = CommonUtils.getLoadingProgressDialog((Activity)context, context.getString(resId));
//			mProgressbarDialog.show();
//			mProgressbarDialog.setCancelable(false);
//		}
	}
}
