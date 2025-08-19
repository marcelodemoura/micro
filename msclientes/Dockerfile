FROM maven:3.9.7-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

ARG EUREKA_SERVER=localhost

ENTRYPOINT ["java", "-jar", "app.jar"]
