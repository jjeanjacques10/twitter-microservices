FROM openjdk:17-alpine as tweet-service

ARG JAR_FILE=target/tweet-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]

EXPOSE 8080