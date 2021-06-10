package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqPublisherApplication
{

	public static void main(String[] args)
	{
		// System.setProperty("javax.net.ssl.trustStore", "N:/SSLCertificate/server.jks");
		// System.setProperty("javax.net.ssl.trustStorePassword", "");
		SpringApplication.run(MqPublisherApplication.class, args);

	}

}
