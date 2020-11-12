package com.mycompany.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.mycompany.core.config.MessagingProperties;

@SpringBootApplication
@EnableConfigurationProperties({ MessagingProperties.class })
public class CoreMicroservice {

  /**
   * A main method to start this application.
   */
  public static void main(String[] args) {
    SpringApplication.run(CoreMicroservice.class, args);
  }
}
