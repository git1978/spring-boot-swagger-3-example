# Use maven base image (slim variant)
FROM maven:amazoncorretto
WORKDIR /app
COPY . .

RUN mvn clean package
# Use OpenJDK 17 base image (slim variant)
FROM openjdk:17-slim

# Set working directory inside the container
WORKDIR /app

ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context into the container
COPY ${JAR_FILE} /app/api-sp3dk17.jar

# Expose the application port (8080 in this case)
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/api-sp3dk17.jar"]
