FROM openjdk:11 as timeline-service
USER root

ARG JAR_FILE=target/timeline-service-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]

EXPOSE 8083