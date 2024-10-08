FROM bellsoft/liberica-openjdk-alpine-musl:21.0.1
COPY /target/docker-compose-example.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
