
Using bucket grouping:

<address-setting match="#">
<default-group-rebalance>true</default-group-rebalance>
<default-group-buckets>3</default-group-buckets>



Note: With above configurations there is no need to execute resetAllGroups operation. Re-balancing of groups will happen automatically as soon number of consumer increase or decrease.

1. GroupRebalancer use three groups and RebalancerGroup can be configured with any number of groups. Both can be run individiually. 

2. Have an AMQ 7.4.1 instance running.

3. Run two consumers in two different terminal with command:

./artemis consumer --url tcp://localhost:61616 --user admin --password admin --destination  Q123T_group_rebalance --verbose


./artemis consumer --url tcp://localhost:61616 --user admin --password admin --destination  Q123T_group_rebalance --verbose


4. Run the class GroupRebalancer.java from IDE with program arguments "tcp://localhost:61616". I used eclipse to run.

5. There are three producer instance. producer1(groupA), producer2(groubB), producer3(groupC). messages of Producer1 and messages of Producer3 are consumed by consumer1 and Producer2 is consumed by consumer2.

6. Start third consumer in separate terminal.


./artemis consumer --url tcp://localhost:61616 --user admin --password admin --destination  Q123T_group_rebalance --verbose

7. Again run GroupRebalancer.java. Third consumer won't consume at all. Only 1st and 2nd will keep consuming.

8. Now from Hawtio Console, for queue Q123T_group_rebalance execute JMX operation "resetAllGroups".

9. Again run GroupRebalancer.java, this time all consumer start consuming again



