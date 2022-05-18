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

import com.example.kkanbu.pojo.Elder;
import com.example.kkanbu.pojo.Olderman;
import com.example.kkanbu.retrofit.BaseEndPoint;
import com.example.kkanbu.retrofit.ElderEndPoint;
import com.example.kkanbu.retrofit.OldermanEndPoint;
import com.example.kkanbu.retrofit.RegistrationendPoint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class management extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton addman;
    List<Elder> elderList;

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

        recyclerView = view.findViewById(R.id.kkanbulist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        recyclerView.setLayoutManager(layoutManager);
        addman = view.findViewById(R.id.btn_addman);
        elderList = new ArrayList<>();



//        adapter.additem(new item(R.drawable.live, "이현빈", "남", "010-7713-8568"));
        ElderEndPoint elderEndPoint = BaseEndPoint.retrofit.create(ElderEndPoint.class);
        Elder elder = new Elder();
        Call<List<Elder>> call = elderEndPoint.getUserelder(1);
        call.enqueue(new Callback<List<Elder>>() {
            @Override
            public void onResponse(Call<List<Elder>> call, Response<List<Elder>> response) {


                elderList = (List<Elder>) response.body();
                adapter = new Adapter(getActivity(), elderList);

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Elder>> call, Throwable t) {

            }
        });
//        Call<Olderman> checkMapCall = oldermanEndPoint.getDataById(1);
//        checkMapCall.enqueue(new Callback<Olderman>() {
//            @Override
//            public void onResponse(Call<Olderman> call, Response<Olderman> response) {
//                oldermanList = (List<Olderman>) response.body();
//            }
//
//            @Override
//            public void onFailure(Call<Olderman> call, Throwable t) {
//
//            }
//        });
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