package com.example.test.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Model extends AppCompatActivity {

    RecyclerView recyclerView;
    private ImageView headshot;
    private TextView words;
    private TextView topic;
    private TextView detail;
    ArrayList<HashMap<String,Integer>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_model);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        headshot = findViewById(R.id.headshot);
        words = findViewById(R.id.words);
        topic = findViewById(R.id.title);
        detail = findViewById(R.id.content);
        recyclerView = findViewById(R.id.cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        Intent intent = getIntent();
        int key = intent.getIntExtra("key",-1);
        if (key == 1){
            for (int i = 0;i < KIdata.Honors.size(); i++){
                HashMap<String,Integer> map = new HashMap<>();
                map.put("url",KIdata.Honors.get(i));
                list.add(map);
            }
            headshot.setImageResource(R.drawable.m1);
            words.setText(R.string.word1);
            topic.setText(KIdata.Topic1);
            detail.setText(KIdata.Content1);
        }
        else if (key == 2){
            for (int i = 0;i < KIdata.Dribbling.size(); i++){
                HashMap<String,Integer> map = new HashMap<>();
                map.put("url",KIdata.Dribbling.get(i));
                list.add(map);
            }
            headshot.setImageResource(R.drawable.th);
            words.setText(R.string.word2);
            topic.setText(KIdata.Topic2);
            detail.setText(KIdata.Content2);
        }
        else if (key == 3){
            for (int i = 0;i < KIdata.Signed_sneakers.size(); i++){
                HashMap<String,Integer> map = new HashMap<>();
                map.put("url",KIdata.Signed_sneakers.get(i));
                list.add(map);
            }
            headshot.setImageResource(R.drawable.m3);
            words.setText(R.string.word3);
            topic.setText(KIdata.Topic3);
            detail.setText(KIdata.Content3);
        }
        CardAdapter adapter = new CardAdapter(this,R.layout.cards_item,list);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "滑动右下方动图", Toast.LENGTH_SHORT).show();
    }
}