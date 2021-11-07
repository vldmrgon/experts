## Development process:

#### 1) Create project via Spring Boot, add all dependencies. To use Gradle builder
#### 2) Run two mysql databases in docker container
docker run --name db1 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db1 -e MYSQL_USER=myDb -e MYSQL_PASSWORD=myDb -p 3306:3306 mysql:8.0.26

docker run --name db2 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db2 -e MYSQL_USER=myDb -e MYSQL_PASSWORD=myDb -p 3307:3306 mysql:8.0.26
#### 3) Connect to databases via plugin Database
#### 4) Write spring configuration for connection to databases
#### 5) Add technologies jpa and hibernate, after to create Entity table
#### 6) Add table schema to flyway
#### 7) 