package com.mycompany.mqtt.client;

public class ThreadFactory {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1; i++) {
            System.out.println("start publish");
            new Thread(new MqttPublishSample(), " Thread_" + i).start();
        }

        Thread.sleep(1000);

        for (int i=1;i<=2;i++) {
            System.out.println("start subscribe");
            new Thread(new MqttSubscribeSample(), "Thread_" + i).start();
        }
    }

}
