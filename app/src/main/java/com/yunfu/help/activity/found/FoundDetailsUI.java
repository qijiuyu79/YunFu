package com.yunfu.help.activity.found;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.yunfu.help.R;
import com.yunfu.help.adapter.found.CommonAdapter;
import com.yunfu.help.adapter.publi.ShowImgAdapter;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.view.CircleImageView;
import com.yunfu.help.view.MeasureListView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 动态详情
 */
public class FoundDetailsUI extends BaseActivity {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.list_img)
    RecyclerView listImg;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_common_num)
    TextView tvCommonNum;
    @BindView(R.id.list_common)
    MeasureListView listCommon;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_found_details;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("动态详情");
        imgRight.setImageResource(R.mipmap.menu_icon2);

        listImg.setLayoutManager(new GridLayoutManager(activity, 3));
        listImg.setAdapter(new ShowImgAdapter(activity, null));

        listCommon.setAdapter(new CommonAdapter(this));
    }


    @OnClick({R.id.lin_back, R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //菜单
            case R.id.img_right:
                new XPopup.Builder(activity)
                        .hasShadowBg(false)
                        .atView(view)
                        .isCenterHorizontal(true)
                        .borderRadius(5)
                        .offsetX(30)
                        .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
                        .asAttachList(new String[]{"分享","删除"},null, (position1, text) -> {

                        }) .show();
                break;
            default:
                break;
        }
    }
}
