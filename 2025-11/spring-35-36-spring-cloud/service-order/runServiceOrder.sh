#!/bin/bash

../gradlew :service-order:build

docker load --input build/jib-image.tar

docker stop service-order-1
docker stop service-order-2

docker run --rm -d --name service-order-1 \
--memory=256m \
--cpus 1 \
--network="host" \
-e JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80" \
-e SPRING_APPLICATION_INSTANCE_ID="i1" \
-e SERVER_PORT=8091 \
localrun/service-order:latest

docker run --rm -d --name service-order-2 \
--memory=256m \
--cpus 1 \
--network="host" \
-e JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80" \
-e SPRING_APPLICATION_INSTANCE_ID="i2" \
-e SERVER_PORT=8092 \
localrun/service-order:latest

