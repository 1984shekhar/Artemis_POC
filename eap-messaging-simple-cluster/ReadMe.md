Server1:
./standalone.sh -c standalone-full-ha.xml
#check logs
tail -f jboss-eap-7.2_2/standalone/log/server.log

Server2:
./standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=100
#check logs
tail -f jboss-eap-7.2_2/standalone/log/server.log

