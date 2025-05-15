package com.example.test.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private ImageButton Honors;
    private ImageButton Dribbling;
    private ImageButton Signed_sneakers;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Honors = binding.Honors;
        Dribbling = binding.Dribbling;
        Signed_sneakers = binding.SignedSneakers;
        Honors.setOnClickListener(v -> model(1,requireContext()));
        Dribbling.setOnClickListener(v -> model(2,requireContext()));
        Signed_sneakers.setOnClickListener(v -> model(3,requireContext()));
        return root;
    }

    private void model(int key, Context context){
        Intent intent = new Intent(context, Model.class);
        intent.putExtra("key",key);
        startActivity(intent);
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}