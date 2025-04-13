# Step 1: Build Java app with Maven
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Create app folder in the container
WORKDIR /app

# Copy everything from your project folder into the container
COPY . .

# Build the project using Maven (ignores tests for speed)
RUN mvn clean package -DskipTests

# Step 2: Run it using a lightweight Java runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built jar file from previous step
COPY --from=builder /app/target/subscriber-1.0-SNAPSHOT.jar app.jar

# Run the jar when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
