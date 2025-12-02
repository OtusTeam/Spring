#!/bin/bash

../gradlew :api-gateway:build

docker load --input build/jib-image.tar

docker stop api-gateway

docker run --rm -d --name api-gateway \
--memory=512m \
--cpus 1 \
--network="host" \
-v $HOME/.ssh:/root/.ssh \
-e JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80" \
localrun/api-gateway:latest

