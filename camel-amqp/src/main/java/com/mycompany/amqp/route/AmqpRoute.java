package com.mycompany.amqp.route;


import java.util.Random;
import java.util.UUID;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mycompany.amqp.config.MessagingProperties;

@Component
public class AmqpRoute extends RouteBuilder {

  @Autowired
  private MessagingProperties properties;
  
  @Value("${mycompany.message.size}")
  private int size;
  
  @Override
  public void configure() throws Exception {     

    // Run a timer and send a queue message
    from("timer://queueTimer?period=60000")
      .setHeader("Sender", constant("Sender"))
     .setHeader("Receiver", constant("1000000"))
      //.setHeader("Receiver", constant("10000"))
      .setHeader("Service", constant("Test"))
      .setHeader("ServiceCapability", constant("Test"))
      .setHeader("ServiceVersion", constant("1.0"))
      .setHeader("MessageIdentifier", constant(UUID.randomUUID().toString()))
      .setHeader("DestinationType", constant("Queue"))
      .setBody(constant(getUnicodeBody(size)))
      .to("amqpProducer:queue:TEST.OUT")
      //.to("amqpProducer:queue:" + properties.getProducerOutQueue())
      .log("Message sent successfully with message identifier: ${in.header.MessageIdentifier}");

    // Consumer to receive queue message
    from("amqpConsumer:queue:TEST.OUT")
      .log("Received a message with message identifier: ${in.header.MessageIdentifier} ");
    
  }
  
  /**
   * Returns a string of specified length
   * @param length
   * @return
   */
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
	    String content = buffer.toString();
	   // System.out.println("content "+content);
		return content;
  }
}
