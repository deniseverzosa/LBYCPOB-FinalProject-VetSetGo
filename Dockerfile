# ---------- Build stage ----------
# Full JDK + Maven, used only to compile and package the jar.
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml first for dependency caching
COPY .mvn/ .mvn/
COPY mvnw mvnw.cmd pom.xml ./
RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

# Copy source code and build executable jar
COPY src ./src
RUN ./mvnw -B clean package -DskipTests

# ---------- Runtime stage ----------
# JRE only for a smaller, secure container image
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Run as non-root user for security
RUN useradd --system --create-home appuser
USER appuser

# Copy the compiled JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Render assigns a dynamic port at runtime via $PORT
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]