
[root@cpandey cpandey]# mount -t nfs -o nfsvers=4,sync,soft,intr,noac,lookupcache=none,timeo=50,retrans=2 [NFS_SERVER_IP]:/home/data/pv9 /home/cpandey/Downloads/sharedeap

[cpandey@cpandey ~]$ mount|grep pv9
[NFS_SERVER_IP]:/home/data/pv9 on /home/cpandey/Downloads/sharedeap type nfs4 (rw,relatime,sync,vers=4.0,rsize=1048576,wsize=1048576,namlen=255,acregmin=0,acregmax=0,acdirmin=0,acdirmax=0,soft,noac,proto=tcp,port=0,timeo=50,retrans=2,sec=sys,clientaddr=[CLIENT_IP],lookupcache=none,local_lock=none,addr=[NFS_SERVER_IP])
[cpandey@cpandey ~]$ 

[cpandey@cpandey ~]$ cd /home/cpandey/Downloads/sharedeap
[cpandey@cpandey sharedeap]$ ls -al
total 86
d---rwxrwx. 3 root          root             53 Oct 18 18:27 .
drwxr-xr-x. 8 cpandey       cpandey        1024 Oct 18 15:27 ..
drwxrwxr-x. 5 cpandey       cpandey          58 Oct 18 18:28 activemq
-rw-rw-r--. 1 rhel-liveuser rhel-liveuser     5 Oct 18 16:37 csp1.txt
-rw-rw-r--. 1 cpandey       cpandey       81692 Oct 18 16:18 csp.txt
[cpandey@cpandey sharedeap]$ 


[cpandey@cpandey bin]$ ./standalone.sh -c standalone-full-ha.xml
[cpandey@cpandey bin]$ ./standalone.sh -c standalone-full-ha.xml -Djboss.socket.binding.port-offset=100

