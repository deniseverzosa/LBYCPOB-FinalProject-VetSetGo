# ---------- Build stage ----------
# Full JDK + Maven, used to compile and package the jar.
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml ./
RUN mvn -B dependency:go-offline

# Copy source code and build executable jar
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---------- Runtime stage ----------
# Lightweight JRE image for running the app
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Run as a non-root user for security
RUN useradd --system --create-home appuser
USER appuser

# Copy the compiled JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]