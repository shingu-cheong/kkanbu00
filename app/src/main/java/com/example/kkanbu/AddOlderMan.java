package com.example.kkanbu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kkanbu.pojo.Elder;
import com.example.kkanbu.pojo.Olderman;
import com.example.kkanbu.pojo.User;
import com.example.kkanbu.retrofit.BaseEndPoint;
import com.example.kkanbu.retrofit.ElderEndPoint;
import com.example.kkanbu.retrofit.OldermanEndPoint;
import com.example.kkanbu.retrofit.UserEndPoint;
import com.example.kkanbu.utils.ProjectConstants;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Path;


public class AddOlderMan extends AppCompatActivity {
    Toolbar tb_addman;
    TextInputLayout et_manName, et_manPh, et_manAdr, et_managerPh, et_detail;
    SharedPreferences shared ;
    Button bt_addman, button1;
    Integer uid ;
    View.OnClickListener cl;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview2;

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


        shared= getSharedPreferences(ProjectConstants.PREF_NAME, MODE_PRIVATE);
        Log.e("User", String.valueOf(shared.getInt(ProjectConstants.USER_NUM,0)));
        uid = shared.getInt(ProjectConstants.USER_NUM,0);


//        Call<User> finduser = userEndPoint.getSingleUser(uid);
//        finduser.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                user = response.body();
//                Log.e("finduser", user.toString());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
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

//        bt_addman.setOnClickListener(cl);
//
//        imageview2 = (ImageView)findViewById(R.id.imageView);
//        imageview2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
//            }
//        });
        Uri uri;
        ImageView imageView2;

        Button selectImageBtn = findViewById(R.id.button1);
        imageView2 = findViewById(R.id.imageView2);

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                private ImageView imageView2;

                @Override
                public void onActivityResult(ActivityResult result) {
                    if( result.getResultCode() == RESULT_OK && result.getData() != null){

                        Uri uri = result.getData().getData();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageView2.setImageBitmap(bitmap);

                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri selectedImageUri = data.getData();
//            imageview2.setImageURI(selectedImageUri);
//
//        }
//
//    }


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
        Elder elder = setElderData();
        ElderEndPoint elderEndPoint = BaseEndPoint.retrofit.create(ElderEndPoint.class);

        Call<String> addNewMan = elderEndPoint.saveElder(uid,elder);


        SweetAlertDialog pDialog = new SweetAlertDialog(AddOlderMan.this,SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading....");
        pDialog.setCancelable(true);
        pDialog.show();

        addNewMan.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pDialog.dismiss();

                SweetAlertDialog message = new SweetAlertDialog(AddOlderMan.this, SweetAlertDialog.SUCCESS_TYPE);
                message.setTitleText(response.body()).show();
                message.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                });

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }



    //데이터 삽입
    private Elder setElderData() {

        Elder elder = new Elder();

        elder.setElderAdr(et_manAdr.getEditText().getText().toString());
        elder.setElderImg(null);
        elder.setElderName(et_manName.getEditText().getText().toString());
        elder.setElderPh(et_manPh.getEditText().getText().toString());
        elder.setMngPh(et_managerPh.getEditText().getText().toString());

        return elder;
    }

}