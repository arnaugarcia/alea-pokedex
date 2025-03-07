# Use the official OpenJDK image as a base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build context
COPY build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080:8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
