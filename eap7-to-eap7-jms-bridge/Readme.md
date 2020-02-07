Create ApplicationUser:

user: admin password: admin role: guest

Statistics: 

/subsystem=messaging-activemq/server=default/jms-queue=InQueue:read-resource(include-runtime=true)

Check Messages: 

[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=InQueue:list-messages

