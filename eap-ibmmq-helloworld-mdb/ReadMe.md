mvn clean package

1. In EAP 7.3 configuration EAP_HOME/standalone/configuration/standalone-full.xml :


```
<subsystem xmlns="urn:jboss:domain:resource-adapters:5.0">
            <resource-adapters>
		    <resource-adapter id="wmq.jmsra.rar">
                    <archive>
                        wmq.jmsra.rar
                    </archive>
		    </resource-adapter>
            </resource-adapters>
</subsystem>
```
2. Deploy wmq.jmsra.rar in EAP_HOME/standalone/deployment folder

```
[chandrashekhar@localhost ~]$ podman ps
CONTAINER ID  IMAGE                       COMMAND  CREATED      STATUS          PORTS                   NAMES
1cda34cf9d00  docker.io/ibmcom/mq:latest           2 hours ago  Up 2 hours ago  0.0.0.0:1414->1414/tcp  stoic_northcutt
[chandrashekhar@localhost ~]$ 
[chandrashekhar@localhost ~]$ podman exec --tty --interactive 1cda34cf9d00 bash
bash-4.4$ pwd
/
bash-4.4$ find . -name wmq.jmsra.rar
find: './proc/tty/driver': Permission denied
find: './dev/mqueue': Permission denied
find: './var/db/sudo': Permission denied
find: './var/cache/ldconfig': Permission denied
./opt/mqm/java/lib/jca/wmq.jmsra.rar
find: './lost+found': Permission denied
bash-4.4$ exit
exit
Error: exec session exited with non-zero exit code 1: OCI runtime error
[chandrashekhar@localhost ~]$ podman cp 1cda34cf9d00:/opt/mqm/java/lib/jca/wmq.jmsra.rar .
[chandrashekhar@localhost ~]$ ls -ltr|grep rar
-r--r--r--.  1 chandrashekhar chandrashekhar 13241863 Mar 31 12:28 wmq.jmsra.rar
[chandrashekhar@localhost ~]$ 

```

3. Start EAP as
```
./path/to/EAP_HOME/bin/standalone.xml -c standalone-full.xml
```

