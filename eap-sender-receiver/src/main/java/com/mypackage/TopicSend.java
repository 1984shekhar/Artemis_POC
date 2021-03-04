package com.mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicSend {
	public final static String JNDI_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	// *************** Connection Factory JNDI name *************************
	public final static String JMS_FACTORY = "jms/RemoteConnectionFactory";
	// *************** Topic JNDI name *************************
	public final static String Topic = "jms/topic/INTopic";
	
	public final static String URL="http-remoting://localhost:8080";

	private TopicConnectionFactory qconFactory;
	private TopicConnection qcon;
	private TopicSession qsession;
	private TopicPublisher qsender;
	private Topic topic;
	private TextMessage msg;
	private String userName="admin";
	private String password="admin";
	

	public void init(Context ctx, String TopicName) throws NamingException,
			JMSException {
		qconFactory = (TopicConnectionFactory) ctx.lookup(JMS_FACTORY);
		//qcon = qconFactory.createTopicConnection();
		qcon = qconFactory.createTopicConnection(userName, password);
		qsession = qcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		topic = (Topic) ctx.lookup(TopicName);
		qsender = qsession.createPublisher(topic);
//		qsender.setTimeToLive(1000);
		msg = qsession.createTextMessage();
		qcon.start();
	}

	public void send(String message) throws JMSException {
		msg.setText(message);
		//for(int i=0;i<=100000;i++)
		qsender.send(msg);
	}

	public void close() throws JMSException {
		qsender.close();
		qsession.close();
		qcon.close();
	}

	public static void main(String[] args) throws Exception {
		String url = args[0]!=null ? args[0]:URL;
		String topic = args[1]!=null ?args[1]:Topic;
		InitialContext ic = getInitialContext(URL);
		TopicSend qs = new TopicSend();
		qs.init(ic, topic);
		readAndSend(qs);
		qs.close();
	}

	private static void readAndSend(TopicSend qs) throws IOException,
			JMSException {
		String line = "Test Message Body with counter s = ";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean readFlag = true;
		System.out.println("ntStart Sending Messages (Enter QUIT to Stop):n");
		while (readFlag) {
			System.out.print("<Msg_Sender> ");
			String msg = br.readLine();
			if (msg.equals("QUIT") || msg.equals("quit")) {
				qs.send(msg);
				System.exit(0);
			}
			qs.send(msg);
			System.out.println();
		}
		br.close();
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
