package com.yunfu.help.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.hjq.permissions.Permission;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.yunfu.help.R;
import com.yunfu.help.callback.PermissionCallBack;

import java.io.File;

public class SelectPhotoUtil {


    //相册
    public static final int CODE_GALLERY_REQUEST = 0xa1;
    //拍照
    public static final int CODE_CAMERA_REQUEST = 0xa2;
    //裁剪
    public static final int CODE_RESULT_REQUEST = 0xa3;
    public static String pai;
    //裁剪后的图片路径
    public static String crop;

    /**
     * 选择照片
     */
    public static  void selectPhoto(Activity activity, int type){
        if(type==1){
            pai = FileUtils.getSdcardPath() + System.currentTimeMillis()+".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pai)));
            activity.startActivityForResult(intent, CODE_CAMERA_REQUEST);
        }else{
            Intent intent = new Intent(Intent.ACTION_PICK);
            // 设置文件类型
            intent.setType("image/*");
            activity.startActivityForResult(intent, CODE_GALLERY_REQUEST);
        }

    }


    /**
     * 裁剪原始的图片
     */
    public static void cropRawPhoto(Uri uri, Activity activity) {
        crop = FileUtils.getSdcardPath()+ System.currentTimeMillis()+".jpg";
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", Uri.fromFile(new File(crop)));
        // 设置裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        activity.startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 选择照片
     */
    public static void SelectPhoto(final Activity activity, final int maxNum){
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(maxNum)// 最大选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isCompress(true)// 是否压缩 true or false
                .isEnableCrop(false)// 是否裁剪 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}
