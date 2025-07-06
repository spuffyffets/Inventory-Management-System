# Use base Java 21 JDK image
FROM eclipse-temurin:21-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

# Set working directory
WORKDIR /app

# Copy all source code into the container
COPY . .

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Expose your Spring Boot port (you are using 5050)
EXPOSE 5050

# Run the app
ENTRYPOINT ["java", "-jar", "target/InventoryManagementSystem-0.0.1-SNAPSHOT.jar"]
