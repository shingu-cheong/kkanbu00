package com.example.kkanbu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kkanbu.pojo.LoginRegistration;
import com.example.kkanbu.pojo.Olderman;
import com.example.kkanbu.pojo.Registration;
import com.example.kkanbu.pojo.User;
import com.example.kkanbu.retrofit.BaseEndPoint;
import com.example.kkanbu.retrofit.RegistrationendPoint;
import com.example.kkanbu.retrofit.UserEndPoint;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout et_email, et_name, et_psw, et_psw2;
    Button bt_join;

    View.OnClickListener cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_password);
        et_psw2 = findViewById(R.id.et_passwordcheck);
        bt_join = findViewById(R.id.join_button);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.join_button:
                        boolean validation = checkValidation();
                        if(validation){
                            User user = setUserData();
                            UserEndPoint userEndPoint = BaseEndPoint.retrofit.create(UserEndPoint.class);
//                            LoginRegistration loginRegistration = setLoginRegistrationData();
//                            RegistrationendPoint registrationendPoint = BaseEndPoint.retrofit.create(RegistrationendPoint.class);
                            Call<User> addNewUser = userEndPoint.saveUser(user);
                            SweetAlertDialog pDialog = new SweetAlertDialog(RegisterActivity.this,SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Loading....");
                            pDialog.setCancelable(true);
                            pDialog.show();
                            addNewUser.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    pDialog.hide();
                                    new SweetAlertDialog(RegisterActivity.this)
                                            .setTitleText("등록완료")
                                            .show();
                                    Log.e("반응", response.body().toString());
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Oops..")
                                            .setContentText(t.getMessage())
                                            .show();
                                }
                            });
//                            addNewUser.enqueue(new Callback<Map>() {
//                                @Override
//                                public void onResponse(Call<Map> call, Response<Map> response) {
//                                    pDialog.hide();
//                                    new SweetAlertDialog(RegisterActivity.this)
//                                            .setTitleText(response.body().get("message").toString())
//                                            .show();
//                                    Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailure(Call<Map> call, Throwable t) {
//                                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                            .setTitleText("Oops..")
//                                            .setContentText(t.getMessage())
//                                            .show();
//                                }
//                            });
//                            addNewUser.enqueue(new Callback<Map>() {
//                                @Override
//                                public void onResponse(Call<Map> call, Response<Map> response) {
//                                    pDialog.hide();
//                                    new SweetAlertDialog(RegisterActivity.this)
//                                            .setTitleText(response.body().get("message").toString())
//                                            .show();
//                                    Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailure(Call<Map> call, Throwable t) {
//                                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                            .setTitleText("Oops..")
//                                            .setContentText(t.getMessage())
//                                            .show();
//                                }
//                            });
                        }

                }

            }
        };

        bt_join.setOnClickListener(cl);

    }

//    private LoginRegistration setLoginRegistrationData() {
//        LoginRegistration loginRegistration = new LoginRegistration();
//        loginRegistration.setAddress1(null);
//        loginRegistration.setCity(null);
//        loginRegistration.setDob(null);
//        loginRegistration.setFullname(null);
//        loginRegistration.setGender("Male");
//        loginRegistration.setPincode(null);
//        loginRegistration.setState(null);
//
//        loginRegistration.setEmail(et_email.getEditText().getText().toString());
//        loginRegistration.setMobile(null);
//        loginRegistration.setPassword(et_psw.getEditText().getText().toString());
//
//        loginRegistration.setUsername(et_name.getEditText().getText().toString());
//        return loginRegistration;
//    }

    private User setUserData() {

        User user = new User();
        user.setUserEmail(et_email.getEditText().getText().toString());
        Log.e("a", et_email.getEditText().getText().toString());
        user.setUserName(et_name.getEditText().getText().toString());
        user.setUserPassword(et_psw.getEditText().getText().toString());
        user.setUserImg(null);
        user.setUserPh(null);
        return user;
    }


    private boolean checkValidation() {
        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_password);
        et_psw2 = findViewById(R.id.et_passwordcheck);
        boolean validation = true;

        if(et_email.getEditText().getText().toString().trim().length()<1){
            et_email.getEditText().setError("이메일을 입력하지 않았습니다.");
            validation = false;
        }
        if(et_name.getEditText().getText().toString().trim().length()<1){
            et_name.getEditText().setError("닉네임을 입력하지 않았습니다");
            validation = false;
        }
        if(et_psw.getEditText().getText().toString().trim().length()<1){
            et_psw.getEditText().setError("비밀번호를 입력하지 않았습니다.");
            validation = false;
        }
        if(et_psw2.getEditText().getText().toString().trim().length()<1){
            et_psw2.getEditText().setError("비밀번호를 확인하지 않았습니다.");
            validation = false;
        }
        else if(!et_psw2.getEditText().getText().toString().equals(et_psw.getEditText().getText().toString())){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            validation = false;
        }
        return validation;
    }
}