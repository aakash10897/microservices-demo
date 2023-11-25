#!/usr/bin/env bash

DOCKER_IMAGE_NAME=${1}
DOCKER_IMAGE_TAG=${2:latest}
REGION=${3:-us-east-1}

ACCOUNT=$(aws sts get-caller-identity --query Account --output text)

ECR_REGISTRY="${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com"

aws ecr describe-repositories --region $REGION --repository-names $DOCKER_IMAGE_NAME || aws ecr create-repository --region $REGION --repository-name $DOCKER_IMAGE_NAME

aws ecr get-login-password --region $REGION | docker login --username AWS --password-stdin $ECR_REGISTRY

QUALIFIED_DOCKER_IMAGE_NAME=$ECR_REGISTRY/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

docker tag $DOCKER_IMAGE_NAME $QUALIFIED_DOCKER_IMAGE_NAME
docker push $QUALIFIED_DOCKER_IMAGE_NAME
