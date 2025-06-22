# ----- Build Stage -----
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# ----- Run Stage -----
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/tamarindtreeschool-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
