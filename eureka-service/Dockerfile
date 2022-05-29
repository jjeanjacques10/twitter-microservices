FROM openjdk:17-alpine as eureka-service

ARG JAR_FILE=target/eureka-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]

EXPOSE 9000