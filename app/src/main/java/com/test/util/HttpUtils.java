package com.test.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import java.io.File;

/**
 * @author modosdom
 *         <p/>
 *         http工具类
 */
public class HttpUtils {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static SyncHttpClient client2 = new SyncHttpClient();

    public static void doHttpGet(Context context, String url, ResponseHandlerInterface responseHandler) {
        // 向服务器发起请求
        client.get(context, url, responseHandler);
    }

    public static void doHttpGetWithParams(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        // 向服务器发起请求
        client.get(context, url, params, responseHandler);
    }

    public static void doHttpPost(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        // 向服务器发起请求
        client.post(context, url, params, responseHandler);
    }

    public static void doHttpPost2(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        // 向服务器发起请求
        client2.post(context, url, params, responseHandler);
    }

    /**
     * 上传文件
     */
    public static void uploadFile(Context context, String url, String[] filePaths, String type, ResponseHandlerInterface responseHandler) {
        RequestParams params = new RequestParams();

        try {
            for (int i = 0; i < filePaths.length; i++) {
                if (filePaths[i] == null || "".equals(filePaths[i])) {
                    continue;
                }
                File file = new File(filePaths[i]);
                params.put("upload_file_" + i, file);
            }

            client.setMaxRetriesAndTimeout(1, 600000);
            client.setConnectTimeout(600000);
            client.setResponseTimeout(600000);
            client.post(context, url, params, responseHandler);
        } catch (Exception e) {

        }
    }

    /**
     * 上传文件
     */
    public static void uploadFileWithParams(Context context, String url, RequestParams params, String[] filePaths, String type, ResponseHandlerInterface responseHandler) {
        try {
            for (int i = 0; i < filePaths.length; i++) {
                if (filePaths[i] == null || "".equals(filePaths[i])) {
                    continue;
                }
                File file = new File(filePaths[i]);
                params.put("upload_file_" + i, file);
            }
            client.setMaxRetriesAndTimeout(1, 600000);
            client.setConnectTimeout(600000);
            client.setResponseTimeout(600000);
            client.post(context, url, params, responseHandler);
        } catch (Exception e) {

        }
    }

    /**
     * 下载文件
     *
     * @param context
     * @param fileUrl         下载文件的服务器路径
     * @param responseHandler
     */
    public static void downloadFile(Context context, String fileUrl, FileAsyncHttpResponseHandler responseHandler) {
        try {
            client.get(context, fileUrl, responseHandler);
        } catch (Exception e) {

        }
    }
}
