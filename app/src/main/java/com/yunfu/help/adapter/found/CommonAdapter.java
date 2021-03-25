package com.yunfu.help.adapter.found;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.yunfu.help.R;
import com.yunfu.help.utils.OtherUtils;
import com.yunfu.help.view.CircleImageView;
import com.yunfu.help.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommonAdapter extends BaseAdapter {

    private Activity activity;

    public CommonAdapter(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 10;
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_common, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        /**
         * 举报
         */
        holder.imgReport.setOnClickListener(v -> new XPopup.Builder(activity)
                .borderRadius(5)
                .maxHeight(OtherUtils.dp2px(activity,260))
                .asBottomList("请选择举报理由", new String[]{"广告", "色情", "暴力", "欺诈", "骚扰","侵权","其他"},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position1, String text) {

                            }
                        })
                .show());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_head)
        CircleImageView imgHead;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.lin_give_like)
        LinearLayout linGiveLike;
        @BindView(R.id.img_give_like)
        ImageView imgGiveLike;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.img_report)
        ImageView imgReport;
        @BindView(R.id.reply_list)
        MeasureListView replyList;
        @BindView(R.id.tv_more)
        TextView tvMore;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
