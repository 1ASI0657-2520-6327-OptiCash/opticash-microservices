FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar built by Maven/Gradle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# To document the internal port, but it's optional
# EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
