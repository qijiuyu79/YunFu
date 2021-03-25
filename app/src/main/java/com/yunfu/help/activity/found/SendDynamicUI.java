package com.yunfu.help.activity.found;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.mapapi.search.core.PoiInfo;
import com.hjq.permissions.Permission;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.yunfu.help.R;
import com.yunfu.help.adapter.publi.SelectImgAdapter;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.callback.PermissionCallBack;
import com.yunfu.help.utils.PermissionUtil;
import com.yunfu.help.utils.SelectPhotoUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconsFragment;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

/**
 * 发布动态
 */
public class SendDynamicUI extends BaseActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener{
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_content)
    EmojiconEditText etContent;
    @BindView(R.id.listView)
    RecyclerView listView;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_is_look)
    TextView tvIsLook;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.rel_emoji)
    RelativeLayout relEmoji;
    @BindView(R.id.emojicons)
    FrameLayout emojicons;
    //位置对象
    private PoiInfo poiInfo;
    private Drawable radioYes,redioNo;
    /**
     * 是否仅自己可见
     */
    private boolean isLook=false;
    //选择的照片
    private List<String> imgList=new ArrayList<>();
    //图片列表适配器
    private SelectImgAdapter adapter;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_dynamic;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        tvHead.setText("发布动态");
        tvRight.setText("发布");
        radioYes= activity.getResources().getDrawable(R.mipmap.check_yes);
        redioNo= activity.getResources().getDrawable(R.mipmap.check_no);

        listView.setLayoutManager(new GridLayoutManager(activity, 4));
        listView.setAdapter(adapter=new SelectImgAdapter(this,imgList));

        getSupportFragmentManager().beginTransaction().replace(R.id.emojicons, EmojiconsFragment.newInstance(false)).commit();

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvNum.setText(s.length()+"/300");
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_right, R.id.tv_is_look, R.id.img_photo, R.id.img_icon, R.id.img_location,R.id.rel_emoji})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //发布
            case R.id.tv_right:
                break;
            //是否仅自己可见
            case R.id.tv_is_look:
                 if(isLook){
                     isLook=false;
                     tvIsLook.setCompoundDrawablesWithIntrinsicBounds(redioNo, null, null, null);
                 }else{
                     isLook=true;
                     tvIsLook.setCompoundDrawablesWithIntrinsicBounds(radioYes, null, null, null);
                 }
                break;
            //选择图片
            case R.id.img_photo:
                SelectPhotoUtil.SelectPhoto(activity,9);
                break;
            //选择笑脸
            case R.id.img_icon:
                relEmoji.setVisibility(View.VISIBLE);
                break;
            //选择位置
            case R.id.img_location:
                if(!PermissionUtil.isPermission(this, new PermissionCallBack() {
                    @Override
                    public void onSuccess() {
                        setClass(SelectCityActivity.class,1000);
                    }
                    @Override
                    public void onFail() {

                    }
                }, Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION)){}
                break;
            //因此表情框
            case R.id.rel_emoji:
                 relEmoji.setVisibility(View.GONE);
                 break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //返回图片
        if(requestCode== PictureConfig.CHOOSE_REQUEST){
            List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
            for (int i=0;i<list.size();i++){
                imgList.add(list.get(i).getCompressPath());
            }
            adapter.notifyDataSetChanged();
        }
        //返回位置
        if(resultCode==1000){
            poiInfo=data.getParcelableExtra("PoiInfo");
            tvLocation.setVisibility(View.VISIBLE);
            tvLocation.setText(poiInfo.getAddress());
        }
    }


    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        EmojiconsFragment.input(etContent, emojicon);
    }

    @Override
    public void onEmojiconBackspaceClicked(View v) {
        EmojiconsFragment.backspace(etContent);
    }
}
