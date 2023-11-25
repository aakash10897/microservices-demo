#!/usr/bin/env bash

DOCKER_IMAGE_NAME=${1}
DOCKER_IMAGE_TAG=${2:latest}
DOCKER_REGISTRY=${3:docker}

echo $DOCKER_REGISTRY

QUALIFIED_DOCKER_IMAGE_NAME=$DOCKER_REGISTRY/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

docker tag $DOCKER_IMAGE_NAME $QUALIFIED_DOCKER_IMAGE_NAME
docker push $QUALIFIED_DOCKER_IMAGE_NAME