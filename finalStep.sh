#!/bin/bash
SERVICE_NAME="adl-gallery-ws-service"

TASK_FAMILY="deploysimpleserver"

TASK_REVISION=`sudo aws ecs describe-task-definition --task-definition deploysimpleserver | egrep "revision" | tr "/" " " | awk '{print $2}' | sed 's/"$//'`

#DESIRED_COUNT=`sudo aws ecs describe-services --cluster twoinstance1 --services adl-gallery-ws-service | egrep "desiredCount"  | tr "/" " "| awk '{print $2}'| sed 's/,$//'| sed -n 1p`


sudo aws ecs update-service --cluster twoinstance1 --service ${SERVICE_NAME} --task-definition ${TASK_FAMILY}:${TASK_REVISION} --desired-count 2