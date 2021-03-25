package com.yunfu.help.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.TypedValue;

import com.yunfu.help.application.MyApplication;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherUtils {

    /**
     * 保留小数的double数据
     * @param d
     * @return
     */
    public static String setDouble(double d,int type){
        DecimalFormat df=null;
        switch (type){
            case 0:
                df = new DecimalFormat("0");
                break;
            case 1:
                df = new DecimalFormat("0.0");
                break;
            case 2:
                df = new DecimalFormat("0.00");
                break;
            case 3:
                df = new DecimalFormat("0.000");
                break;
            case 4:
                df = new DecimalFormat("0.0000");
                break;
        }
        return df.format(d);
    }


    /**
     * 判断两个数组中是否有相同的元素
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isRepeat(String str1,String str2){
        String[] strOne=str1.split(",");
        String[] strTwo=str2.split(",");
        boolean has = false;
        Set<String> set = new HashSet<>(Arrays.asList(strOne));
        set.retainAll(Arrays.asList(strTwo));
        if(set.size() > 0){
            has =  true;
        }
        return has;
    }


    /**
     * 复制文字
     * @param message
     */
    public static void copyTxt(String message){
        ClipboardManager cm = (ClipboardManager) MyApplication.getApplication().getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("text", message));
        if (cm.hasPrimaryClip()) {
            cm.getPrimaryClip().getItemAt(0).getText();
        }
        ToastUtil.showLong("复制成功");
    }


    public static boolean judgeContainsStr(String str) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(str);
        return m.matches();
    }


    /**
     * 判断一个字符串是否含有数字
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }


    /**
     * 使用正则表达式来判断字符串中是否包含字母
     * @return 返回是否包含
     * true: 包含字母 ;false 不包含字母
     */
    public static boolean judgePwd(String content){
        if(judgeContainsStr(content) && hasDigit(content)){
            return true;
        }else{
            return false;
        }
    }


    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}
