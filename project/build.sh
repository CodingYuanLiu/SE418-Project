#!/usr/bin/env bash
containers=$(docker ps -aq)
if test -z "$containers"
then echo "No containers running"
else 
	docker container stop $containers
	docker container rm $containers
fi
cd service-discovery
mvn -B -DskipTests clean package
cd ../auth-server/
mvn -B -DskipTests clean package
cd ../TongquParser
mvn -B -DskipTests clean package
cd ../gateway/
mvn -B -DskipTests clean package
