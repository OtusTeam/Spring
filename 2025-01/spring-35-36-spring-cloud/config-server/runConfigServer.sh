#!/bin/bash

../gradlew :config-server:build

docker load --input build/jib-image.tar

docker stop config-server

docker run --rm -d --name config-server \
--memory=256m \
--cpus 1 \
--network="host" \
-v $HOME/.ssh:/root/.ssh \
-e JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80" \
localrun/config-server:latest

