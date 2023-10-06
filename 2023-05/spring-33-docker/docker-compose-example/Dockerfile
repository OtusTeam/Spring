FROM openjdk:11-jre-slim
COPY /target/docker-compose-example.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
