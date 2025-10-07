FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Assumindo que o nome do seu JAR segue este padrão
COPY build/libs/customer-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]