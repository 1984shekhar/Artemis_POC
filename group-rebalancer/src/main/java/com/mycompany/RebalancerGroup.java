package com.mycompany;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class RebalancerGroup {

	// Queue to send the messages
	public final static String QUEUE = "Q123T_group_rebalance";

	
	// Connection attributes
	public final static String JNDI_FACTORY = "org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory";
	public final static String JMS_FACTORY = "ConnectionFactory";
	public final static String URL = "tcp://localhost:61616";
	
	// Number of groups used in the test.
	public final static int groups_size = 10000;
	
	// ExecutorService to run the test in parallel - it will send the message in parallel.
	public final static ExecutorService executor = Executors.newFixedThreadPool(20);
	

	public static void main(String[] args) throws Exception {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, URL);
		Context ctx = new InitialContext(env);
		
		ConnectionFactory qconFactory = (ConnectionFactory) ctx.lookup(JMS_FACTORY);

		List<Callable<Void>> sendMessageTasks = new ArrayList<>();	
		
		// Preparing the producers
		for (int i = 0; i < groups_size; i++) {
			
			final String groupID = "group_" + i;		
		
			
			Callable<Void> task = () -> {
				
				System.out.println("Start sending messages for group id: " + groupID);
				
				JMSContext jmscontext = qconFactory.createContext("admin", "admin", Session.SESSION_TRANSACTED);
				
				Queue testQueue = jmscontext.createQueue(QUEUE);
				
				JMSProducer producer  = jmscontext.createProducer().setProperty("JMSXGroupID", groupID);
				
				for (int j = 0; j < 5; j++) {
					
					TextMessage message = jmscontext.createTextMessage("Message " + j + " of group " + groupID );
					producer.send(testQueue, message);
				}
				
				jmscontext.commit();
				
				jmscontext.close();
				
				System.out.println("Sent messages for group id: " + groupID);
			
				
				return null;
			};
			
			sendMessageTasks.add(task);
		}
		
		System.out.println("Start the ExecutorService");

		// Send the messages in parallel. 
		executor.invokeAll(sendMessageTasks);
		
		executor.shutdown();
	}

}
