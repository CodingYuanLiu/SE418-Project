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
junit target/surefire-reports/*.xml

cd ../auth-server
echo "Start test auth-server"
mvn test
junit target/surefire-reports/*.xml
