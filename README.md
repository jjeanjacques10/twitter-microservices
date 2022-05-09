# Tweet Service

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

## Tables

- tweet (MySQL)
- tb_favorite_tweets (Dynamo)

---
Developed by [Jean Jacques](https://github.com/jjeanjacques10)