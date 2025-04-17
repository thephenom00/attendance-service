FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
ENV MANAGEMENT_METRICS_BINDERS_PROCESSOR_ENABLED=false
COPY --from=build /app/attendance-service-logic/target/attendance-service-logic-1.0.0.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
