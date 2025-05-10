package com.example.test.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter{
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HashMap<String,String>> lists) {
        super(context, resource, lists);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,
                    false);
        }
        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView date = itemView.findViewById(R.id.date);
        TextView time = itemView.findViewById(R.id.time);
        TextView name1 = itemView.findViewById(R.id.name1);
        TextView name2 = itemView.findViewById(R.id.name2);
        TextView score1 = itemView.findViewById(R.id.score1);
        TextView score2 = itemView.findViewById(R.id.score2);
        TextView state = itemView.findViewById(R.id.state);
        ImageView team1 = itemView.findViewById(R.id.team1);
        ImageView team2 = itemView.findViewById(R.id.team2);
        date.setText(map.get("date"));
        time.setText(map.get("time"));
        name1.setText(map.get("name1"));
        name2.setText(map.get("name2"));
        score1.setText(map.get("score1"));
        score2.setText(map.get("score2"));
        state.setText(map.get("state"));
        Glide.with(getContext()).load(map.get("teamurl1")).into(team1);
        Glide.with(getContext()).load(map.get("teamurl2")).into(team2);
        return itemView;
    }
}
