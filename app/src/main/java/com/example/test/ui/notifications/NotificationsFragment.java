package com.example.test.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.databinding.FragmentNotificationsBinding;
import com.google.android.material.chip.Chip;

public class NotificationsFragment extends Fragment {

    private static final String TAG = "NotificationsFragment";

    private Chip Honors;
    private Chip Core_strength;
    private Chip Dribbling;
    private Chip Signed_sneakers;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Honors = binding.Honors;
        Core_strength = binding.CoreStrength;
        Dribbling = binding.Dribbling;
        Signed_sneakers = binding.SignedSneakers;

        Honors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Honors = new Intent(requireContext(),com.example.test.ui.notifications.Honors.class);
                startActivity(Honors);
            }
        });

        Core_strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Dribbling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Signed_sneakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}