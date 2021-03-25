package com.yunfu.help.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.yunfu.help.R;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.utils.SelectPhotoUtil;
import com.yunfu.help.view.CircleImageView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改用户信息
 */
public class UserInfoUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    private Intent intent=new Intent();

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("个人信息");
    }


    @OnClick({R.id.lin_back, R.id.img_head,R.id.tv_nickname, R.id.tv_remark,R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择头像
            case R.id.img_head:
                SelectPhotoUtil.SelectPhoto(this,1);
                break;
            case R.id.tv_nickname:
                 intent.setClass(this,EditNickNameUI.class);
                 intent.putExtra("type",1);
                 startActivityForResult(intent,1000);
                 break;
            case R.id.tv_remark:
                 intent.setClass(this,EditNickNameUI.class);
                 intent.putExtra("type",2);
                 startActivityForResult(intent,2000);
                 break;
            case R.id.tv_save:
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //返回图片
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            if(list.size()==0){
                return;
            }
            Glide.with(activity).load(list.get(0).getCompressPath()).into(imgHead);
        }
        if(resultCode==1000){
            tvNickname.setText(data.getStringExtra("data"));
        }
        if(resultCode==2000){
            tvRemark.setText(data.getStringExtra("data"));
        }
    }
}
