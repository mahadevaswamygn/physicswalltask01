package com.example.physicswallahtaskone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.physicswallahtaskone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());
        Fragment fragment = new ListviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment,fragment.getClass().getName()).commit();
    }


}