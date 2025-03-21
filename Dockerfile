# Etapa 1: Imagem base Maven com JDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Diretório de trabalho
WORKDIR /app

# Copia apenas o arquivo pom.xml para resolver dependências de forma eficiente
COPY pom.xml .

# Baixa as dependências (vai usar cache até que o pom.xml ou dependências mudem)
RUN mvn dependency:go-offline

# Copia o código fonte do projeto
COPY src /app/src

# Compila e cria o pacote
RUN mvn clean install -DskipTests

# Etapa 2: Imagem base OpenJDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim

# Diretório de trabalho onde o app será executado
WORKDIR /app

# Copia o jar gerado da etapa de build
COPY --from=build /app/target/backEndMobile-*.jar backend-mobile.jar

# Define a variável de ambiente para especificar o arquivo JAR a ser executado
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "backend-mobile.jar"]

# Expor a porta para acesso
EXPOSE 8080
