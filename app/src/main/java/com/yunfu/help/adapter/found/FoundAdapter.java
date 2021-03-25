package com.yunfu.help.adapter.found;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.wx.goodview.GoodView;
import com.yunfu.help.R;
import com.yunfu.help.activity.found.FoundDetailsUI;
import com.yunfu.help.adapter.publi.ShowImgAdapter;
import com.yunfu.help.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoundAdapter extends BaseAdapter {

    private Activity activity;

    public FoundAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_dynamic, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.listImg.setLayoutManager(new GridLayoutManager(activity, 3));
        holder.listImg.setAdapter(new ShowImgAdapter(activity, null));


        /**
         * 菜单
         */
        holder.imgMenu.setOnClickListener(v -> new XPopup.Builder(activity)
            .hasShadowBg(false)
            .atView(v)
            .isCenterHorizontal(true)
            .borderRadius(5)
            .offsetX(30)
            .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
            .asAttachList(new String[]{"分享","删除"},null, (position1, text) -> {


            }) .show());


        /**
         * 点赞
         */
        holder.linGiveLike.setOnClickListener(v -> {
            final GoodView goodView=new GoodView(activity);
            goodView.setText("+1");
            goodView.show(v);
        });


        /**
         * 进入详情
         */
        holder.relClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, FoundDetailsUI.class);
                activity.startActivity(intent);
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.rel_click)
        RelativeLayout relClick;
        @BindView(R.id.img_head)
        CircleImageView imgHead;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.img_menu)
        ImageView imgMenu;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.list_img)
        RecyclerView listImg;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.img_give_like)
        ImageView imgGiveLike;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.lin_give_like)
        LinearLayout linGiveLike;
        @BindView(R.id.img_common)
        ImageView imgCommon;
        @BindView(R.id.tv_common_num)
        TextView tvCommonNum;
        @BindView(R.id.lin_common)
        LinearLayout linCommon;
        @BindView(R.id.img_coll)
        ImageView imgColl;
        @BindView(R.id.tv_coll_num)
        TextView tvCollNum;
        @BindView(R.id.lin_coll)
        LinearLayout linColl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
