package com.example.test.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.databinding.FragmentHomeBinding;
import com.example.test.ui.dashboard.PurchaseActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment{

    ListView schedule;
    MyAdapter adapter;
    private FragmentHomeBinding binding;
    private ProgressBar progressBar;
    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        schedule = binding.schedule;
        schedule.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("提醒");
                builder.setMessage("您要隐藏该比赛记录吗？");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(schedule.getItemAtPosition(position));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });
        progressBar = binding.progressBar;
        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                if (msg.what==0){
                    ArrayList<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>) msg.obj;
                    adapter = new MyAdapter(requireContext(),R.layout.list_item,list);
                    schedule.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
                super.handleMessage(msg);
            }
        };
        Thread t = new Thread(()->{
            try {
                Message msg = handler.obtainMessage(0);
                Thread.sleep(100);
                Document doc = Jsoup.connect("https://m.hupu.com/nba/schedule").get();
                Elements sections = doc.getElementsByTag("section");
                ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
                for (Element section : sections){
                    String date = section.children().get(0).getElementsByTag("h2").text();
                    Element general_div = section.children().get(1);
                    Elements divs = general_div.children();
                    for (Element div : divs){
                        HashMap<String,String> map = new HashMap<String,String>();
                        String time = div.children().get(0).getElementsByTag("strong").text();
                        String teamurl1 = div.children().get(1).getElementsByTag("img").get(0).attr("src");
                        String name1 = div.children().get(1).getElementsByTag("h5").get(0).text();
                        String teamurl2 = div.children().get(1).getElementsByTag("img").get(1).attr("src");
                        String name2 = div.children().get(1).getElementsByTag("h5").get(1).text();
                        String score1 = div.children().get(1).getElementsByTag("strong").get(0).text();
                        String score2 = div.children().get(1).getElementsByTag("strong").get(1).text();
                        String state = div.children().get(2).getElementsByTag("span").text();
                        map.put("date",date);
                        map.put("time",time);
                        map.put("teamurl1",teamurl1);
                        map.put("teamurl2",teamurl2);
                        map.put("name1",name1);
                        map.put("name2",name2);
                        map.put("score1",score1);
                        map.put("score2",score2);
                        map.put("state",state);
                        list.add(map);
                    }
                }
                msg.obj = list;
                handler.sendMessage(msg);
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        t.start();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}