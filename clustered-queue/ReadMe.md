#In broker.xml message-load-balancing is set to STRICT

message-load-balancing. This parameter determines if/how messages will be distributed between other nodes of the cluster. It can be one of three values - OFF, STRICT, or ON_DEMAND (default). This parameter replaces the deprecated forward-when-no-consumers parameter.

If this is set to OFF then messages will never be forwarded to another node in the cluster

If this is set to STRICT then each incoming message will be round robin'd even though the same queues on the other nodes of the cluster may have no consumers at all, or they may have consumers that have non matching message filters (selectors). Note that Apache ActiveMQ Artemis will not forward messages to other nodes if there are no queues of the same name on the other nodes, even if this parameter is set to STRICT. Using STRICT is like setting the legacy forward-when-no-consumers parameter to true.

If this is set to ON_DEMAND then Apache ActiveMQ Artemis will only forward messages to other nodes of the cluster if the address to which they are being forwarded has queues which have consumers, and if those consumers have message filters (selectors) at least one of those selectors must match the message. Using ON_DEMAND is like setting the legacy forward-when-no-consumers parameter to false.

Default is ON_DEMAND.

#might have to stop iptables service so that bridge connection can be created between brokers. 

#Once cluster is configured successfully we can test with producer on one of the broker and check that messages are sent in round robin sequence to each broker.

service iptables stop 

./artemis producer --url tcp://0.0.0.0:61621 --message-count 4 --destination exampleQueue

./artemis queue stat --url tcp://0.0.0.0:61622 --queueName exampleQueue

./artemis queue stat --url tcp://0.0.0.0:61621 --queueName exampleQueue

chandrashekhar@chandrashekhar:~/Development/Red_Hat_AMQ/AMQ_7.2_Instance/broker_cluster_2/bin$ jps

15843 Jps

13443 Artemis

13357 Artemis


chandrashekhar@chandrashekhar:~/Development/Red_Hat_AMQ/AMQ_7.2_Instance/broker_cluster_2/bin$ netstat -anp|grep 13443

(Not all processes could be identified, non-owned process info
 will not be shown, you would have to be root to see it all.)
tcp6       0      0 :::61622                :::*                    LISTEN      13443/java          
tcp6       0      0 127.0.0.1:8167          :::*                    LISTEN      13443/java          
tcp6       0      0 ::1:41462               ::1:61621               ESTABLISHED 13443/java          
tcp6       0      0 ::1:61622               ::1:42618               ESTABLISHED 13443/java          
tcp6       0      0 ::1:61622               ::1:42620               ESTABLISHED 13443/java          
tcp6       0      0 ::1:61622               ::1:42628               ESTABLISHED 13443/java          
tcp6       0      0 ::1:41470               ::1:61621               ESTABLISHED 13443/java          
tcp6       0      0 ::1:42620               ::1:61622               ESTABLISHED 13443/java          
tcp6       0      0 ::1:61622               ::1:42630               ESTABLISHED 13443/java          
tcp6       0      0 ::1:41476               ::1:61621               ESTABLISHED 13443/java          
udp6       0      0 :::37243                :::*                                13443/java          
udp6    1280      0 231.7.7.7:9876          :::*                                13443/java          
udp6   17920      0 231.7.7.7:9876          :::*                                13443/java          
unix  2      [ ]         STREAM     CONNECTED     126124   13443/java           
unix  2      [ ]         STREAM     CONNECTED     125239   13443/java           

chandrashekhar@chandrashekhar:~/Development/Red_Hat_AMQ/AMQ_7.2_Instance/broker_cluster_2/bin$ netstat -anp|grep 13357
(Not all processes could be identified, non-owned process info
 will not be shown, you would have to be root to see it all.)
tcp6       0      0 :::61621                :::*                    LISTEN      13357/java          
tcp6       0      0 127.0.0.1:8166          :::*                    LISTEN      13357/java          
tcp6       0      0 ::1:42628               ::1:61622               ESTABLISHED 13357/java          
tcp6       0      0 ::1:42630               ::1:61622               ESTABLISHED 13357/java          
tcp6       0      0 ::1:42618               ::1:61622               ESTABLISHED 13357/java          
tcp6       0      0 ::1:61621               ::1:41460               ESTABLISHED 13357/java          
tcp6       0      0 ::1:61621               ::1:41462               ESTABLISHED 13357/java          
tcp6       0      0 ::1:61621               ::1:41476               ESTABLISHED 13357/java          
tcp6       0      0 ::1:61621               ::1:41470               ESTABLISHED 13357/java          
tcp6       0      0 ::1:41460               ::1:61621               ESTABLISHED 13357/java          
udp6       0      0 :::56894                :::*                                13357/java          
udp6    2560      0 231.7.7.7:9876          :::*                                13357/java          
udp6    3840      0 231.7.7.7:9876          :::*                                13357/java          
unix  2      [ ]         STREAM     CONNECTED     120750   13357/java           
unix  2      [ ]         STREAM     CONNECTED     127162   13357/java           

chandrashekhar@chandrashekhar:~/Development/Red_Hat_AMQ/AMQ_7.2_Instance/broker_cluster_2/bin$ 

