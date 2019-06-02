#!/usr/bin/env bash

# Stop all containers
containers=$(docker ps -aq)
if test -z "$containers"
	then echo "No containers running~"
	else 
		docker stop $(docker ps -aq)
		docker rm $(docker ps -aq)
fi

# Run on demand

docker run -p 9000:9000 -d --name=eureka-server \
	summer855/service-discovery
docker run -p 9100:9100 -d --name=auth-server \
	--link=eureka-server summer855/auth-server
docker run -p 9200:9200 -d --name=tongqu-parser \
	--link=eureka-server --link=auth-server summer855/parser
docker run -p 10000:10000 -d --name=api-gateway \
	--link=eureka-server summer855/gateway
