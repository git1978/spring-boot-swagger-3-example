# Swagger 3 and Spring Boot example (with OpenAPI 3)

Document REST API with Swagger 3 in Spring Boot example (follow OpenAPI 3 specification). You will also know several ways to configure Swagger API description and response.



## Add lombok
```
download  le jar lomobk
add in eclipse.ini
-javaagent:/Users/younessabach/Documents/dev/java/mylibs/lombok.jar
```

## Run Spring Boot application
```
mvn spring-boot:run
```


## Create image docker
```
docker build -t image-sp317 .

docker run -p 8080:8080 image-sp317

docker images 
```


## kill port
```
kill -9 <PORT>

```