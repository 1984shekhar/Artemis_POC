package com.mypackage;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicReceiveEAP implements MessageListener {

	public final static String JNDI_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	// *************** Connection Factory JNDI name *************************
	public final static String JMS_FACTORY = "jms/RemoteConnectionFactory";
	// *************** Topic JNDI name *************************
	public final static String Topic = "jms/topic/INTopic";
	
	public final static String URL="http-remoting://localhost:8080";
	
	private TopicConnectionFactory  qconFactory;
	private TopicConnection qcon;
	private TopicSession qsession;
	private TopicSubscriber qreceiver;
	private Topic topic;
	private boolean quit = false;
	private String userName="admin";
	private String password="admin";
	

	public void onMessage(Message msg) {
		try {
			String msgText;
			if (msg instanceof TextMessage) {
				msgText = ((TextMessage) msg).getText();
			} else {
				msgText = msg.toString();
			}
			System.out.println("Msg_Receiver = " + msgText);
			
			qsession.commit();

			if (msgText.equalsIgnoreCase("quit")) {
				synchronized (this) {
					quit = true;
					qsession.commit();
					this.notifyAll(); // Notify main thread to quit
				}
			}
		} catch (JMSException jmse) {
			jmse.printStackTrace();
		}

	}

	public void init(Context ctx, String TopicName) throws NamingException,
			JMSException {
		qconFactory = (TopicConnectionFactory ) ctx.lookup(JMS_FACTORY);
		//qconFactory.getRedeliveryPolicy().setMaximumRedeliveries(2);
		qcon = qconFactory.createTopicConnection(userName, password);
		//qcon.setClientID("Hello Shailendra");
		qsession = qcon.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);

		topic = (Topic) ctx.lookup(TopicName);
		qreceiver = qsession.createSubscriber(topic);
		qreceiver.setMessageListener(this);
		qcon.start();
	}

	public void close() throws JMSException {
		qreceiver.close();
		qsession.close();
		qcon.close();
	}

	public static void main(String[] args) throws Exception {
		String url = args[0]!=null ? args[0]:URL;
		String topic = args[1]!=null ?args[1]:Topic;
		InitialContext ic= getInitialContext(url);
		
		
		TopicReceiveEAP qr = new TopicReceiveEAP();
		qr.init(ic, topic);
		System.out
				.println("JMS Ready To Receive Messages (To quit, send a  message from TopicSender.class).");
		// Wait until a "quit" message has been received.
		synchronized (qr) {
			while (!qr.quit) {
				try {
					qr.wait();
				} catch (InterruptedException ie) {
				}
			}
		}
		qr.close();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static InitialContext getInitialContext(String url)
			throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, url);
		return new InitialContext(env);
	}
}
