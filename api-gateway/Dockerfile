FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]