- Test ldap connection with ldapsearch utility.
```
[chandrashekhar@localhost security-ldap]$ ldapsearch -H ldap://localhost:10390 -x -D "uid=bill,dc=activemq,dc=org" -w activemq|less
```
- Producer and Consumer
```
[chandrashekhar@localhost bin]$ ./artemis producer --user andrew --password activemq1 --destination queue://news.europe.europeTopic --message-count 1
Connection brokerURL = tcp://localhost:61616
Producer ActiveMQTopic[news.europe.europeTopic], thread=0 Started to calculate elapsed time ...

Producer ActiveMQTopic[news.europe.europeTopic], thread=0 Produced: 1 messages
Producer ActiveMQTopic[news.europe.europeTopic], thread=0 Elapsed time in second : 0 s
Producer ActiveMQTopic[news.europe.europeTopic], thread=0 Elapsed time in milli second : 78 milli seconds
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ 
[chandrashekhar@localhost bin]$ ./artemis consumer --user frank --password activemq2 --destination queue://news.europe.europeTopic --message-count 1
Connection brokerURL = tcp://localhost:61616
Consumer:: filter = null
Consumer ActiveMQTopic[news.europe.europeTopic], thread=0 wait until 1 messages are consumed
```
- Logs
 
```
2020-07-11 01:38:35,066 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Create the LDAP initial context.
2020-07-11 01:38:35,067 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Referral handling: ignore
2020-07-11 01:38:35,070 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Get the user DN.
2020-07-11 01:38:35,070 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Looking for the user in LDAP with 
2020-07-11 01:38:35,071 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   base DN: dc=activemq,dc=org
2020-07-11 01:38:35,071 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   filter: (uid=frank)
2020-07-11 01:38:35,073 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] LDAP returned a relative name: uid=frank
2020-07-11 01:38:35,073 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Using DN [uid=frank,dc=activemq,dc=org] for binding.
2020-07-11 01:38:35,073 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Binding the user.
2020-07-11 01:38:35,076 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] User uid=frank,dc=activemq,dc=org successfully bound.
2020-07-11 01:38:35,077 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Get user roles.
2020-07-11 01:38:35,077 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Looking for the user roles in LDAP with 
2020-07-11 01:38:35,077 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   base DN: dc=activemq,dc=org
```
