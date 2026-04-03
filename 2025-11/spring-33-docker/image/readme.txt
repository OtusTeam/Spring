docker build -t my-demo-image:v1 .
docker images | grep my-demo-image
docker run -p:80:80 --rm my-demo-image:v1
docker ps
curl http://localhost