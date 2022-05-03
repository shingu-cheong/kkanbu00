package com.example.kkanbu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class management extends AppCompatActivity {

    RecyclerView kkanbulist;

    Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.);

        kkanbulist = findViewById(R.id.kkanbulist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        kkanbulist.setLayoutManager(layoutManager);

        adapter = new Adapter(getApplicationContext());
        kkanbulist.setAdapter(adapter);

        adapter.additem(new item(R.drawable.live, "이현빈", "남", "010-7713-8568"));
    }
}