package com.mycompany.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback{

    private String name = "";

    public SimpleMqttCallBack(String tName) {

        this.name = tName;
    }

    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub
        System.out.println("Connection to MQTT broker lost!");

    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(name +" Message received:\n\t"+ new String(message.getPayload()) );

    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }

}
