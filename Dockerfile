FROM postgres
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB mobileaction

FROM openjdk:17-alpine
ARG JAR_FILE=target/api_deneme-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]