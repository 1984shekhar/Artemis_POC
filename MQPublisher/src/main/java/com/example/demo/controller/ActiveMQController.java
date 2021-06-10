package com.example.demo.controller;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;*/
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Pojo;

@RestController
@RequestMapping("/MQ")
public class ActiveMQController {

	@Value("${activemq.broker-url}")
	String brokerUrl;

	@Value("${activemq.user}")
	String userName;

	@Value("${activemq.password}")
	String password;

	@Value("${activemq.queue}")
	String queueName;

	String INTIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
	QueueSession qsession = null;
	QueueSender qsender = null;
	QueueConnectionFactory factory = null;
	QueueConnection qcon = null;
	@Value("${activemq.factory}")
	String connection_factory;
	// @Autowired
	// private JmsMessagingTemplate jmsMessagingTemplate;

	// @Autowired
	// private Queue queue;

	Context namingContext = null;

	@GetMapping
	public String postMessage() throws JMSException, NamingException {
		try {
			Pojo pojo = new Pojo("Name", "Prashant");
			System.out.println(pojo + " " + userName + " " + password);

			// ConnectionFactory factory = new
			// ActiveMQJMSConnectionFactory(brokerUrl,userName,password);
			factory = getConnectionFactory();
			// Destination destination = ActiveMQDestination.fromPrefixedName(queue);
			Queue queueDes = (Queue) namingContext.lookup(queueName);
			qcon = factory.createQueueConnection(userName, password);

			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			qsender = qsession.createSender(queueDes);
			ObjectMessage message = qsession.createObjectMessage();

			message.setObject(pojo);
			qcon.start();
			qsender.send(message);
			return "successfully published";
		} finally {
			close();
		}

	}

	private QueueConnectionFactory getConnectionFactory() throws NamingException {

		final Properties env = new Properties();

		env.put(Context.INITIAL_CONTEXT_FACTORY, INTIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, brokerUrl);
		// username
		env.put(Context.SECURITY_PRINCIPAL, userName);
		// password
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			namingContext = new InitialContext(env);

		} catch (NamingException e) {

			e.printStackTrace();
		}

		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) namingContext.lookup(connection_factory);
		return connectionFactory;

	}

	private void close() throws JMSException {
		if (qsender != null)
			qsender.close();
		if (qsession != null)
			qsession.close();
		if (qcon != null)
			qcon.close();
	}
}
