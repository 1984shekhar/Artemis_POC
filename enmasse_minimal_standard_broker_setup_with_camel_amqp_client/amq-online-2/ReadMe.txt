minishift tunnel -p amq-online-2

[chandrashekhar@localhost enmasse-0.31.0]$ kubectl get svc|grep messaging
NAME                             TYPE           CLUSTER-IP       EXTERNAL-IP      PORT(S)                                                   AGE
messaging-8f90483                ClusterIP      10.106.54.7      <none>           5672/TCP,5671/TCP,55671/TCP,56671/TCP,55672/TCP,443/TCP   3h54m
messaging-8f90483-external       LoadBalancer   10.96.240.36     10.96.240.36     5671:31538/TCP                                            3h54m
messaging-wss-8f90483-external   LoadBalancer   10.110.190.147   10.110.190.147   443:30231/TCP                                             3h54m
[chandrashekhar@localhost enmasse-0.31.0]$ 

External Client can connect with:
host: 10.96.240.36
port: 5671
