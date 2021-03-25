package com.yunfu.help.utils;

import android.app.Activity;

import java.util.List;

import cc.shinichi.library.ImagePreview;

/**
 * 预览大图
 */
public class ShowBigImgUtils {

    public static void showImg(Activity activity,List<String> imgList,int position){
        ImagePreview
                .getInstance()
                .setContext(activity)
                .setIndex(position) // 默认显示第几个
                .setImageList(imgList) // 图片集合
                .setShowDownButton(false) // 是否显示下载按钮
                .setFolderName("BigImageViewDownload") // 设置下载到的文件夹名（保存到根目录）
                .setZoomTransitionDuration(500) // 设置缩放的动画时长
                .setEnableDragClose(true)//设置是否开启下拉图片退出
                .start(); // 开始跳转
    }
}
