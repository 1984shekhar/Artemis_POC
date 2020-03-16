package com.mypackage;

import java.io.*;
import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueSendEAP {
	public final static String JNDI_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	// *************** Connection Factory JNDI name *************************
	public final static String JMS_FACTORY = "jms/RemoteConnectionFactory";
	// *************** Queue JNDI name *************************
	public final static String QUEUE = "jms/queue/clusterQ";
	
	public final static String URL="http-remoting://localhost:8080";

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;
	private String userName="admin";
	private String password="admin";
	

	public void init(Context ctx, String queueName) throws NamingException,
			JMSException {
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
		//qcon = qconFactory.createQueueConnection();
		qcon = qconFactory.createQueueConnection(userName, password);
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
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
		InitialContext ic = getInitialContext(URL);
		QueueSendEAP qs = new QueueSendEAP();
		qs.init(ic, QUEUE);
		readAndSend(qs);
		qs.close();
	}

	private static void readAndSend(QueueSendEAP qs) throws IOException,
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
