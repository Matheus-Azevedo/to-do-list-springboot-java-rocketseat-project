# Estágio de compilação
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk

COPY . .

RUN apt-get install -y maven
RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copia o arquivo JAR da etapa de compilação
COPY --from=build /target/todolist-1.0.1-LASTEST.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]