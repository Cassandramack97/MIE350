# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the backend source code into the container
COPY ./target/*.jar app.jar

# Expose the port for the application
EXPOSE 8081

# Run the JAR file
CMD ["java", "-jar", "app.jar"]
