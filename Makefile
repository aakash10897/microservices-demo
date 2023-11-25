.PHONY: docker_build docker_push clean build

export APP_NAME := social-media-service
export DOCKER_IMAGE_NAME := ${APP_NAME}
PWD := $(shell pwd)

docker_build:
	docker build -t ${DOCKER_IMAGE_NAME}  --build-arg appName=${APP_NAME} -f build/docker/Dockerfile .


clean:
	./mvnw clean

build: clean
	./mvnw package

all: clean build docker_build

docker_push:
	./deployment/publish_to_docker.sh ${APP_NAME} $(IMAGE_TAG) $(DOCKER_REGISTRY)
