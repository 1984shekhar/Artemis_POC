minikube start --cpus 4 --memory 10240Mi -p amq-online

minikube profile list

eval $(minikube -p amq-online docker-env)

kubectl config set-context $(kubectl config current-context) --namespace=enmasse-infra

kubectl apply -f install/bundles/enmasse

kubectl apply -f minimal-setup.yaml

kubectl apply -f minimal-address-space.yaml

kubectl apply -f minimal-address.yaml

kubectl exec -it broker-30e3f82-hbnp-0 -- /bin/bash

bash-4.2# cd /opt/apache-artemis/bin

bash-4.2# ./artemis queue stat --user "" --password ""|grep -E "NAME|myqueue"

|NAME                     |ADDRESS                  |CONSUMER_COUNT |MESSAGE_COUNT |MESSAGES_ADDED |DELIVERING_COUNT |MESSAGES_ACKED |SCHEDULED_COUNT |ROUTING_TYPE |

|myqueue                  |myqueue                  |1              |0             |0              |0                |0              |0               |ANYCAST      |

bash-4.2# 

kubectl get addressspace myspace -o jsonpath={.status.endpointStatuses[?(@.name==\'messaging\')].serviceHost}

> messaging-30e3f82.enmasse-infra.svc

bash-4.2# ./artemis producer --url tcp://messaging-30e3f82.enmasse-infra.svc:5672 --user "" --password "" --destination myqueue --message-count 3 --protocol amqp

./artemis consumer --url tcp://messaging-30e3f82.enmasse-infra.svc:5672 --user "" --password "" --destination myqueue --protocol amqp

kubectl get pods -l name=qdrouterd

> NAME                  READY   STATUS    RESTARTS   AGE

> qdrouterd-30e3f82-0   1/1     Running   1          4h32m

kubectl logs -f qdrouterd-30e3f82-0

kubectl create configmap spring-boot-camel-amq-config --from-env-file=./application.properties

docker build -t camel-amqp-client -f ./dockerfile_springboot_enmasse_camel_amqp .

kubectl apply -f camel-amqp-client-deployment.yaml

