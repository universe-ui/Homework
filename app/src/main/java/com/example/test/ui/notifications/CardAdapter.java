package com.example.test.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>{

    private ArrayList<HashMap<String, Integer>> list;
    private Context context;
    private int itemLayoutRes;

    public CardAdapter(Context context,int itemLayoutRes,ArrayList<HashMap<String, Integer>> list){
        this.list = list;
        this.context = context;
        this.itemLayoutRes = itemLayoutRes;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView moment;
        public CardViewHolder(View itemView){
            super(itemView);
            moment = itemView.findViewById(R.id.moment);
        }
    }
    @NonNull
    @Override
    public CardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cards_item,parent,false);
        return new CardAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.CardViewHolder holder, int position) {
        HashMap<String, Integer> map = (HashMap<String, Integer>) list.get(position);
        Glide.with(context).load((int) map.get("url")).into(holder.moment);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
