package com.example.kkanbu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kkanbu.pojo.Login;
import com.example.kkanbu.pojo.LoginRegistration;
import com.example.kkanbu.retrofit.BaseEndPoint;
import com.example.kkanbu.retrofit.LoginEndPoint;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    
    EditText login_mail, login_psw;
    Button btn_login;

    View.OnClickListener cl;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_login);
        
        login_mail = (EditText)findViewById(R.id.login_email);
        login_psw = (EditText) findViewById(R.id.login_password);
        btn_login = (Button) findViewById(R.id.login_button);
        
        
        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.login_button:
                        boolean validation = checkValidation();
                        if(validation){
                            Login login = new Login();
                            login.setEmail(login_mail.toString());
                            login.setPassword(login_psw.toString());
                            LoginEndPoint loginEndPoint = BaseEndPoint.retrofit.create(LoginEndPoint.class);
                            Call<Map> checkMapCall = loginEndPoint.checkUserNameAndPassword(login);
                            SweetAlertDialog pDialog = new SweetAlertDialog(login.this,SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Loading....");
                            pDialog.setCancelable(false);
                            pDialog.show();
                            checkMapCall.enqueue(new Callback<Map>() {
                                @Override
                                public void onResponse(Call<Map> call, Response<Map> response) {
                                    pDialog.hide();
                                    new SweetAlertDialog(login.this)
                                            .setTitleText(response.body().get("message").toString())
                                            .show();
                                    Intent intent = new Intent(login.this, MainActivity2.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<Map> call, Throwable t) {
                                    new SweetAlertDialog(login.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Oops..")
                                            .setContentText(t.getMessage())
                                            .show();

                                }
                            });
                        }
                        

                }
                
            }
        };
        
        btn_login.setOnClickListener(cl);
    }

    public void clicklogin(){

    }

    private boolean checkValidation() {
        boolean validation = true;
        if(login_mail.toString().trim().length()<1){
            Toast.makeText(this, "이메일 입력하세요", Toast.LENGTH_SHORT).show();
            validation = false;
        }
        if(login_psw.toString().trim().length()<1){
            Toast.makeText(this, "비밀번호 입력하세요", Toast.LENGTH_SHORT).show();
            validation = false;
        }
        return validation;
    }
}
