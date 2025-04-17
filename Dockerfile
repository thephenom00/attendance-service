FROM maven:3.8.5-openjdk-22 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-slim
WORKDIR /app
COPY --from=build /app/attendance-service-logic/target/attendance-service-logic-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
