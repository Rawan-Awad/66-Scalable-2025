FROM openjdk:25-ea-4-jdk-oraclelinux9
WORKDIR /app
COPY ./ /app
COPY src/main/java/com/example/data /app/data
ENV DATA_PATH=/app/data/
EXPOSE 8080
CMD ["java","-jar","/app/target/mini1.jar"]