# Spring Boot Rest API Template

## Overview
- Restful API that performs `GET` `POST` `PATCH` & `DELETE`.
- To be used as future reference.
- [Unit tests](https://github.com/TonyCallaghan/java-personal/blob/main/API%20Template/src/test/java/com/tonycallaghan/restfulAPI/ApplicationTests.java) for additional coverage.
  
## Resources
 - [Spring Boot 3.0.8.](https://start.spring.io/)
 - JDK 17.
 - [Maven.](https://maven.apache.org/download.cgi)
 - [Postman](https://www.postman.com/) for testing.

## To Run
Building the project and running tests:
```bash
mvn clean install 
```
Starting the server (http://localhost:8080/product)
```
mvn spring-boot:run
```
Executing all tests:
```
mvn test
```

## API Endpoints

|            |  Commands                                                |
|------------| ---------------------------------------------------------|
| - `GET`    | `http://localhost:8080/product` (All products)           |
| - `GET`    | `http://localhost:8080/product/{id}` (individual product)|
| - `DELETE` | `http://localhost:8080/product/{id}`                     |
| - `POST`   | `http://localhost:8080/product` (json)                   |
| - `PATCH`  | `http://localhost:8080/product` (json)                   |

e.g:
```json
{
    "description": "New Product",
    "price": 201.200
}
```
