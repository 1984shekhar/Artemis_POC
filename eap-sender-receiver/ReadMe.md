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
$ mvn exec:java -Dexec.mainClass="com.mypackage.QueueReceiveEAP" -Dexec.args="http-remoting://localhost:8080 jms/topic/INDLQ"

$ mvn exec:java -Dexec.mainClass="com.mypackage.QueueSendEAP" -Dexec.args="http-remoting://localhost:8080 jms/topic/INDLQ"

```

