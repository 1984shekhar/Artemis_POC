Static queue must exist for load-balancing even for  message-load-balancing is set to message-load-balancing. Here "exampleQueue" is the queue set in broker.xml.


Store and Forward queues:

[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61617|head -n 3
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|$.artemis.internal.sf.my-cluster.32966acd-5ca1-11ea-9dbb-d0374540288a|$.artemis.internal.sf.my-cluster.32966acd-5ca1-11ea-9dbb-d0374540288a|1              |0             |0              |0                |0              |0               |MULTICAST    |
|$.artemis.internal.sf.my-cluster.b4a9c8ac-5ca0-11ea-ab8f-d0374540288a|$.artemis.internal.sf.my-cluster.b4a9c8ac-5ca0-11ea-ab8f-d0374540288a|1              |0             |0              |0                |0              |0               |MULTICAST    |
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61619|head -n 3
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|$.artemis.internal.sf.my-cluster.32966acd-5ca1-11ea-9dbb-d0374540288a|$.artemis.internal.sf.my-cluster.32966acd-5ca1-11ea-9dbb-d0374540288a|1              |0             |0              |0                |0              |0               |MULTICAST    |
|$.artemis.internal.sf.my-cluster.90fb23c3-5c9d-11ea-a90f-d0374540288a|$.artemis.internal.sf.my-cluster.90fb23c3-5c9d-11ea-a90f-d0374540288a|1              |0             |0              |0                |0              |0               |MULTICAST    |
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61621|head -n 3
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|$.artemis.internal.sf.my-cluster.90fb23c3-5c9d-11ea-a90f-d0374540288a|$.artemis.internal.sf.my-cluster.90fb23c3-5c9d-11ea-a90f-d0374540288a|1              |0             |0              |0                |0              |0               |MULTICAST    |
|$.artemis.internal.sf.my-cluster.b4a9c8ac-5ca0-11ea-ab8f-d0374540288a|$.artemis.internal.sf.my-cluster.b4a9c8ac-5ca0-11ea-ab8f-d0374540288a|1              |0             |0              |0                |0              |0               |MULTICAST    |
[chandrashekhar@localhost bin]$ 

Produce Messages:

[chandrashekhar@localhost bin]$ ./artemis producer --user admin --password admin --url tcp://localhost:61617 --message-count 3 --destination exampleQueue
Producer ActiveMQQueue[exampleQueue], thread=0 Started to calculate elapsed time ...

Producer ActiveMQQueue[exampleQueue], thread=0 Produced: 3 messages
Producer ActiveMQQueue[exampleQueue], thread=0 Elapsed time in second : 0 s
Producer ActiveMQQueue[exampleQueue], thread=0 Elapsed time in milli second : 191 milli seconds

Check Status:

[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61621|grep exampleQueue
|exampleQueue             |exampleQueue             |0              |1             |1              |0                |0              |0               |ANYCAST      |
[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61619|grep exampleQueue
|exampleQueue             |exampleQueue             |0              |1             |1              |0                |0              |0               |ANYCAST      |
[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin --url tcp://localhost:61617|grep exampleQueue
|exampleQueue             |exampleQueue             |0              |1             |1              |0                |0              |0               |ANYCAST      |
[chandrashekhar@localhost bin]$ 
