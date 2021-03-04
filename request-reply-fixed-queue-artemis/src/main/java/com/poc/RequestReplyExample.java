package com.poc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.jboss.logging.Logger;

public class RequestReplyExample {

    //static Logger log=Logger.getLogger(RequestReplyExample.class);

    public static void main(final String[] args) throws Exception {

        final Map<String, TextMessage> requestMap = new HashMap<String, TextMessage>();
        Connection connection = null;
        String brokerURL = "(tcp://localhost:61616,tcp://localhost:61618)?ha=true&reconnectAttempts=-1&retryInterval=5000";
        String username = "admin";
        String password = "admin";
        String requestQueueName = "REQ.3M3S";
        String responseQueueName = "RES.3M3S";
   	
        String detokenReq = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:DecodeTokenRq xmlns:ns2=\"http://my.company.com/api/xsd/tokenization/v1_0\"><ns2:PartyToken>56225154001</ns2:PartyToken></ns2:DecodeTokenRq>";

        try {

            RequestConsumer requestConsumer = new RequestConsumer();
            //Queue requestQueue = new ActiveMQQueue(requestQueueName);
            //Destination responseQueue = new ActiveMQQueue(responseQueueName);
            ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL, username, password);
            connection = cf.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
           Queue requestQueue = session.createQueue(requestQueueName);
           Queue responseQueue = session.createQueue(responseQueueName);
            final MessageProducer producer = session.createProducer(requestQueue);
            List<String> messageIds = new ArrayList<>();
            //TextMessage[] requestMsgs = textMesaages(detokenReq, session, responseQueue);
            TextMessage[] textMesaages = new TextMessage[10];
            for (int i = 0; i < 10; i++) {
                
                textMesaages[i] = session.createTextMessage(detokenReq);
                textMesaages[i].setJMSReplyTo(responseQueue);
                /*
    			 * try { Thread.sleep(10000); } catch (InterruptedException e) {
    			 * 
    			 * e.printStackTrace(); }
    			 */
                System.out.println(textMesaages[i].getJMSCorrelationID());
            }
            
            Arrays.stream(textMesaages).forEach(i -> {
                try {
                    producer.send(i);
                    System.out.println(i.getJMSMessageID());
                    messageIds.add(i.getJMSMessageID());
                } catch (JMSException e1) {
                    e1.printStackTrace();
                }
            });

            System.out.println("Request message sent.");


            System.out.println(messageIds);
            messageIds.forEach(messageId -> {
                MessageConsumer replyConsumer;
                try {
                    replyConsumer = session.createConsumer(responseQueue);
                    TextMessage replyMessageReceived = (TextMessage) replyConsumer.receive(25000);
                    System.out.println("-------------Start--------------------");
                    if (replyMessageReceived == null) {
                        System.out.println("Time out for messgaeid : " + messageId);
                    } else {
                        System.out.println("Got reply for CorrelatedId: " + messageId);
                    }
                    System.out.println("----------------END-----------------");
                    replyConsumer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            });

            producer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {

                connection.close();

            }

        }

    }

}

class RequestConsumer implements MessageListener{
    private Session session = null;
    final 	String detokenReq = "Reply";
    final String brokerURL = "(tcp://localhost:61616,tcp://localhost:61618)?ha=true&reconnectAttempts=-1&retryInterval=5000";
    final String username = "admin";
    final String password = "admin";
    Destination responseQ = null;
    public RequestConsumer() throws Exception{

        String requestQueueName = "REQ.3M3S";
        Queue requestQueue = new ActiveMQQueue(requestQueueName);
        ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL, username, password);
        Connection connection = cf.createConnection();
        connection.start();
        this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(requestQueue);
        consumer.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        try{
        	System.out.println("Within receiver onmessage");
            ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL, username, password);
            Connection connection = cf.createConnection();
            connection.start();
            responseQ = message.getJMSReplyTo();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(responseQ);
            TextMessage replyMessage = session.createTextMessage(detokenReq);
            //Destination destination = session.createQueue(message.getJMSMessageID());
            producer.send(replyMessage);
            this.session.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
