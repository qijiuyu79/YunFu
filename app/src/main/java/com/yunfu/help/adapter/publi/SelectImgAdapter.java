package com.yunfu.help.adapter.publi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.yunfu.help.R;
import com.yunfu.help.utils.ShowBigImgUtils;
import java.util.List;

public class SelectImgAdapter extends RecyclerView.Adapter<SelectImgAdapter.MyHolder>{

    private Activity activity;
    private List<String> imgList;
    public SelectImgAdapter(Activity activity, List<String> imgList) {
        super();
        this.activity = activity;
        this.imgList=imgList;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_select_img, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        Glide.with(activity).load(imgList.get(i)).into(holder.imgHead);
        holder.imgDelete.setVisibility(View.VISIBLE);


        /**
         * 查看大图
         */
        holder.imgHead.setTag(R.id.tag1,i);
        holder.imgHead.setOnClickListener(v -> {
            final int position= (int) v.getTag(R.id.tag1);
            ShowBigImgUtils.showImg(activity,imgList,position);
        });


        /**
         * 删除图片
         */
        holder.imgDelete.setTag(i);
        holder.imgDelete.setOnClickListener(v -> {
            imgList.remove(v.getTag());
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return imgList==null ? 0 : imgList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgHead,imgDelete;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHead=itemView.findViewById(R.id.img_head);
            imgDelete=itemView.findViewById(R.id.img_delete);
        }
    }

}

