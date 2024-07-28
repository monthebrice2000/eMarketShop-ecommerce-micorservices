# #!/usr/bin/bash

# set -xe

# PROJECT_DIR=`dirname $(pwd)`

# # Define the service name and version
# SERVICES=("user-service" "shipping-service" "product-service" "payment-service" "order-service" "favourite-service" "cloud-config")
# VERSION="0.0.1"
# SPRING_PROFILES_ACTIVE="dev"
# SPRING_CONFIG_LOCATION=""

# if [ $# -ge 2 ]; then
#     case "$1" in
#         "--service-name") 
#             SERVICES=("$2");;
#         *)
#             echo "Invalid argument: $1"
#             echo "Usage: $0 --service-name <service-name>"
#             exit 1
#             ;;
#     esac    
# fi

# for SERVICE in "${SERVICES[@]}"
# do
#     cd ${PROJECT_DIR}/${SERVICE}

#     # Run Maven clean and package
#     echo "Building the project with Maven..."
#     mvn clean package

#     # Check if the build was successful
#     if [ $? -eq 0 ]; then
#         echo "Maven build successful."

#         IMAGE_NAME="${REPO_CONTAINER_IMAGE}/${SERVICE}"
#         # Check if the Docker image already exists
#         if -f "./target/${SERVICE}-${VERSION}.jar"; then
#             java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Dspring.config.location=${SPRING_CONFIG_LOCATION} -jar ./target/${SERVICE}-${VERSION}.jar
#         fi
        
#         # Check if the lauch failed
#         if [ $? -nq 0 ]; then
#             echo "Lauch failed"
#             exit 1            
#         fi
#     else
#         echo "Maven build failed."
#         exit 1
#     fi
# done

