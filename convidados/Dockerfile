FROM maven:3.9.11 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /app
COPY --from=build ./build/target/*.jar ./convidados.jar
ENTRYPOINT java -jar convidados.jar
