package com.test.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 2017/1/6.
 */

public class ImageUtil {

    /**
     * 存储在sd卡下面
     * */
    public static void saveBitmap2sdCard(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "tupian");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir,fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,10,fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储在app目录下面
     * */
    public static void saveBitmap2appCache(Bitmap bitmap) {

//        Context.getFileDir() ： /data/data/应用包名/files/
//        Context.getCacheDir() ： /data/data/应用包名/cache/
        File appDir = new File("/data/data/com.test.myapplication/cache/", "tupian");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir,fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 大小压缩
     * */
    public static Bitmap cropBitmap(String path, Bitmap bitmap, int maxW, int maxH) {

        if (path != null && bitmap != null) {
            return null;
        }
        if (path == null && bitmap == null) {
            return null;
        }
        if (maxW == 0 || maxH == 0) {
            return null;
        }
        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (path != null) {
            bm = BitmapFactory.decodeFile(path, options);
        }
        options.inJustDecodeBounds = false;
        int width = options.outWidth;
        int height = options.outHeight;

        if (width <= maxW && height <= maxH) {
            return BitmapFactory.decodeFile(path);
        } else if (width > maxW || height > maxH) {
            // 图片过大进行缩放
            int beW = 0;
            int beH = 0;
            int be = 1;
            if (maxH != 0 && maxW != 0) {
                beW = width / maxW;
                beH = height / maxH;
            }
            be = Math.max(beH, beW);
            if (be < 1) {
                be = 1;
            }
            options.inSampleSize = be;
            bm = BitmapFactory.decodeFile(path, options);
            // 重新测量长度，修改绝对的长和宽
            int scaleW = bm.getWidth();
            int scaleH = bm.getHeight();

            Matrix matrix = new Matrix();
            if (scaleH <= maxH && scaleW <= maxW) {
                return bm;
            }
            if (scaleH > maxH || scaleW > maxW) {
                float x = (float) maxW / (float) scaleW;
                float y = (float) maxH / (float) scaleH;
                if (x < y) {
                    matrix.postScale(x, x);
                } else {
                    matrix.postScale(y, y);
                }
                bm = Bitmap
                        .createBitmap(bm, 0, 0, scaleW, scaleH, matrix, true);
                return bm;
            }

        }
        return null;
    }

    /**
     * 质量压缩
     * @param quality 0~100 越大越清晰，越大压缩后图片大小越大。例如：80 代表只压缩 20%
     *
     * 如果你使用的透底的png图片，用这种方式进行压缩，就会出现黑底
     * */
    public static Bitmap compressQuality(Bitmap bitmap, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        byte[] compress_datas = outputStream.toByteArray();
        return BitmapFactory.decodeByteArray(compress_datas, 0, compress_datas.length);
    }

    /**
     * 尺寸压缩
     * */
    public static void compressBitmapToFile(Bitmap bmp, File file){
        // 尺寸压缩倍数,值越大，图片尺寸越小
        int ratio = 2;
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(bmp.getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100 ,baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采样率压缩
     * */
    public static void compressBitmap(String filePath, File file){
        // 数值越高，图片像素越低
        int inSampleSize = 2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        //采样率
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 ,baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getSDCard() {
        // 判断sd卡是否存在
        boolean issdCardExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        if (issdCardExist) {
            // 如果存在，获取根目录
            File sdDir = Environment.getExternalStorageDirectory();
            String abPath = sdDir.getAbsolutePath(); // 绝对路径
            String path = sdDir.getPath(); // 相对路径
            StatFs sf = new StatFs(path);
            long blockSize; // 获取单个数据块的大小(Byte)
            long allBlocks; // 获取数据块总数量
            long freeBlocks; // 获取可用数据块数量
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = sf.getBlockSizeLong();
                allBlocks = sf.getBlockCountLong();
                freeBlocks = sf.getFreeBlocksLong();
            } else {
                blockSize = sf.getBlockSize();
                allBlocks = sf.getBlockCount();
                freeBlocks = sf.getAvailableBlocks();
            }

//            long size = freeBlocks * blockSize; // 单位Byte
//            long size = (freeBlocks * blockSize) / 1024 ; // 单位KB
//            long size = (freeBlocks * blockSize) / 1024 / 1024; // 单位MB

        }
        return issdCardExist;
    }










    public static int getRatioSize(int bitWidth, int bitHeight) {
        // 图片最大分辨率
        int imageHeight = 1280;
        int imageWidth = 960;
        // 缩放比
        int ratio = 1;
        // 缩放比,由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        if (bitWidth > bitHeight && bitWidth > imageWidth) {
            // 如果图片宽度比高度大,以宽度为基准
            ratio = bitWidth / imageWidth;
        } else if (bitWidth < bitHeight && bitHeight > imageHeight) {
            // 如果图片高度比宽度大，以高度为基准
            ratio = bitHeight / imageHeight;
        }
        // 最小比率为1
        if (ratio <= 0)
            ratio = 1;
        return ratio;
    }

    public static void compressBitmap(Bitmap image, String filePath) {
        // 最大图片大小 100KB
        int maxSize = 100;
        // 获取尺寸压缩倍数
        int ratio = NativeUtil.getRatioSize(image.getWidth(), image.getHeight());
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(image.getWidth() / ratio, image.getHeight() / ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, image.getWidth() / ratio, image.getHeight() / ratio);
        canvas.drawBitmap(image, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        result.compress(Bitmap.CompressFormat.JPEG, options, baos);
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > maxSize) {
            // 重置baos即清空baos
            baos.reset();
            // 每次都减少10
            options -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            result.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        // JNI调用保存图片到SD卡 这个关键
        NativeUtil.saveBitmap(result, options, filePath, true);
        // 释放Bitmap
        if (result != null && !result.isRecycled()) {
            result.recycle();
            result = null;
        }
    }
}
