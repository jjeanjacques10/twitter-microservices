# Microservices Architecture of Twitter Service

Repository to recreate the microservices architecture of Twitter. The first service created was:

### Tweet Service

Microservice to record tweets and favorite actions.

## Technologies

- Spring Boot
- Java 17
- Docker
- MySQL
- DynamoDB

## Get Started

Running Databases:

```
docker-compose up -d --build
```

Access DynamoDb Admin: http://localhost:8001/

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

---
Developed by [Jean Jacques](https://github.com/jjeanjacques10)