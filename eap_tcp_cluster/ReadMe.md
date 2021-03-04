```
./standalone.sh -c standalone-full-ha.xml

./standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=1000

server 1 :

2021-03-04 10:05:41,781 INFO  [org.apache.activemq.artemis.core.server] (Thread-5 (ActiveMQ-server-org.apache.activemq.artemis.core.server.impl.ActiveMQServerImpl$6@244ca6b1)) AMQ221027: Bridge ClusterConnectionBridge@3ba8ccd1 [name=$.artemis.internal.sf.my-IN-cluster.a98469af-7be0-11eb-b035-b0fc366fae1f, queue=QueueImpl[name=$.artemis.internal.sf.my-IN-cluster.a98469af-7be0-11eb-b035-b0fc366fae1f, postOffice=PostOfficeImpl [server=ActiveMQServerImpl::serverUUID=dd2054d2-e635-11ea-895b-d0374540288a], temp=false]@6d17d482 targetConnector=ServerLocatorImpl (identity=(Cluster-connection-bridge::ClusterConnectionBridge@3ba8ccd1 [name=$.artemis.internal.sf.my-IN-cluster.a98469af-7be0-11eb-b035-b0fc366fae1f, queue=QueueImpl[name=$.artemis.internal.sf.my-IN-cluster.a98469af-7be0-11eb-b035-b0fc366fae1f, postOffice=PostOfficeImpl [server=ActiveMQServerImpl::serverUUID=dd2054d2-e635-11ea-895b-d0374540288a], temp=false]@6d17d482 targetConnector=ServerLocatorImpl [initialConnectors=[TransportConfiguration(name=http-connector, factory=org-apache-activemq-artemis-core-remoting-impl-netty-NettyConnectorFactory) ?httpUpgradeEndpoint=http-acceptor&activemqServerName=default&httpUpgradeEnabled=true&port=9080&host=localhost], discoveryGroupConfiguration=null]]::ClusterConnectionImpl@1426268022[nodeUUID=dd2054d2-e635-11ea-895b-d0374540288a, connector=TransportConfiguration(name=http-connector, factory=org-apache-activemq-artemis-core-remoting-impl-netty-NettyConnectorFactory) ?httpUpgradeEndpoint=http-acceptor&activemqServerName=default&httpUpgradeEnabled=true&port=8080&host=localhost, address=jms, server=ActiveMQServerImpl::serverUUID=dd2054d2-e635-11ea-895b-d0374540288a])) [initialConnectors=[TransportConfiguration(name=http-connector, factory=org-apache-activemq-artemis-core-remoting-impl-netty-NettyConnectorFactory) ?httpUpgradeEndpoint=http-acceptor&activemqServerName=default&httpUpgradeEnabled=true&port=9080&host=localhost], discoveryGroupConfiguration=null]] is connected

server 2 :

2021-03-04 10:05:41,786 INFO  [org.apache.activemq.artemis.core.server] (Thread-2 (ActiveMQ-server-org.apache.activemq.artemis.core.server.impl.ActiveMQServerImpl$6@1b8d67b6)) AMQ221027: Bridge ClusterConnectionBridge@28ff7ee9 [name=$.artemis.internal.sf.my-in-cluster.dd2054d2-e635-11ea-895b-d0374540288a, queue=QueueImpl[name=$.artemis.internal.sf.my-in-cluster.dd2054d2-e635-11ea-895b-d0374540288a, postOffice=PostOfficeImpl [server=ActiveMQServerImpl::serverUUID=a98469af-7be0-11eb-b035-b0fc366fae1f], temp=false]@12ca73df targetConnector=ServerLocatorImpl (identity=(Cluster-connection-bridge::ClusterConnectionBridge@28ff7ee9 [name=$.artemis.internal.sf.my-in-cluster.dd2054d2-e635-11ea-895b-d0374540288a, queue=QueueImpl[name=$.artemis.internal.sf.my-in-cluster.dd2054d2-e635-11ea-895b-d0374540288a, postOffice=PostOfficeImpl [server=ActiveMQServerImpl::serverUUID=a98469af-7be0-11eb-b035-b0fc366fae1f], temp=false]@12ca73df targetConnector=ServerLocatorImpl [initialConnectors=[TransportConfiguration(name=http-connector, factory=org-apache-activemq-artemis-core-remoting-impl-netty-NettyConnectorFactory) ?httpUpgradeEndpoint=http-acceptor&activemqServerName=default&httpUpgradeEnabled=true&port=8080&host=localhost], discoveryGroupConfiguration=null]]::ClusterConnectionImpl@868888823[nodeUUID=a98469af-7be0-11eb-b035-b0fc366fae1f, connector=TransportConfiguration(name=http-connector, factory=org-apache-activemq-artemis-core-remoting-impl-netty-NettyConnectorFactory) ?httpUpgradeEndpoint=http-acceptor&activemqServerName=default&httpUpgradeEnabled=true&port=9080&host=localhost, address=jms, server=ActiveMQServerImpl::serverUUID=a98469af-7be0-11eb-b035-b0fc366fae1f])) [initialConnectors=[TransportConfiguration(name=http-connector, factory=org-apache-activemq-artemis-core-remoting-impl-netty-NettyConnectorFactory) ?httpUpgradeEndpoint=http-acceptor&activemqServerName=default&httpUpgradeEnabled=true&port=8080&host=localhost], discoveryGroupConfiguration=null]] is connected
```

