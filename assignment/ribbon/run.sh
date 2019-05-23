#!/bin/bash

cd ./service-discovery
mvn clean package
cd ./target
java -jar ./service-discovery-0.0.1-SNAPSHOT.jar &
cd ../../service-provider
mvn clean package
cd ./target
java -jar ./service-provider-0.0.1-SNAPSHOT.jar &
java -jar ./service-provider-0.0.1-SNAPSHOT.jar --server.port=8910 &
cd ../../service-consumer
mvn clean package
cd ./target
java -jar ./service-consumer-0.0.1-SNAPSHOT.jar &

