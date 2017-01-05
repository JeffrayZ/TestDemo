package com.test.util;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/********
 * @author： Jeffray zhanfeifei1991@163.com
 * @time： 2016/4/21 11:13
 * @description： 网络有关的工具类。包括上传、下载、访问等
 * @Copyright： 2016 www.modosdom.com Inc. All rights reserved.
 ********/
public class MyJsonHttpResponseHandler extends JsonHttpResponseHandler {
    private Boolean isProgressNeeded;
    private int resId;
    private Dialog mProgressbarDialog;
    private Context context;
//    private BaseSharePreferenceUtil spUtil;

    public MyJsonHttpResponseHandler(Context context) {
        this.context = context;
        this.isProgressNeeded = false;
//        this.spUtil = new BaseSharePreferenceUtil(context, BaseConstant.SP_FILE_NAME);
    }

    public MyJsonHttpResponseHandler(Context context, Boolean isProgressNeeded, int resId) {
        this.context = context;
        this.isProgressNeeded = isProgressNeeded;
        this.resId = resId;
//        this.spUtil = new BaseSharePreferenceUtil(context, BaseConstant.SP_FILE_NAME);
    }

    @Override
    public void onStart() {
        if (isProgressNeeded) {
//            mProgressbarDialog = CommonUtils.getLoadingProgressDialog((Activity) context, context.getString(resId));
//            mProgressbarDialog.show();
//            mProgressbarDialog.setCancelable(false);
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
//        if (isProgressNeeded) {
//            mProgressbarDialog.dismiss();
//        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        super.onSuccess(statusCode, headers, response);
//        if (isProgressNeeded) {
//            mProgressbarDialog.dismiss();
//        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        super.onSuccess(statusCode, headers, responseString);
//        if (isProgressNeeded) {
//            mProgressbarDialog.dismiss();
//        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
//        if (isProgressNeeded) {
//            mProgressbarDialog.dismiss();
//        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
//        if (isProgressNeeded) {
//            mProgressbarDialog.dismiss();
//        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
//        if (isProgressNeeded) {
//            mProgressbarDialog.dismiss();
//        }
        Toast.makeText(context, "无法连接到服务器", Toast.LENGTH_SHORT).show();
    }
}
