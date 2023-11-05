# Reactive Spring Boot with WebFlux and R2DBC


## Requirements

* x86-64
* Linux/Unix
* Docker
* JDK 17


## Startup

The script "up" creates our database container and starts up our application:
```
1. docker-compose -f db/cars/docker-compose.yml up -d
2. mvn spring-boot:run
```


## Shutdown

The script "down" removes our database container:
```
1.docker-compose -f db/cars/docker-compose.yml down
```

