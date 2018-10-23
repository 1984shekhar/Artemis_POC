1. Once you build the project using 'mvn package', paste jar at location amq_broker_home/lib. 

2. In amq_broker_home/etc/broker.xml, paste following within core tag of broker.xml:
<core....>
---
---
    <remoting-incoming-interceptors>
       <class-name>com.mycompany.interceptor.SimpleInterceptor</class-name>
    </remoting-incoming-interceptors>
    <remoting-outgoing-interceptors>
       <class-name>com.mycompany.interceptor.SimpleInterceptor</class-name>
    </remoting-outgoing-interceptors>
---
---
</core>

3. Start AMQ node now.

4. Now test using following commands from amq_broker_home/bin folder:

./artemis consumer --url tcp://localhost:61616 --user admin --password admin --destination queue://TESTCP --verbose
./artemis producer --url tcp://localhost:61616 --user admin --password admin --destination queue://TESTCP --message-count 1 

5. In logs we will get:

#For Sender
SimpleInterceptor gets called!.... Packet: org.apache.activemq.artemis.core.protocol.core.impl.wireformat.SessionSendMessageRemotingConnection: /127.0.0.1:49896
Msg: CoreMessage[messageID=0,durable=true,userID=4e094a2a-d6e0-11e8-a444-e8b1fc466329,priority=4, timestamp=Tue Oct 23 21:55:50 IST 2018,expiration=0, durable=true, address=exampleQueue,size=270,properties=TypedProperties[__AMQ_CID=4df09207-d6e0-11e8-a444-e8b1fc466329,_AMQ_ROUTING_TYPE=1]]@183519025

#For Receiver
SimpleInterceptor gets called!.... Packet: org.apache.activemq.artemis.core.protocol.core.impl.wireformat.SessionReceiveMessageRemotingConnection: /127.0.0.1:49896
Msg: CoreMessage[messageID=3333,durable=true,userID=4e094a2a-d6e0-11e8-a444-e8b1fc466329,priority=4, timestamp=Tue Oct 23 21:55:50 IST 2018,expiration=0, durable=true, address=exampleQueue,size=270,properties=TypedProperties[__AMQ_CID=4df09207-d6e0-11e8-a444-e8b1fc466329,_AMQ_ROUTING_TYPE=1]]@1835190256
 



