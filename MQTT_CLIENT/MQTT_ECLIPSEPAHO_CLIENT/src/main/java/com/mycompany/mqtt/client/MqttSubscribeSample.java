package com.mycompany.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscribeSample implements Runnable {

    public void run() {

        MqttClient client;
        try {
            String suf = String.valueOf(System.currentTimeMillis());
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
            MqttConnectOptions opt = new MqttConnectOptions();
            opt.setUserName("admin");
            opt.setPassword("admin".toCharArray());
            opt.setCleanSession(false);
            client.setCallback(new SimpleMqttCallBack(Thread.currentThread().getName()));
            client.connect(opt);
            client.subscribe("MQTT_paho");
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
