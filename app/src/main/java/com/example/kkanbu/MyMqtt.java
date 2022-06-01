package com.example.kkanbu;

import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MyMqtt {
    private MqttAndroidClient androidClient;
    public MyMqtt(Context context, String serverURI, String clientId){
        androidClient = new MqttAndroidClient(context,serverURI,clientId);
        IMqttToken token = null;

        try {
            androidClient.connect(new MqttConnectOptions());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
