# Use OpenJDK 17 as a base image
FROM openjdk:17-jdk-slim as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build files
COPY pom.xml .

# Download the Maven dependencies (this step can be cached)
RUN ./mvnw dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Build the application using Maven (package the JAR file)
RUN ./mvnw clean package -DskipTests

# Start a new stage to build the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/api-gateway-*.jar /app/api-gateway.jar

# Expose the port that your API Gateway will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]

