package com.yunfu.help.activity.mine;

import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置昵称
 */
public class EditNickNameUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_num)
    TextView tvNum;
    /**
     * 1：昵称
     * 2：个人介绍
     */
    private int type;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_nickname;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvRight.setText("完成");
        type=getIntent().getIntExtra("type",0);
        if(type==1){
            tvHead.setText("昵称");
            etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
            tvNum.setText("0/10");
        }else{
            tvHead.setText("自我介绍");
            etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
            tvNum.setText("0/30");
        }

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(type==1){
                    tvNum.setText(s.length()+"/10");
                }else{
                    tvNum.setText(s.length()+"/30");
                }
            }
        });
    }


    @OnClick({R.id.lin_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_right:
                Intent intent=new Intent();
                intent.putExtra("data",etContent.getText().toString().trim());
                if(type==1){
                    setResult(1000,intent);
                }else{
                    setResult(2000,intent);
                }
                finish();
                break;
            default:
                break;
        }
    }
}
