FROM maven:3.9.6-eclipse-temurin-21 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
RUN mkdir /app
COPY --from=build /project/target/handler.jar /app/java-application.jar
WORKDIR /app
CMD "java" "-jar" "java-application.jar"