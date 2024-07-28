#!/usr/bin/bash

set -xe

PROJECT_DIR=$(dirname $(pwd))

# Define the service name and version
DOCKER_REGISTRY="819XXXXXX43.dkr.ecr.us-east-2.amazonaws.com"
REPO_CONTAINER_IMAGE="${DOCKER_REGISTRY}/emarketshop"
SERVICES=("user-service" "shipping-service" "product-service" "payment-service" "order-service" "favourite-service" "cloud-config" "service-discovery" "api-gateway")
VERSION="0.0.1"

if [ $# -ge 2 ]; then
    case "$1" in
    "--image-name")
        SERVICES=("$2")
        ;;
    *)
        echo "Invalid argument: $1"
        echo "Usage: $0 --image-name <service-name>"
        exit 1
        ;;
    esac
fi

for SERVICE in "${SERVICES[@]}"; do

    IMAGE_NAME="${REPO_CONTAINER_IMAGE}/${SERVICE}"
    # Check if the Docker image already exists
    if ! (docker images | grep -q "${IMAGE_NAME}"); then
        echo "Image doesn't exit: ${IMAGE_NAME}:${VERSION}"
        exit 1
    fi

    # Build the Docker image
    echo "Pushing the Docker image..."
    docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:${VERSION}

    # Check if the Docker build was successful
    if [ $? -eq 0 ]; then
        echo "Docker image pushed successfully."
    else
        echo "Docker image pushed failed."
        exit 1
    fi
done
