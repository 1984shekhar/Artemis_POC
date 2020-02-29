Server1:
./standalone.sh -c standalone-full-ha.xml

#check logs

tail -f jboss-eap-7.2_2/standalone/log/server.log

Server2:

./standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=100

#check logs

tail -f jboss-eap-7.2_2/standalone/log/server.log



#Check statistics JMS queue:

- Count Message:

/subsystem=messaging-activemq/server=default/jms-queue=InQueue:count-messages

- List Message:

/subsystem=messaging-activemq/server=default/jms-queue=InQueue:list-messages

- Remove Message:

/subsystem=messaging-activemq/server=default/jms-queue=InQueue:remove-messages()



#Check statistics runtim queue:

- Count Message:

/subsystem=messaging-activemq/server=default/runtime-queue=InQueue:count-messages

- List Message:

/subsystem=messaging-activemq/server=default/runtime-queue=InQueue:list-messages

- Remove Message:

/subsystem=messaging-activemq/server=default/runtime-queue=InQueue:remove-messages()
