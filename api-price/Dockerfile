
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
WORKDIR /app/api-price
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/api-price/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]