FROM maven:3.5-jdk-8-alpine AS build

COPY pom.xml /api-service/pom.xml

COPY ["src", "/api-service/src"]

WORKDIR /api-service

RUN ["mvn", "clean", "package"]

FROM openjdk:8-jre-alpine

COPY --from=build /api-service/target/api-service.jar /
ENTRYPOINT ["java", "-jar", "/api-service.jar"]
EXPOSE 8080


