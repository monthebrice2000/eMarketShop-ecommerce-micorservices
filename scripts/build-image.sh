#!/usr/bin/bash

set -xe

PROJECT_DIR=`dirname $(pwd)`

# Define the service name and version
REPO_CONTAINER_IMAGE="emarketshop"
SERVICES=("user-service" "shipping-service" "product-service" "payment-service" "order-service" "favourite-service")
VERSION="0.0.1"

for SERVICE in "${SERVICES[@]}"
do
    cd ${PROJECT_DIR}/${SERVICE}

    # Run Maven clean and package
    echo "Building the project with Maven..."
    mvn clean package

    # Check if the build was successful
    if [ $? -eq 0 ]; then
        echo "Maven build successful."

        IMAGE_NAME="${REPO_CONTAINER_IMAGE}/${SERVICE}"
        # Check if the Docker image already exists
        if docker images | grep -q "${IMAGE_NAME}"; then
            echo "Deleting old Docker image: ${IMAGE_NAME}:${VERSION}"
            docker rmi -f "${IMAGE_NAME}:${VERSION}"
        fi
        
        # Build the Docker image
        echo "Building the Docker image..."
        docker build --build-arg="PROJECT_VERSION=${VERSION}" -t ${IMAGE_NAME}:${VERSION} .
        
        # Check if the Docker build was successful
        if [ $? -eq 0 ]; then
            echo "Docker image built successfully."
        else
            echo "Docker image build failed."
            exit 1
        fi
    else
        echo "Maven build failed."
        exit 1
    fi
done

