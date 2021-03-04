package com.mycompany.core.route;

import java.util.Random;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mycompany.core.config.MessagingProperties;

@Component
public class CoreRoute extends RouteBuilder {

  @Autowired
  private MessagingProperties properties;
  
  @Value("${mycompany.message.size}")
  private int size;
  
  @Override
  public void configure() throws Exception {

    // Run a timer and send a queue message
    from("timer://queueTimer?repeatCount=1")
      .setHeader("Sender", constant("Sender"))
      .setHeader("Receiver", constant("Receiver"))
      .setHeader("Service", constant("Test"))
      .setHeader("ServiceCapability", constant("Test"))
      .setHeader("ServiceVersion", constant("1.0"))
      .setHeader("MessageIdentifier", constant("Queue-1.2.3.4"))
      .setHeader("DestinationType", constant("Queue"))
      .setBody(constant(getUnicodeBody(size)))
      .to("coreProducer:queue:" + properties.getProducerOutQueue())
      .log("Message sent successfully with message identifier: ${in.header.MessageIdentifier}");

    // Consumer to receive queue message
    from("coreConsumer:queue:" + properties.getConsumerInQueue())
      .log("Received a message with message identifier: ${in.header.MessageIdentifier}")
      .log("Message body is ");
    
  }
  
  private String getBody(int length) {
	  System.out.println("length"+length);
	  int leftLimit = 32; 
	    int rightLimit = 1024; 
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	   
  }
  
  private String getUnicodeBody(int length) {
	  StringBuilder unicodeStringBuilder = new StringBuilder();
      for (char c = 1000; c < 11000; c++) {
         unicodeStringBuilder.append(c);
      }
      System.out.println("unicode string "+unicodeStringBuilder);
      String unicodeString = unicodeStringBuilder.toString();

      StringBuilder builder = new StringBuilder();
      while (builder.length() < length) {
         builder.append("hello " + unicodeString);
      }
	  return builder.toString();  
  }
}
