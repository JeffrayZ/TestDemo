package com.recyclerview.util;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 文件工具类
 */
public class FileUtil {

    //读取Assets文件内容
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStream r = context.getResources().getAssets().open(fileName);
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            byte tmp[] = new byte[1024];
            byte content[];
            int i = 0;
            while ((i = r.read(tmp)) > 0) {
                byteout.write(tmp, 0, i);
            }
            content = byteout.toByteArray();
            String str = new String(content, "UTF-8");
            r.close();
            byteout.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.print(e.getMessage());
        }
        return null;
    }


}
