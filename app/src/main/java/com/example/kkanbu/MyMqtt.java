package com.example.kkanbu;

import android.content.Context;
import android.os.Build;
import android.util.Log;


import androidx.annotation.RequiresApi;

import com.hivemq.client.internal.mqtt.codec.decoder.MqttMessageDecoder;
import com.hivemq.client.internal.mqtt.codec.encoder.mqtt3.Mqtt3ClientMessageEncoders;
import com.hivemq.client.internal.mqtt.message.MqttMessage;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;
import com.hivemq.client.mqtt.mqtt3.Mqtt3ClientConfig;
import com.hivemq.client.mqtt.mqtt3.Mqtt3RxClient;
import com.hivemq.client.mqtt.mqtt3.message.Mqtt3Message;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.hivemq.client.mqtt.mqtt3.message.subscribe.Mqtt3Subscribe;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PayloadFormatIndicator;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import okio.Utf8;

public class MyMqtt {

    private Mqtt3Client client;

    public MyMqtt(String serverURI){

        client= MqttClient.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost(serverURI)
                .serverPort(1883)
                .useMqttVersion3()
                .buildAsync();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            client.toAsync().connect().whenComplete((mqtt3ConnAck, throwable) -> {
                if (throwable == null) {
                    Log.d("mqtt", "connect");
                    subscribe();
                } else {
                    Log.e("mqtterror", throwable.toString());
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void subscribe(){
        Mqtt3Subscribe subscribeMessage = Mqtt3Subscribe.builder()
                .topicFilter("Sensor/Humancount")
                .qos(MqttQos.EXACTLY_ONCE)
                .build();

        client.toAsync().subscribe(subscribeMessage,System.out::println);
        client.toAsync().subscribeWith()
                .topicFilter("Sensor/Humancount")
                .callback(publish -> {
                    byte[] payload = publish.getPayloadAsBytes();
                    String s = new String(payload, StandardCharsets.UTF_8);
                    Log.d("mqttpayload", s);
                })
                .send()
                .whenComplete((subAck, throwable) -> {
                    if (throwable != null) {
                        // Handle failure to subscribe
                    } else {
                        Log.d("mqttcomplete",subAck.toString());
                    }
                });








    }

    public void publish(String a){
        Mqtt3Publish publish = Mqtt3Publish.builder()
                .topic("Sensor/Humancount")
                .qos(MqttQos.EXACTLY_ONCE)
                .payload(a.getBytes(StandardCharsets.UTF_8))
                .build();

        client.toAsync().publish(publish);
    }
}