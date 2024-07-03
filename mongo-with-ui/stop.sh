#!/usr/bin/env bash

echo Stopping running containers without removing them.

DIR=`dirname "$0"`
docker-compose --file ${DIR}/docker-compose.yml stop