package com.mycompany.amqp.config;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpProducerBeanConfiguration {

  @Autowired
  private MessagingProperties properties;

  @Bean(name = "amqpProducer")
  public AMQPComponent createComponent(@Qualifier("amqpProducerConfiguration") final JmsConfiguration amqpProducerConfiguration) throws Exception {
    AMQPComponent amqpProducerComponent = new AMQPComponent();
    amqpProducerComponent.setConfiguration(amqpProducerConfiguration);
    return amqpProducerComponent;
  }

  @Bean(name = "amqpProducerConfiguration")
  public JmsConfiguration createConfiguration(@Qualifier("amqpProducerConnectionFactory") final JmsConnectionFactory amqpProducerConnectionFactory) throws Exception {
    JmsConfiguration amqpProducerConfiguration = new JmsConfiguration();
    amqpProducerConfiguration.setConnectionFactory(amqpProducerConnectionFactory);
    return amqpProducerConfiguration;
  } 
  
  @Bean(name = "amqpProducerConnectionFactory")
  public JmsConnectionFactory connectionFactory() throws Exception {
    JmsConnectionFactory amqpProducerConnectionFactory = new JmsConnectionFactory();
    amqpProducerConnectionFactory.setRemoteURI(properties.getProducerUrl());

    return amqpProducerConnectionFactory;
  }   
}
