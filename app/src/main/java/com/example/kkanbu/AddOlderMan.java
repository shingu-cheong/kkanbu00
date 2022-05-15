package com.example.kkanbu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kkanbu.pojo.Olderman;
import com.example.kkanbu.retrofit.BaseEndPoint;
import com.example.kkanbu.retrofit.OldermanEndPoint;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddOlderMan extends AppCompatActivity {
    Toolbar tb_addman;
    TextInputLayout et_manName, et_manPh, et_manAdr, et_managerPh, et_detail;
    Button bt_addman;

    View.OnClickListener cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_older_man);

        tb_addman = (Toolbar) findViewById(R.id.mt_toolbar);
        et_manName = findViewById(R.id.et_manName);
        et_manPh = findViewById(R.id.et_manPh);
        et_manAdr = findViewById(R.id.et_manAdress);
        et_managerPh = findViewById(R.id.et_managerPh);
        et_detail = findViewById(R.id.et_manDetail);
        bt_addman = findViewById(R.id.addman);
        setSupportActionBar(tb_addman);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);



        }

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.addman:
                        addman();

                }

            }
        };

        bt_addman.setOnClickListener(cl);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void addman(){
        Olderman olderman = setOldermanData();
        OldermanEndPoint oldermanEndPoint = BaseEndPoint.retrofit.create(OldermanEndPoint.class);
        Call<Map> addNewMan = oldermanEndPoint.addOlderman(olderman);
        SweetAlertDialog pDialog = new SweetAlertDialog(AddOlderMan.this,SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading....");
        pDialog.setCancelable(true);
        pDialog.show();
        addNewMan.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                pDialog.hide();
                new SweetAlertDialog(AddOlderMan.this)
                        .setTitleText(response.body().get("message").toString())
                        .show();
                Intent intent = new Intent(AddOlderMan.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops..")
                        .setContentText(t.getMessage())
                        .show();
            }
        });

    }

    //데이터 삽입
    private Olderman setOldermanData() {

        Olderman olderman = new Olderman();
        olderman.setManPh(et_manPh.getEditText().getText().toString());
        olderman.setManName(et_manName.getEditText().getText().toString());
        olderman.setManGender(null);
        olderman.setMangerGroupId(0);
        olderman.setManManager(null);
        olderman.setManAdr(et_manAdr.getEditText().getText().toString());
        olderman.setManManager(et_managerPh.getEditText().getText().toString());
        olderman.setManIllness(et_detail.getEditText().getText().toString());
        return olderman;
    }

}