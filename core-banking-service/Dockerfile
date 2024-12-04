# Use a lightweight image that contains Java
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the target directory to the working directory
COPY target/corebankingservice-0.0.1-SNAPSHOT.jar /app/corebankingservice.jar

# Expose the port that the application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "corebankingservice.jar"]
