./artemis producer --url tcp://localhost:61616 --message-count 10 --destination helloQ
./artemis consumer --url tcp://localhost:61617  --destination helloQ
