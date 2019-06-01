#!/usr/bin/env bash

cd service-discovery
mvn -B -DskipTests clean package
cd ../auth-server/
mvn -B -DskipTests clean package
cd ../TongquParser
mvn -B -DskipTests clean package
cd ../gateway/
mvn -B -DskipTests clean package
