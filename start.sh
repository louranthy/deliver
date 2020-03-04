mvn clean install -DskipTests
docker build -t delivery .
docker build -f Dockerfile-mysql -t delivery-sql .
docker-compose up
