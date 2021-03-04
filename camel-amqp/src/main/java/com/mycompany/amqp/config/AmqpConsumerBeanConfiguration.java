package com.mycompany.amqp.config;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConsumerBeanConfiguration {

  @Autowired
  private MessagingProperties properties;

  @Bean(name = "amqpConsumer")
  public AMQPComponent createComponent(@Qualifier("amqpConsumerConfiguration") final JmsConfiguration amqpConsumerConfiguration) throws Exception {
    AMQPComponent amqpConsumerComponent = new AMQPComponent();
    amqpConsumerComponent.setConfiguration(amqpConsumerConfiguration);
    return amqpConsumerComponent;
  }

  @Bean(name = "amqpConsumerConfiguration")
  public JmsConfiguration createConfiguration(@Qualifier("amqpConsumerConnectionFactory") final JmsConnectionFactory amqpConsumerConnectionFactory)
    throws Exception {
    JmsConfiguration amqpConsumerConfiguration = new JmsConfiguration();
    amqpConsumerConfiguration.setConnectionFactory(amqpConsumerConnectionFactory);
    amqpConsumerConfiguration.setConcurrentConsumers(properties.getConcurrentConsumers());
    amqpConsumerConfiguration.setAsyncConsumer(properties.getAsyncConsumer());
    return amqpConsumerConfiguration;
  }

  @Bean(name = "amqpConsumerConnectionFactory")
  public JmsConnectionFactory connectionFactory() throws Exception {
    JmsConnectionFactory amqpConsumerConnectionFactory = new JmsConnectionFactory();
    amqpConsumerConnectionFactory.setRemoteURI(properties.getConsumerUrl());

    return amqpConsumerConnectionFactory;
  }
}
