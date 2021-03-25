package com.yunfu.help.adapter.init;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yunfu.help.R;

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.MyHolder> {

    private Context context;
    private int position;
    public LineAdapter(Context context,int position) {
        super();
        this.context = context;
        this.position=position;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_line, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        if(position>=i){
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }else{
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.color_f9f9f9));
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        View view;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.view);
        }
    }

}

