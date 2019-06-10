#!/usr/bin/env bash

# Stop all containers
containers=$(docker ps -aq)
if test -z "$containers"
	then echo "No containers running~"
	else 
		docker stop $containers
		docker rm $containers
fi

# Run on demand

docker run -p 9000:9000 -d --name=eureka-server --net=host\
	summer855/service-discovery
docker run -p 9100:9100 -d --name=auth-server --net=host\
	summer855/auth-server
docker run -p 9200:9200 -d --name=tongqu-parser --net=host \
	summer855/parser
docker run -p 10000:10000 -d --name=api-gateway --net=host \
	summer855/gateway
