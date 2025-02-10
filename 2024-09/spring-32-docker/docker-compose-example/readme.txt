./mvnw package
docker-compose up -d
curl http://localhost:8080/api/persons