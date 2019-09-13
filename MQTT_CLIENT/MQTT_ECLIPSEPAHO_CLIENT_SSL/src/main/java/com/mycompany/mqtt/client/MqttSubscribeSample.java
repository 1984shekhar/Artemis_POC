package com.mycompany.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscribeSample implements Runnable {

    public void run() {

        MqttClient client;
        try {
            client = new MqttClient("ssl://broker-amq-mqtt-ssl-amq-demo.apps.csppnq.lab.pnq2.cee.redhat.com:443", MqttClient.generateClientId());
            MqttConnectOptions opt = new MqttConnectOptions();
            opt.setUserName("admin");
            opt.setPassword("admin".toCharArray());
            opt.setCleanSession(false);
            opt.setSocketFactory(ThreadFactory.getTruststoreFactory());
            client.setCallback(new SimpleMqttCallBack(Thread.currentThread().getName()));
            client.connect(opt);
            client.subscribe("MQTT_paho");
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
