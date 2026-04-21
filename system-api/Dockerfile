# FROM eclipse-temurin:21-jdk AS build
# WORKDIR /app
# COPY . .
# RUN ./mvnw -DskipTests clean package
#
# FROM eclipse-temurin:21-jre-alpine
# WORKDIR /app
# COPY --from=build /app/target/*.jar app.jar
# EXPOSE 8081
# ENTRYPOINT ["java", "-jar", "app.jar"]

# ===============================
# ✅ Stage 1 — Build the JAR
# ===============================
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy root pom.xml and module-specific pom
COPY pom.xml .
COPY system-api/pom.xml system-api/

# Download dependencies — cache layer
RUN mvn -q -e -DskipTests dependency:go-offline -pl system-api -am

# Copy source code
COPY system-api/src system-api/src

# Build only system-api module
RUN mvn -q -DskipTests clean package -pl system-api -am


# ===============================
# ✅ Stage 2 — Runtime image
# ===============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Non-root user (security best practice)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy only the final JAR
COPY --from=build /app/system-api/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]