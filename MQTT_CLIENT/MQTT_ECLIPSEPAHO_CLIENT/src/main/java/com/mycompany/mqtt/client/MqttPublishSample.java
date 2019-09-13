package com.mycompany.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublishSample implements Runnable {

    public void run() {

        String tName = String.valueOf(Thread.currentThread().getName());
        try {
            String suf = String.valueOf(System.currentTimeMillis());
            String topic = "MQTT_paho";
            String content = "Message from MqttPublishSample";
            int qos = 1;
            String broker = "tcp://0.0.0.0:1883";
            String clientId = "JavaSample";
            //MemoryPersistence persistence = new MemoryPersistence();

            MqttClient sampleClient = null;
            MqttConnectOptions connOpts = new MqttConnectOptions();
            sampleClient = new MqttClient(broker, clientId);
            connOpts.setUserName("admin");
            connOpts.setPassword("admin".toCharArray());
            connOpts.setCleanSession(false);
            System.out.println(tName + " Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println(tName + " Connected");
            System.out.println(tName + " Publishing message: " + content);
            for (int i = 0; i < 10; i++) {
                MqttMessage message = new MqttMessage((content + i).getBytes());
                message.setQos(qos);
                sampleClient.publish(topic, message);
                Thread.sleep(1000);;
            }
             sampleClient.disconnect();
             System.out.println(tName + " Disconnected");
             System.exit(0);
        } catch (MqttException me) {
            System.out.println("Thread " + tName + " reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
