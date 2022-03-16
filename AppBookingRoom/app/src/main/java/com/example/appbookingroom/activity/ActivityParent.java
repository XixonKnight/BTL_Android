package com.example.appbookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.appbookingroom.R;
import com.example.appbookingroom.databinding.ActivityParentBinding;

public class ActivityParent extends AppCompatActivity {


    private ActivityParentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_parent);
        init();
    }


    private void init() {
        binding = ActivityParentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FragmentHome());
        binding.tabLayout.setOnItemReselectedListener(v -> {
                    switch (v.getItemId()) {
                        case R.id.home:
                            replaceFragment(new FragmentHome());
                            break;
                        case R.id.settings:
                            replaceFragment(new FragmentSearch());
                            break;
                        case R.id.profile:
                            replaceFragment(new Fragment3());
                            break;
                    }
//                    return true;
                });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}