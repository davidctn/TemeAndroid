package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fragments=new ArrayList<>();
        fragments.add(new F1A2());
        fragments.add(new F2A2());
        fragments.add(new F3A2());

        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragments.get(0));
        fragmentTransaction.commit();
    }

    public void addF2A2(View view)
    {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragments.get(1));
        fragmentTransaction.commit();
    }
    public void replaceF2A2WithF3A2(View view)
    {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragments.get(2));
        fragmentTransaction.commit();
    }
    public void backToF1A2(View view)
    {
        findViewById(R.id.textView).setVisibility(View.VISIBLE);

        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragments.get(0));
        fragmentTransaction.commit();
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        finishAffinity();
    }
    public void closeActivity(View view)
    {
        finish();
    }
}