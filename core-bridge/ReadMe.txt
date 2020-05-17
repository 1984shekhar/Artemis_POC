Check ScreenShot SendMessage.png. You have to send similar message to tcp://localhost:61616 broker. Check following statistics.


```
[chandrashekhar@localhost bin]$ ./artemis queue stat --url tcp://localhost:61616 --user admin --password admin
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|activemq.management.970803a7-a649-455b-bf8d-c5d3bcff3f55|activemq.management.970803a7-a649-455b-bf8d-c5d3bcff3f55|1              |0             |0              |0                |0              |0               |MULTICAST    |
|sausage-factory          |sausage-factory          |1              |1             |3              |0                |2              |0               |ANYCAST      |
[chandrashekhar@localhost bin]$ ./artemis queue stat --url tcp://localhost:61617 --user admin --password admin
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|activemq.management.0c6c0ccb-28a9-4500-a30e-df620a3ec239|activemq.management.0c6c0ccb-28a9-4500-a30e-df620a3ec239|1              |0             |0              |0                |0              |0               |MULTICAST    |
|mincing-machine          |mincing-machine          |0              |2             |2              |0                |0              |0               |ANYCAST      |
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ ./artemis browser --url tcp://localhost:61617 --user admin --password admin --destination mincing-machine
Consumer:: filter = null
Consumer ActiveMQQueue[mincing-machine], thread=0 trying to browse 1000 messages
Consumer ActiveMQQueue[mincing-machine], thread=0 browsing Hey ALL




Consumer ActiveMQQueue[mincing-machine], thread=0 browsing 1111




Consumer ActiveMQQueue[mincing-machine], thread=0 browsed: 2 messages
Consumer ActiveMQQueue[mincing-machine], thread=0 Browser thread finished


```
