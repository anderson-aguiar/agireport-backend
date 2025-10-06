FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/history-service-0.0.1-SNAPSHOT.jar app.jar
COPY certs ./certs

EXPOSE 8084

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]