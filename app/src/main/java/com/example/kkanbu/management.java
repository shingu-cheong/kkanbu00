package com.example.kkanbu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class management extends Fragment {

    RecyclerView kkanbulist;
    FloatingActionButton addman;

    Adapter adapter;
    View.OnClickListener cl;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_management);
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_management,container,false);

        kkanbulist = view.findViewById(R.id.kkanbulist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        kkanbulist.setLayoutManager(layoutManager);
        addman = view.findViewById(R.id.btn_addman);

        adapter = new Adapter(getContext());
        kkanbulist.setAdapter(adapter);

        adapter.additem(new item(R.drawable.live, "이현빈", "남", "010-7713-8568"));

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_addman:
                        Intent intent = new Intent(getContext(), AddOlderMan.class);
                        startActivity(intent);
                }

            }
        };

        addman.setOnClickListener(cl);

        return view;
    }

}