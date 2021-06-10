- Create application user in EAP with user: admin and password: admin with guest role.
- In eap we would need one change, you would have to comment out following from "application-realm" configuration of EAP. 

 <local default-user="$local" allowed-users="*" skip-group-loading="true"/>
