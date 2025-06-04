package com.example.test.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment implements SneakerAdapter.OnItemClickListener{
    private static final String TAG = "DashboardFragment";
    private FragmentDashboardBinding binding;

    ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
    List<Integer> sneakernames = Arrays.asList(R.string.k0,R.string.huanying,R.string.k5,R.string.k6,R.string.k7,R.string.yinsu,R.string.k1,R.string.big3,R.string.harden,R.string.freak,R.string.aj,R.string.dunk);
    List<Integer> prices = Arrays.asList(R.string.k0p,R.string.huanyingp,R.string.k5p,R.string.k6p,R.string.k7p,R.string.yinsup,R.string.k1p,R.string.big3p,R.string.hardenp,R.string.freakp,R.string.ajp,R.string.dunkp);
    List<Integer> lookingurls = Arrays.asList(R.drawable.k0,R.drawable.huanying,R.drawable.k5,R.drawable.k6,R.drawable.k7,R.drawable.yinsu,R.drawable.k1,R.drawable.big3,R.drawable.harden,R.drawable.freak,R.drawable.aj,R.drawable.dunk);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        for (int i =0;i < prices.size();i++){
            HashMap<String,Integer> map = new HashMap<>();
            map.put("sneakername",sneakernames.get(i));
            map.put("price",prices.get(i));
            map.put("lookingurl",lookingurls.get(i));
            list.add(map);
        }
        SneakerAdapter adapter = new SneakerAdapter(requireContext(),R.layout.sneaker_item,list);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {  //设置点击事件
        HashMap<String,Integer> map = list.get(position);
        int sneakername = map.get("sneakername");
        int price = map.get("price");
        int lookingurl = map.get("lookingurl");
        Intent purchase = new Intent(requireContext(),PurchaseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sneakername",sneakername);
        bundle.putInt("price",price);
        bundle.putInt("lookingurl",lookingurl);
        purchase.putExtras(bundle);
        startActivityForResult(purchase,6666);
    }
}