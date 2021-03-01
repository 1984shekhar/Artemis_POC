Create ApplicationUser:

user: admin
password: admin
role: guest

 <jms-queue name="InQueue" entries="queue/InQueue jms/queue/InQueue java:jboss/exported/jms/queue/InQueue"/>

Statistics:
/subsystem=messaging-activemq/server=default/jms-queue=InQueue:read-resource(include-runtime=true)

Check Messages:
[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=InQueue:list-messages

[chandrashekhar@localhost bin]$ ./jboss-cli.sh --connect --controller=localhost:10090

[standalone@localhost:10090 /] /subsystem=messaging-activemq/server=default/jms-queue=InQueue:query(select=[message-count])
{
    "outcome" => "success",
    "result" => {"message-count" => 2L}
}



