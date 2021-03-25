package com.yunfu.help.adapter.init;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunfu.help.R;
import com.yunfu.help.bean.DiseaseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditDiseaseAdapter extends BaseAdapter {

    private Activity activity;
    private List<DiseaseBean> list;

    public EditDiseaseAdapter(Activity activity, List<DiseaseBean> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_edit_disease, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final DiseaseBean diseaseBean=list.get(position);
        holder.tvName.setText(diseaseBean.getName());
        if(diseaseBean.isSelect()){
            holder.relBg.setBackgroundResource(R.drawable.bg_blue_15);
            holder.tvName.setTextColor(activity.getResources().getColor(android.R.color.white));
        }else{
            holder.relBg.setBackgroundResource(R.drawable.bg_gray_15);
            holder.tvName.setTextColor(activity.getResources().getColor(R.color.color_333333));
        }

        holder.relBg.setTag(diseaseBean);
        holder.relBg.setOnClickListener(v -> {
            final DiseaseBean object= (DiseaseBean) v.getTag();
            if(object.isSelect()){
                object.setSelect(false);
            }else{
                object.setSelect(true);
            }
            notifyDataSetChanged();
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.rel_bg)
        RelativeLayout relBg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
