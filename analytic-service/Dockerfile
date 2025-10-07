FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
# Assumindo que o nome do seu JAR segue este padrão
COPY build/libs/analytic-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]