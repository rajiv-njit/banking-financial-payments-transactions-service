# Use a lightweight image that contains Java
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the target directory to the working directory
COPY target/paymenthandlingservice-0.0.1-SNAPSHOT.jar /app/paymenthandlingservice.jar

# Expose the port that the application runs on
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "paymenthandlingservice.jar"]
