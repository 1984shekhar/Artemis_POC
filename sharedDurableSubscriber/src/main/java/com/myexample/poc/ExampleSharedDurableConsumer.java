package com.myexample.poc;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ExampleSharedDurableConsumer {

	public static void main(String[] args) throws JMSException, InterruptedException {
		// TODO Auto-generated method stub
		Connection connection =null;
	         try {
	         //Topic topic = ActiveMQJMSClient.createTopic("Test.Topic");

	         ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	         connection = connectionFactory.createConnection("admin", "admin");
	         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	         Topic topic = session.createTopic("Test.Topic");
	         connection.start();
	         String selector="country='IN'";
	         MessageConsumer consumerTest = session.createSharedDurableConsumer(topic, "Amqp.Test.Subscription", selector);
	         MessageProducer producerTest = session.createProducer(topic);

	         Thread.sleep(5000);

	         final int numMessages = 5;

	         for (int i = 0; i < numMessages; i++) {
	            TextMessage message = session.createTextMessage("Sending Text, message " + i);
	            message.setStringProperty("country", "IN");
	            producerTest.send(message);

	            System.out.println("Sent message: " + message.getText());
	         }

	         //Thread.sleep(60000);

	         for (int i = 0; i < numMessages; i++) {
	            TextMessage message = (TextMessage) consumerTest.receive(5000);

	            System.out.println("Got message: " + message.getText());


	         }
	         }finally {
	        	 
	        	 if (connection != null) {
	        		 connection.stop();
	        		 connection.close();
	              }
	         }
	}

}
