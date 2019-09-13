package com.mycompany.mqtt.client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class ThreadFactory {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1; i++) {
            System.out.println("start publish");
            new Thread(new MqttPublishSample(), " Thread_" + i).start();
        }

        Thread.sleep(1000);

        for (int i=1;i<=1;i++) {
            System.out.println("start subscribe");
            new Thread(new MqttSubscribeSample(), "Thread_" + i).start();
        }
    }
    public static SocketFactory getTruststoreFactory() throws Exception {

        KeyStore trustStore = KeyStore.getInstance("JKS");
        InputStream in = new FileInputStream("/home/cpandey/client.ts");
        trustStore.load(in, "password".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        SSLContext sslCtx = SSLContext.getInstance("TLSv1.2");
        sslCtx.init(null, tmf.getTrustManagers(), null);
        return sslCtx.getSocketFactory();
    }

}
