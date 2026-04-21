# ===============================
# ✅ Stage 1 — Build the JAR
# ===============================
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY bff-gateway/pom.xml bff-gateway/

RUN mvn -q -e -DskipTests dependency:go-offline -pl bff-gateway -am

COPY bff-gateway/src bff-gateway/src

RUN mvn -q -DskipTests clean package -pl bff-gateway -am


# ===============================
# ✅ Stage 2 — Runtime Image
# ===============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

COPY --from=build /app/bff-gateway/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]