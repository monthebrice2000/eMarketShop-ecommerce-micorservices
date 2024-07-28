#!/usr/bin/bash

set -e

# Define the service name and version
REPO_CONTAINER_IMAGE="emarketshop"
SERVICES=("user-service" "shipping-service" "product-service" "payment-service" "order-service" "favourite-service" "cloud-config" "service-discovery")
VERSION="0.0.1"

for SERVICE in "${SERVICES[@]}"
do
    IMAGE_NAME="${REPO_CONTAINER_IMAGE}/${SERVICE}"
    # Check if the Docker image already exists
    if docker images | grep -q "${IMAGE_NAME}"; then
        echo "Deleting old Docker image: ${IMAGE_NAME}:${VERSION}"
        docker rmi -f "${IMAGE_NAME}:${VERSION}"


        # Check if the Docker build was successful
        if [ $? -eq 0 ]; then
            echo "Docker image ${IMAGE_NAME} remove successfully."
        else
            echo "Docker image ${IMAGE_NAME} remove failed."
            exit 1
        fi

    else
        echo "Docker Image ${IMAGE_NAME} doesn't exist"
        exit 1
    fi
done

