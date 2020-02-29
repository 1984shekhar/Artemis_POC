


[chandrashekhar@localhost bin]$ ./artemis queue stat --user admin --password admin
|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |
|DLQ                      |DLQ                      |0              |0             |0              |0                |0              |0               |ANYCAST      |
|ExpiryQueue              |ExpiryQueue              |0              |0             |0              |0                |0              |0               |ANYCAST      |
|MyOtherQueue             |MyOtherQueue             |0              |0             |0              |0                |0              |0               |ANYCAST      |
|MyQueue                  |MyQueue                  |15             |0             |5              |0                |5              |0               |ANYCAST      |
|activemq.management.2e561bd8-c833-4183-8dd0-9494ab836740|activemq.management.2e561bd8-c833-4183-8dd0-9494ab836740|1              |0             |0              |0                |0              |0               |MULTICAST    |
|jms.queue.MyQueue        |jms.queue.MyQueue        |0              |10            |10             |0                |0              |0               |ANYCAST      |
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ ./artemis producer --url tcp://localhost:61616 --user admin --password admin --message-count 5 --message "hello all" --destination MyQueue
Producer ActiveMQQueue[MyQueue], thread=0 Started to calculate elapsed time ...

Producer ActiveMQQueue[MyQueue], thread=0 Produced: 5 messages
Producer ActiveMQQueue[MyQueue], thread=0 Elapsed time in second : 0 s
Producer ActiveMQQueue[MyQueue], thread=0 Elapsed time in milli second : 283 milli seconds
[chandrashekhar@localhost bin]$ 

