package com.example.test.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SneakerAdapter extends RecyclerView.Adapter<SneakerAdapter.SneakerViewHolder> {

    private ArrayList<HashMap<String,Integer>> list;
    private Context context;
    private int itemLayoutRes;

    public SneakerAdapter(Context context,int itemLayoutRes,ArrayList<HashMap<String,Integer>> list){
        this.list = list;
        this.context = context;
        this.itemLayoutRes = itemLayoutRes;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class SneakerViewHolder extends RecyclerView.ViewHolder{
        ImageView looking;
        TextView sneakername;
        TextView price;
        public SneakerViewHolder(View itemView){
            super(itemView);
            looking = itemView.findViewById(R.id.looking);
            sneakername = itemView.findViewById(R.id.sneakername);
            price = itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public SneakerAdapter.SneakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sneaker_item,parent,false);
        return new SneakerViewHolder(view);
    }
    public void onBindViewHolder(SneakerViewHolder holder,int position){
        Map<String,Integer> map = (Map<String, Integer>) list.get(position);
        holder.sneakername.setText(map.get("sneakername").intValue());
        holder.price.setText(map.get("price").intValue());
        holder.looking.setImageResource(map.get("lookingurl").intValue());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }
    public int getItemCount(){
        return list.size();
    }
}
