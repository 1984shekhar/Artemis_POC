$ ./domain.sh -Djboss.messaging.cluster.password=passwd

$ ./jboss-cli --connect

Following way we can create simple master-slave( one node master and one node slave)

```
[domain@localhost:9990 /] /profile=full-ha:clone(to-profile=full-ha-backup)
{
    "outcome" => "success",
    "result" => undefined,
    "server-groups" => undefined
}

[domain@localhost:9990 /] /profile=full-ha/subsystem=messaging-activemq/server=default/ha-policy=replication-master:add(check-for-live-server=true,cluster-name=my-cluster,group-name=group1)
{
    "outcome" => "success",
    "result" => undefined,
    "server-groups" => undefined
}

[domain@localhost:9990 /] /profile=full-ha-backup/subsystem=messaging-activemq/server=default/ha-policy=replication-slave:add(cluster-name=my-cluster,group-name=group1) 
{
    "outcome" => "success",
    "result" => undefined,
    "server-groups" => undefined
}
```
Following way we can create queue in both master and slave

```
[domain@localhost:9990 /] jms-queue --profile=full-ha add --queue-address=testQueue  --entries=queue/testQueue,java:jboss/exported/jms/queue/testQueue
[domain@localhost:9990 /] jms-queue --profile=full-ha-backup add --queue-address=testQueue  --entries=queue/testQueue,java:jboss/exported/jms/queue/testQueue
[domain@localhost:9990 /] 
```
