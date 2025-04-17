FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app
COPY --from=build /app/attendance-service-logic/target/attendance-service-logic-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
