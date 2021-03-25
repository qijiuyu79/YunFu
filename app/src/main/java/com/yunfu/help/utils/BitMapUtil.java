package com.yunfu.help.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片工具类
 */
public class BitMapUtil {

    /**
     * 将图片的长和宽缩小为原来的1/2
     * @param imgPath
     * @return
     */
    public static Bitmap getBitMapBy2(String imgPath, int potions){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = potions;
        return BitmapFactory.decodeFile(imgPath,options);
    }


    /**
     * 质量压缩方法
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 60;
        while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于1000kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            options -= 10;// 每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /**
     * 对图片进行压缩
     * @param file
     * @return
     */
    public static String compressBitMap(File file){
        //将图片缩小为原来的一半
        Bitmap bitmap=getBitMapBy2(file.getPath(), 2);
        //对图片进行压缩
        bitmap = compressImage(bitmap);
        String newPath=FileUtils.getSdcardPath()+ System.currentTimeMillis()+"_"+(Math.random()*9+1)*1000+".jpg";
        try {
            file = new File(newPath);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null!=bitmap){
            bitmap.recycle();
            bitmap=null;
        }
        return newPath;
    }


    /**
     * drawable 转成bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }



    /**
     * Bitmap转换成Drawable
     * @param context
     */
    public static Drawable bitmapToDrawable(Context context, Bitmap bitmap){
        //因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        BitmapDrawable bd= new BitmapDrawable(context.getResources(), bitmap);
        return bd;
    }


    /**
     * bitmap转base64字符串
     * @param imgPath
     * @return
     */
    public static String getBase64Str(String imgPath){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap  = BitmapFactory.decodeStream(fis);
        String string = null;
        ByteArrayOutputStream btString = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, btString);
        byte[] bytes = btString.toByteArray();
        string = Base64.encodeToString(bytes, Base64.URL_SAFE);
        return string;
    }


    /**
     * 字符串转bitmap
     * @param string
     * @return
     */
    public static Bitmap getBitmap(String string){
        //将字符串转换成Bitmap类型
        Bitmap bitmap=null;
        try {
            byte[]bitmapArray;
            bitmapArray= Base64.decode(string, Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 放大缩小图片
     *
     * @param bitmap 源Bitmap
     * @param w 宽
     * @param h 高
     * @return 目标Bitmap
     */
    public static Bitmap zoom(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

}
