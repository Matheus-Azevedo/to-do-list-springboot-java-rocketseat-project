# Estágio de compilação
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk
RUN apt-get install -y maven

WORKDIR /app

COPY . .
RUN mvn clean install

# Estágio de execução
FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

# Copia o arquivo JAR da etapa de compilação
COPY --from=build /app/target/todolist.jar todolist.jar

ENTRYPOINT ["java", "-jar", "todolist.jar"]