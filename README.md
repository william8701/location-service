# Location Service

## Prerequisites
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/) 
- [JDK 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

## Development

Setup database by running 

```
docker-compose -f ./etc/database/docker-compose.yml up -d
```

To run the project

```
mvn spring-boot:run  
```

To view available endpoints

```
http://localhost:8080/swagger-ui/
```

Default username and password 

    username: locationservice
    password: locationservice
