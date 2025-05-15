package com.example.test.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView pic;
    ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
    List<Integer> Honors = Arrays.asList(R.drawable.draft1,R.drawable.kill);
    List<Integer> Dribbling = Arrays.asList(R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5);
    List<Integer> Signed_sneakers = Arrays.asList(R.drawable.kk1,R.drawable.kk2,R.drawable.kk3,R.drawable.kk4);

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
        pic = findViewById(R.id.pic);
        recyclerView = findViewById(R.id.cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        Intent intent = getIntent();
        int key = intent.getIntExtra("key",-1);
        if (key == 1){
            for (int i = 0;i < Honors.size(); i++){
                HashMap<String,Integer> map = new HashMap<>();
                map.put("url",Honors.get(i));
                list.add(map);
            }
            headshot.setImageResource(R.drawable.m1);
            words.setText(R.string.word1);
            pic.setImageResource(R.drawable.cav);
        }
        else if (key == 2){
            for (int i = 0;i < Dribbling.size(); i++){
                HashMap<String,Integer> map = new HashMap<>();
                map.put("url",Dribbling.get(i));
                list.add(map);
            }
            headshot.setImageResource(R.drawable.m2);
            words.setText(R.string.word2);
            pic.setImageResource(R.drawable.kyrie);
        }
        else if (key == 3){
            for (int i = 0;i < Signed_sneakers.size(); i++){
                HashMap<String,Integer> map = new HashMap<>();
                map.put("url",Signed_sneakers.get(i));
                list.add(map);
            }
            headshot.setImageResource(R.drawable.m3);
            words.setText(R.string.word3);
            pic.setImageResource(R.drawable.kk);
        }
        CardAdapter adapter = new CardAdapter(this,R.layout.cards_item,list);
        recyclerView.setAdapter(adapter);
    }
}