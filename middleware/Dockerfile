# ===============================
# ✅ Stage 1 — Build the JAR
# ===============================
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy root POM and module POM
COPY pom.xml .
COPY middleware/pom.xml middleware/

# Pre-download dependencies (cache)
RUN mvn -q -e -DskipTests dependency:go-offline -pl middleware -am

# Copy source
COPY middleware/src middleware/src

# Build only middleware module
RUN mvn -q -DskipTests clean package -pl middleware -am



# ===============================
# ✅ Stage 2 — Runtime Image
# ===============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Add non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /app/middleware/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]