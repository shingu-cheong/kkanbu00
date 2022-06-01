package com.example.kkanbu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class mqtttest extends AppCompatActivity {

    private String topic, clientID;
    private MqttAndroidClient client;
    private MyMqtt myMqtt;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtttest);
        button = findViewById(R.id.mqttsub);
//        myMqtt = new MyMqtt(this, "tcp://152.")
        init();

    }

    private void init(){


        clientID = "kkanbu";
        topic = "Sensor/Humancount";
        client =
                new MqttAndroidClient(this
                                , "tcp://152.67.192.198:1883"
                                ,clientID);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });

    }

    private void connect(){
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    sub();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void sub(){
        try {
            client.subscribe(topic,0);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {

                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.e("inout", new String(message.getPayload()));

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });

        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }

}