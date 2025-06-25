#!/bin/bash

../gradlew :eureka-server:build

docker load --input build/jib-image.tar

docker stop eureka-server

docker run --rm -d --name eureka-server \
--memory=512m \
--cpus 1 \
--network="host" \
-v $HOME/.ssh:/root/.ssh \
-e JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80" \
localrun/eureka-server:latest

