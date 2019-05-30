#!/usr/bin/env bash
#
#  1. deliver and run service-discovery
#
#
#
#
###############################################

cd service-discovery
mvn docker:build
cd ../auth-server
mvn docker:build

cd ../TongquParser
mvn docker:build