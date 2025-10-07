FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]