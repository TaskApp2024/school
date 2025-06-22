# Build stage
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Run stage
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/tamarindtreeschool-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
