# Use OpenJDK 17 as base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper and pom.xml first for caching dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (this helps speed up subsequent builds)
RUN ./mvnw dependency:go-offline -B

# Copy the entire project
COPY src ./src

# Package the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Expose port your Spring Boot app runs on
EXPOSE $PORT

# Set environment variables for Spring Boot
ENV JAVA_OPTS=""

# Run the Spring Boot app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar target/jobConnect-0.0.1-SNAPSHOT.jar"]


