# Estágio de compilação
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk
RUN apt-get install -y maven

WORKDIR /app

COPY . .

# Estágio de execução
FROM openjdk:17-jdk-slim

RUN mvn clean install

EXPOSE 8080

# Copia o arquivo JAR da etapa de compilação
COPY --from=build /target/todolist-1.0.1-LASTEST.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]