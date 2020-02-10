Create ApplicationUser:

user: admin password: admin role: guest

Statistics: 

/subsystem=messaging-activemq/server=default/jms-queue=InQueue:read-resource(include-runtime=true)

Check Messages: 

[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=InQueue:list-messages

SendMessage:
https://github.com/1984shekhar/Artemis_POC/blob/master/eap-sender-receiver/src/main/java/com/mypackage/QueueSendEAP.java
