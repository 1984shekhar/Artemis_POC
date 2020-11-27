```
# EAP1
./standalone.sh -c standalone-full-ha.xml 

# EAP2
./standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=100

# EAP3
./standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=200
```
