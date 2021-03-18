Send Message using artemis script found in AMQ 7.x or Artemis:

<jms-queue name="testQueue" entries="queue/test java:jboss/exported/jms/queue/test"/>
$ ./artemis producer --user admin --password admin --message-size 100 --message-count 191876 --destination testQueue --threads 10 --url tcp://127.0.0.1:8080?httpUpgradeEnabled=true

Send/Receive message from EAP
Create ApplicationUser:
```
user: admin
password: admin
role: guest

 <jms-queue name="InQueue" entries="queue/InQueue jms/queue/InQueue java:jboss/exported/jms/queue/InQueue"/>
```

Statistics:
```
/subsystem=messaging-activemq/server=default/jms-queue=InQueue:read-resource(include-runtime=true)
```

Check Messages:
```
[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=InQueue:list-messages

[chandrashekhar@localhost bin]$ ./jboss-cli.sh --connect --controller=localhost:10090

[standalone@localhost:10090 /] /subsystem=messaging-activemq/server=default/jms-queue=InQueue:query(select=[message-count])
{
    "outcome" => "success",
    "result" => {"message-count" => 2L}
}
```

For Topic can be run as:
```
$ mvn exec:java -Dexec.mainClass="com.mypackage.TopicReceiveEAP" -Dexec.args="http-remoting://localhost:8080 jms/topic/INTopic"

$ mvn exec:java -Dexec.mainClass="com.mypackage.TopicSend" -Dexec.args="http-remoting://localhost:8080 jms/topic/INTopic"

```
For Queue can be run as:
```
$ mvn exec:java -Dexec.mainClass="com.mypackage.QueueReceiveEAP" -Dexec.args="http-remoting://localhost:8080 jms/queue/INDLQ"

$ mvn exec:java -Dexec.mainClass="com.mypackage.QueueSendEAP" -Dexec.args="http-remoting://localhost:8080 jms/queue/INDLQ"

```
Expiry Queue:

```
[standalone@localhost:9990 /] jms-queue add --queue-address=testQueue  --entries=queue/test,java:jboss/exported/jms/queue/test
[standalone@localhost:9990 /] jms-queue read-resource --queue-address=testQueue
[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=testQueue:read-resource(include-runtime=true)
{
    "outcome" => "success",
    "result" => {
        "consumer-count" => 0,
        "dead-letter-address" => "jms.queue.DLQ",
        "delivering-count" => 0,
        "durable" => true,
        "entries" => [
            "queue/test",
            "java:jboss/exported/jms/queue/test"
        ],
        "expiry-address" => "jms.queue.ExpiryQueue",
        "legacy-entries" => undefined,
        "message-count" => 0L,
        "messages-added" => 2L,
        "paused" => false,
        "queue-address" => "jms.queue.testQueue",
        "scheduled-count" => 0L,
        "selector" => undefined,
        "temporary" => false
    }
}

[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=ExpiryQueue:read-resource(include-runtime=true)
{
    "outcome" => "success",
    "result" => {
        "consumer-count" => 0,
        "dead-letter-address" => "jms.queue.DLQ",
        "delivering-count" => 0,
        "durable" => true,
        "entries" => ["java:/jms/queue/ExpiryQueue"],
        "expiry-address" => "jms.queue.ExpiryQueue",
        "legacy-entries" => undefined,
        "message-count" => 2L,
        "messages-added" => 2L,
        "paused" => false,
        "queue-address" => "jms.queue.ExpiryQueue",
        "scheduled-count" => 0L,
        "selector" => undefined,
        "temporary" => false
    }
}

 
[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=ExpiryQueue:remove-messages
{
    "outcome" => "success",
    "result" => 2
}

[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=ExpiryQueue:read-resource(include-runtime=true)
{
    "outcome" => "success",
    "result" => {
        "consumer-count" => 0,
        "dead-letter-address" => "jms.queue.DLQ",
        "delivering-count" => 0,
        "durable" => true,
        "entries" => ["java:/jms/queue/ExpiryQueue"],
        "expiry-address" => "jms.queue.ExpiryQueue",
        "legacy-entries" => undefined,
        "message-count" => 0L,
        "messages-added" => 2L,
        "paused" => false,
        "queue-address" => "jms.queue.ExpiryQueue",
        "scheduled-count" => 0L,
        "selector" => undefined,
        "temporary" => false
    }
}

[standalone@localhost:9990 /] 
```

