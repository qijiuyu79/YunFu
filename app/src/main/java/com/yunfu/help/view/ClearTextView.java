package com.yunfu.help.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.yunfu.help.R;

@SuppressLint("AppCompatCustomView")
public class ClearTextView extends TextView implements TextWatcher {
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable,showDrawable;
    private boolean isOpen=false;


    public ClearTextView(Context context) {
        this(context, null);
    }


    public ClearTextView(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }


    public ClearTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }




    private void init() {
        mClearDrawable = getCompoundDrawables()[2];
        showDrawable = getCompoundDrawables()[2];

        mClearDrawable = getResources().getDrawable(R.mipmap.down);
        showDrawable = getResources().getDrawable(R.mipmap.close_text);


        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        showDrawable.setBounds(0, 0, showDrawable.getIntrinsicWidth(), showDrawable.getIntrinsicHeight());

        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);

        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mClearDrawable, getCompoundDrawables()[3]);
    }




    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable && isOpen) {
                    this.setText("");
                    return false;
                }
            }

            this.performClick();
            return false;
        }

        return super.onTouchEvent(event);
    }


    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if(s.length()>0){
            isOpen=true;
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], showDrawable, getCompoundDrawables()[3]);
        }else{
            isOpen=false;
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mClearDrawable, getCompoundDrawables()[3]);
        }
    }




    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }




    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }




}