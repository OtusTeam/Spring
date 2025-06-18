#!/bin/bash
./hey -n=1000000 -c=1 -m GET http://localhost:7777/client/info?name=testClientName

