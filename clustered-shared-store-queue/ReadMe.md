mount client:

mount -t nfs -o nfsvers=4,sync,soft,intr,noac,lookupcache=none,timeo=50,retrans=2 192.168.1.9:/cpandey/ /home/cpandey/NotBackedUp/Development/NFS_CLIENT_MOUNT
