FROM maven:3.8.1-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package


FROM openjdk:11.0.8-slim
COPY images /images
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","app.jar"]
