# Use OpenJDK 17 base image (Alpine-based)
FROM openjdk:17-jdk-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/api-sp3dk17.jar

# Set working directory
WORKDIR /opt/app

# Copy the JAR file from the build context to the container
COPY ${JAR_FILE} api-sp3dk17.jar

# Set the command to run the JAR file
ENTRYPOINT ["java", "-jar", "api-sp3dk17.jar"]

# Expose the application port (8080 in this case)
EXPOSE 8080

# Optional: specify a default command to run the application (could also be done in the entry point)
# CMD ["java", "-jar", "api-sp3dk17.jar"]


## sudo docker run -p 8080:8080 -t docker-spring-boot:1.0
## sudo docker run -p 80:8080 -t docker-spring-boot:1.0
## sudo docker run -p 443:8443 -t docker-spring-boot:1.0