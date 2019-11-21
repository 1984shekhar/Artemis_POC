package com.mycompany;


import java.util.Hashtable;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSPasswordCredential;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class GroupRebalancer {
	
	
	public final static String QUEUE="Q123";
	private QueueConnectionFactory qconFactory;
	
	private JMSContext jmscontext = null;
	public final static String JNDI_FACTORY="org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory";
	public final static String JMS_FACTORY="ConnectionFactory";
	public void init(Context ctx, String queueName)throws NamingException, JMSException
	{
	qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
	jmscontext = qconFactory.createContext("admin", "admin", Session.SESSION_TRANSACTED);
	
	}
	public static void main(String[] args) throws Exception{
		
		if (args.length != 1) {
			System.out.println("Usage: java QueueSend URL");
			return;
			}
			InitialContext ic = getInitialContext(args[0]);
			GroupRebalancer groupRebalancer = new GroupRebalancer();
			groupRebalancer.init(ic, QUEUE);
		
	     // server.createQueue(SimpleString.toSimpleString(testQueueName), RoutingType.ANYCAST, SimpleString.toSimpleString(testQueueName), null, null, true, false, false, false, false, -1, false, false, true, -1, false, null, false, 0, 0, false, 0, 0, true);
	      JMSContext context = groupRebalancer.jmscontext;

	      Queue testQueue = 	context.createQueue(QUEUE);
    
	      final String groupID1 = "groupA";
	      final String groupID2 = "groupB";
	      final String groupID3 = "groupC";


	      JMSProducer producer1 = 	context.createProducer().setProperty("JMSXGroupID", groupID1);
	      JMSProducer producer2 = 	context.createProducer().setProperty("JMSXGroupID", groupID2);
	      JMSProducer producer3 = 	context.createProducer().setProperty("JMSXGroupID", groupID3);
/*
	      JMSConsumer consumer1 = 	context.createConsumer(testQueue);
	      JMSConsumer consumer2 = 	context.createConsumer(testQueue);
*/

	     context.start();
	  	System.out.println("---------Start: Set-1-------------");
	     System.out.println("1. Producer 1");
	      for (int j = 0; j < 10; j++) {
	    	  groupRebalancer.send(	context, testQueue, groupID1, producer1, j);
	      }
	      System.out.println("1. Producer 2");
	      for (int j = 10; j < 20; j++) {
	    	  groupRebalancer.send(	context, testQueue, groupID2, producer2, j);
	      }
	      System.out.println("1. Producer 3");
	      for (int j = 20; j < 30; j++) {
	    	  groupRebalancer.send(	context, testQueue, groupID3, producer3, j);
	      }

	  	context.commit();

	  	
	      //First set of msgs should go to the first consumer only
/*	  	System.out.println("1st set- Consumer 1 will receive ");
	      for (int j = 0; j < 10; j++) {
	         TextMessage tm = (TextMessage) consumer1.receive(20000);
	         tm.acknowledge();
	         //System.out.println("1. Message" + j +" "+ tm.getText());
	         //System.out.println(tm.getStringProperty("JMSXGroupID")+" "+ groupID1);
	      }

	      //Second set of msgs should go to the second consumers only
	      System.out.println("2nd set- Consumer 2 will receive");
	      for (int j = 10; j < 20; j++) {
	         TextMessage tm = (TextMessage) consumer2.receive(20000);

	         tm.acknowledge();

	         //System.out.println("2. Message" + j +" "+ tm.getText());

	         //System.out.println(tm.getStringProperty("JMSXGroupID")+" "+ groupID2);
	      }

	      //Third set of msgs should go to the first consumer only
	      System.out.println("3rd set- Consumer 1 will receive");
	      for (int j = 20; j < 30; j++) {
	         TextMessage tm = (TextMessage) consumer1.receive(20000);


	         tm.acknowledge();

	         //System.out.println("3. Message" + j +" "+ tm.getText());

	         //System.out.println(tm.getStringProperty("JMSXGroupID")+" "+ groupID3);
	      }
	      context.commit();*/
	  	System.out.println("Sleeping for 20 Sec");
	  	Thread.sleep(30000);
	  	System.out.println("---------End: Set-1-------------");
	      //Add new consumer, that should cause rebalance
	    //  JMSConsumer consumer3 = 	context.createConsumer(testQueue);
	      System.out.println("---------Start: Set-2-------------");
	      
	      System.out.println("2. Producer 1");
	      for (int j = 0; j < 10; j++) {
	    	  groupRebalancer.send(	context, testQueue, groupID1, producer1, j);
	      }
	      System.out.println("2. Producer 2");
	      for (int j = 10; j < 20; j++) {
	    	  groupRebalancer.send(	context, testQueue, groupID2, producer2, j);
	      }
	      System.out.println("2. Producer 3");
	      for (int j = 20; j < 30; j++) {
	    	  groupRebalancer.send(	context, testQueue, groupID3, producer3, j);
	      }
	  	context.commit();

/*	      //First set of msgs should go to the first consumer only
	  	System.out.println("1st set- Consumer 1 will receive ");
	      for (int j = 0; j < 10; j++) {
	         TextMessage tm = (TextMessage) consumer1.receive(20000);
	         tm.acknowledge();
	         //System.out.println("1. Message" + j +" "+ tm.getText());
	         //System.out.println(tm.getStringProperty("JMSXGroupID")+" "+ groupID1);
	      }

	      //Second set of msgs should go to the second consumers only
	      System.out.println("2nd set- Consumer 2 will receive");
	      for (int j = 10; j < 20; j++) {
	         TextMessage tm = (TextMessage) consumer2.receive(20000);

	         tm.acknowledge();

	         //System.out.println("2. Message" + j +" "+ tm.getText());

	         //System.out.println(tm.getStringProperty("JMSXGroupID")+" "+ groupID2);
	      }

	      //Third set of msgs should now go to the third consumer now
	      System.out.println("3rd set- Consumer 3 will receive");
	      for (int j = 20; j < 30; j++) {
	         TextMessage tm = (TextMessage) consumer3.receive(20000);


	         tm.acknowledge();

	         //System.out.println("3. Message" + j +" "+ tm.getText());

	         //System.out.println(tm.getStringProperty("JMSXGroupID")+" "+ groupID3);
	      }

	  	context.commit();*/
	  	 System.out.println("---------End: Set-2-------------");
		context.close();
	   
	}
	
	   private void send(JMSContext ctx, Queue testQueue, String groupID1, JMSProducer producer1, int j) throws JMSException {
		      TextMessage message = ctx.createTextMessage("Message" + j);
		      producer1.send(testQueue, message);
		      String prop = message.getStringProperty("JMSXGroupID");
		      //System.out.println("prop " + prop);
		      //System.out.println("groupID1"+" " +prop);
		   }
	   private static InitialContext getInitialContext(String url) throws NamingException
	   {
	   Hashtable env = new Hashtable();
	   env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
	   env.put(Context.PROVIDER_URL, url);
	   return new InitialContext(env);
	   }

}
