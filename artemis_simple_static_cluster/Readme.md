1. 
```
../amq-broker-7.5.0/bin/artemis create broker2 
../amq-broker-7.5.0/bin/artemis create broker2 --port-offset 1

[cpandey@cpandey AMQ_750_INSTANCES]$ pwd
/home/cpandey/NotBackedUp/Development/AMQ_RedHat/AMQ_750_INSTANCES
[cpandey@cpandey AMQ_750_INSTANCES]$ ls
broker1  broker2
[cpandey@cpandey AMQ_750_INSTANCES]$ 
```


2. 
```
[cpandey@cpandey bin]$ ./artemis producer --url tcp://localhost:61616 --user admin --password admin --destination csp --message-count 4

Producer ActiveMQQueue[csp], thread=0 Started to calculate elapsed time ...

Producer ActiveMQQueue[csp], thread=0 Produced: 4 messages

Producer ActiveMQQueue[csp], thread=0 Elapsed time in second : 0 s

Producer ActiveMQQueue[csp], thread=0 Elapsed time in milli second : 41 milli seconds
```

3.
```
[cpandey@cpandey bin]$ ./artemis consumer --url tcp://localhost:61616 --user admin --password admin --destination csp --verbose

Executing org.apache.activemq.artemis.cli.commands.messages.Consumer consumer --url tcp://localhost:61616 --user admin --password admin --destination csp --verbose 

Home::/home/cpandey/NotBackedUp/Development/AMQ_RedHat/amq-broker-7.5.0, Instance::/home/cpandey/
NotBackedUp/Development/AMQ_RedHat/AMQ_750_INSTANCES/broker1
Consumer:: filter = null
Consumer ActiveMQQueue[csp], thread=0 wait until 1000 messages are consumed
Consumer ActiveMQQueue[csp], thread=0 Received test message: 0
Received text sized at 15
Consumer ActiveMQQueue[csp], thread=0 Received test message: 2
Received text sized at 15
```

4.
```
[cpandey@cpandey bin]$ ./artemis consumer --url tcp://localhost:61617 --user admin --password admin --destination csp --verbose

Executing org.apache.activemq.artemis.cli.commands.messages.Consumer consumer --url tcp://localhost:61617 --user admin --password admin --destination csp --verbose 

Home::/home/cpandey/NotBackedUp/Development/AMQ_RedHat/amq-broker-7.5.0, Instance::/home/cpandey/NotBackedUp/Development/AMQ_RedHat/AMQ_750_INSTANCES/broker1

Consumer:: filter = null
Consumer ActiveMQQueue[csp], thread=0 wait until 1000 messages are consumed
Consumer ActiveMQQueue[csp], thread=0 Received test message: 1
Received text sized at 15
Consumer ActiveMQQueue[csp], thread=0 Received test message: 3
Received text sized at 15
```


5.
```
[cpandey@cpandey bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61616 --queueName csp
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|csp                      |csp                      |1              |0             |2              |0                |2              |0               |ANYCAST      |
[cpandey@cpandey bin]$ 
[cpandey@cpandey bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61617 --queueName csp
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|csp                      |csp                      |1              |0             |2              |0                |2              |0               |ANYCAST      |
[cpandey@cpandey bin]$ 
```


