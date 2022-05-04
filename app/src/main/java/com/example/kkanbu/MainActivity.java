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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Fragment {
    ImageButton btn_live;
    ImageView profile;
    View.OnClickListener cl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        btn_live = view.findViewById(R.id.btn_live);
        profile = view.findViewById(R.id.home_profile);
        GradientDrawable drawable = (GradientDrawable) getContext().getDrawable(R.drawable.home_profileround);
        profile.setBackground(drawable);
        profile.setClipToOutline(true);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_live:
                        Intent a = new Intent(getActivity(), monitoring.class);
                        startActivity(a);
                        break;
                }
            }
        };

        btn_live.setOnClickListener(cl);
        return view;
    }
}
