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
public class CoreProducerBeanConfiguration {

  @Autowired
  private MessagingProperties properties;

  @Bean(name = "coreProducer")
  public JmsComponent createComponent(@Qualifier("jmsProducerConfiguration") final JmsConfiguration jmsProducerConfiguration) {
    JmsComponent jmsProducerComponent = new JmsComponent();
    jmsProducerComponent.setConfiguration(jmsProducerConfiguration);
    return jmsProducerComponent;
  }

  @Bean(name = "jmsProducerConfiguration")
  public JmsConfiguration createConfiguration(@Qualifier("jmsProducerConnectionFactory") final ActiveMQJMSConnectionFactory jmsProducerConnectionFactory) {
    JmsConfiguration jmsProducerConfiguration = new JmsConfiguration();
    jmsProducerConfiguration.setConnectionFactory(jmsProducerConnectionFactory);
    return jmsProducerConfiguration;
  } 
  
  @Bean(name = "jmsProducerConnectionFactory")
  public ActiveMQJMSConnectionFactory connectionFactory() throws JMSException {
    ActiveMQJMSConnectionFactory jmsProducerConnectionFactory = new ActiveMQJMSConnectionFactory();
    jmsProducerConnectionFactory.setBrokerURL(properties.getProducerUrl());
    return jmsProducerConnectionFactory;
  }   
}
