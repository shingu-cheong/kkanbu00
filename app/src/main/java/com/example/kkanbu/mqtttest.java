package com.example.kkanbu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;







public class mqtttest extends AppCompatActivity {

    private String topic, clientID;
    //    private MqttAndroidClient client;
    private MyMqtt myMqtt;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtttest);
        button = findViewById(R.id.mqttsub);
        myMqtt = new MyMqtt("152.67.192.198");


    }





}