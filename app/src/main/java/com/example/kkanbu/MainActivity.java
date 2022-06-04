package com.example.kkanbu;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.kkanbu.thread.GetHuman;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends Fragment {
    private ImageButton btn_live, btn_schedule;
    private ImageView profile, present;
    private GetHuman getHuman;
    private Object humancount, humancountpre;
    private View.OnClickListener cl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHuman = new GetHuman();
        getHuman.start();
        while (true){
            humancount = getHuman.getSomethingResult();
            if(humancountpre != humancount){
                humancountpre =humancount;
                if(humancount!= null){
                    Log.e("efsd", String.valueOf(humancount));
                }
            }


        }



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        btn_live = view.findViewById(R.id.btn_live);
        btn_schedule = view.findViewById(R.id.btn_schedule);
        profile = view.findViewById(R.id.home_profile);
        present = view.findViewById(R.id.present);
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.home_profileround);
        profile.setBackground(drawable);
        profile.setClipToOutline(true);



        if (humancount == "0"){
            present.setImageResource(android.R.drawable.presence_invisible);
        }else {
            present.setImageResource(android.R.drawable.presence_online);
        }

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_live:
                        Intent a = new Intent(getActivity(), monitoring.class);
                        startActivity(a);
                        break;
                    case R.id.btn_schedule:
                        Intent b = new Intent(getActivity(), schedule.class);
                        startActivity(b);
                        break;
                }
            }
        };
        btn_live.setOnClickListener(cl);
        btn_schedule.setOnClickListener(cl);
        return view;
    }
}
