package com.example.demo.config;

//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.ObjectMessage;
//import javax.jms.Queue;
//import javax.jms.Session;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
//import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.core.JmsTemplate;
//
//import com.example.demo.dto.Pojo;

//@Configuration
public class ActiveMQConfiguration
{
//
//	@Value("${spring.activemq.broker-url}")
//	String	brokerUrl;
//
//	@Value("${spring.activemq.user}")
//	String	userName;
//
//	@Value("${spring.activemq.password}")
//	String	password;
//
//	@Value("${spring.activemq.queue}")
//	String	queue;
//
//	@Bean
//	public Queue queue()
//	{
//		return new ActiveMQQueue(queue);
//	}
//
//	@Bean
//	public ConnectionFactory connectionFactory()
//	{
//		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//		connectionFactory.setBrokerURL(brokerUrl);
//		connectionFactory.setUserName(userName);
//		connectionFactory.setPassword(password);
//		return connectionFactory;
//	}
//
//	@Bean
//	public JmsTemplate jmsTemplate()
//	{
//		JmsTemplate template = new JmsTemplate();
//		template.setConnectionFactory(connectionFactory());
//		template.setPubSubDomain(true);
//		return template;
//	}
}
