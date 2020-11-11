package com.mycompany.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.mycompany.amqp.config.MessagingProperties;

@SpringBootApplication
//@EnableConfigurationProperties({ MessagingProperties.class, JSSEProperties.class })
@EnableConfigurationProperties({ MessagingProperties.class})
public class AmqpMicroservice {

  /**
   * A main method to start this application.
   */
  public static void main(String[] args) {
    SpringApplication.run(AmqpMicroservice.class, args);
  }
}
