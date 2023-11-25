#!/usr/bin/env bash

ENV=${1}
DOCKER_IMAGE_NAME=${2}
SHA=${3}
REGION=${4:-us-east-1}

RELATIVE_DIR="$(dirname "${BASH_SOURCE[0]}")"

ACCOUNT=$(aws sts get-caller-identity --query Account --output text)

ECR_REGISTRY="${ACCOUNT}.dkr.ecr.${REGION}.amazonaws.com"

IMAGE_TAG=$SHA

cd ${RELATIVE_DIR}

if [ -z "$SHA" ]
then
    echo "Empty SHA. Finding SHA from stage."
    IMAGE_TAG=$(grep -Eo "${ECR_REGISTRY}/${DOCKER_IMAGE_NAME}:[a-zA-Z0-9]+" ../gitops/src/${DOCKER_IMAGE_NAME}/stage.yaml | head -1 | cut -d : -f 2 | xargs)
fi

sed -i "s#${ECR_REGISTRY}/${DOCKER_IMAGE_NAME}:[a-zA-Z0-9]\\+#${ECR_REGISTRY}/${DOCKER_IMAGE_NAME}:$IMAGE_TAG#" ../gitops/src/${DOCKER_IMAGE_NAME}/${ENV}.yaml

cd ../gitops/
git config user.email "s-eg-sem-eng@expediagroup.com"
git config user.name "s-eg-sem-eng"
git add .
git commit -m "Upgrade ${DOCKER_IMAGE_NAME} in $ENV" -m "To $IMAGE_TAG"
git push origin master