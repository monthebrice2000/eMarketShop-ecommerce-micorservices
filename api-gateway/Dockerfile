
#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

# Add Maintainer Info
LABEL maintainer="Brice Monthe"

# The application's version file
ARG PROJECT_VERSION=0.0.1
ARG WKD=/home/app

RUN mkdir -p ${WKD}

WORKDIR ${WKD}

# Add the application's jar to the container
ADD ./target/api-gateway-${PROJECT_VERSION}.jar app.jar

#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ${WKD}/app.jar)

#stage 2
#Same Java runtime
FROM openjdk:17-jdk-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG WORKDIR=/home/app
ARG DEPENDENCY=${WORKDIR}/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=build ${WORKDIR}/app.jar /app

ENV SPRING_PROFILES_ACTIVE dev

#execute the application
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.emarketshop.payment_service.PaymentServiceApplication"]

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/app/app.jar"]