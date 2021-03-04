package com.mycompany.core.config;

import javax.jms.JMSException;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConsumerBeanConfiguration {

  @Autowired
  private MessagingProperties properties;

  @Bean(name = "coreConsumer")
  public JmsComponent createComponent(@Qualifier("jmsConsumerConfiguration") final JmsConfiguration jmsConsumerConfiguration) {
    JmsComponent jmsConsumerComponent = new JmsComponent();
    jmsConsumerComponent.setConfiguration(jmsConsumerConfiguration);
    return jmsConsumerComponent;
  }

  @Bean(name = "jmsConsumerConfiguration")
  public JmsConfiguration createConfiguration(@Qualifier("jmsConsumerConnectionFactory") final ActiveMQJMSConnectionFactory jmsConsumerConnectionFactory) {
    JmsConfiguration jmsConsumerConfiguration = new JmsConfiguration();
    jmsConsumerConfiguration.setConnectionFactory(jmsConsumerConnectionFactory);
    jmsConsumerConfiguration.setConcurrentConsumers(properties.getConcurrentConsumers());
    jmsConsumerConfiguration.setAsyncConsumer(properties.getAsyncConsumer());
    return jmsConsumerConfiguration;
  }

  @Bean(name = "jmsConsumerConnectionFactory")
  public ActiveMQJMSConnectionFactory connectionFactory() throws JMSException {
    ActiveMQJMSConnectionFactory jmsConsumerConnectionFactory = new ActiveMQJMSConnectionFactory();
    jmsConsumerConnectionFactory.setBrokerURL(properties.getConsumerUrl());
    return jmsConsumerConnectionFactory;
  }
}
