#!/usr/bin/env bash
#
#  1. Test eureka server - service-discovery
#  2. Test authorization server - auth-server
#  3. Test ...
#
#
#
###############################################

cd service-discovery
echo "Start test service-discovery"
mvn test

cd ../auth-server
echo "Start test auth-server"
mvn test

cd ../TongquParser
echo "Start test Parser"
mvn test

cd ../gateway
echo "Start test gateway"
mvn test
