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

2. Start EAP as
./path/to/EAP_HOME/bin/standalone.xml -c standalone-full.xml