<br />
<p align="center">
  <a href="https://github.com/jjeanjacques10/twitter-microservices">
    <img src="./files/twitter-logo.png" width="100px" alt="Logo">
  </a>

<h3 align="center">Microservices Architecture of Twitter Service</h3>
  <p align="center">
    Repository to recreate the microservices architecture of Twitter. 
    <br />
    <a href="https://github.com/jjeanjacques10/twitter-microservices"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/jjeanjacques10/twitter-microservices/issues">Report Bug</a>
    ·
    <a href="https://github.com/jjeanjacques10/twitter-microservices/issues">Request Feature</a>
  </p>
</p>

### Tweet Service

The first service created was Tweet Service. Microservice to record tweets and favorite actions.

### User Service

Manage users profiles to control authentication and authorization actions.

### Eureka Service

Service Discovery, it's where I'll register my microservices.

### Gateway Service

Gateway provides a flexible way of routing requests.

### Timeline Service

Service to listener new tweets created and generate users' timeline in Redis

## Technologies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- Java 17
- Kotlin
- Docker
- MySQL
- Redis
- Localstack
    - DynamoDB
    - SQS

## Get Started

Running Databases:

``` bash
docker-compose up -d --build
```

- Access DynamoDb Admin: http://localhost:8001/
- Access Database Tweet: http://localhost:3306/
- Access Database User: http://localhost:3307/

Running applications:

``` bash
## == Build ==
run.sh 
# or 
run.bat

## == Running ==
docker-compose -f docker-compose-app.yml up --build -d
```

- Gateway Service: http://localhost:9002
    - Tweet Service: [/tweet](http://localhost:9002)
    - User Service: [/user](http://localhost:9002)
    - Timeline Service: [/timeline](http://localhost:9002)
- Eureka Service: http://localhost:9000

### Postman

Access postman collection **[here](./files/Twitter.postman_collection.json)**

<img src="./files/postman.png">

## Tables

- tweet (MySQL)
- tb_favorite_tweets (Dynamo)
    - Table Schema: [environment/dynamodb/tables.json](./environment/dynamodb/tables.json)

## Functional Requirements

The reference to functional requirements is this
article: [Design Twitter — Microservices Architecture of Twitter Service](https://thinksoftware.medium.com/design-twitter-microservices-architecture-of-twitter-service-996ddd68e1ca)

1. The users can post new messages or tweets (this is a write operation).

2. The size of a tweet is 140 characters at most.

3. A user can delete his tweets but not update/edit his posted tweets (this is a write operation).

4. The users can mark favorite tweets (write operation).

5. Each user have a personalized timeline with must recent tweets

## Diagrams

<p align="center">
<img src="./files/Microservices Diagram.jpg" width="700px" alt="Logo">
</p>

## Contact

[Linkedin](https://www.linkedin.com/in/jjean-jacques10) | [Github](https://github.com/jjeanjacques10/)
| [Medium](https://jjeanjacques10.medium.com/) | [Email](mail:jjean.jacques10@gmail.com)

Project
Link: [https://github.com/jjeanjacques10/twitter-microservices](https://github.com/jjeanjacques10/twitter-microservices)

---
Developed by [Jean Jacques Barros](https://github.com/jjeanjacques10)