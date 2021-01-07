# restapi-postgres-crud-tutorial

This is a tutorial project using Spring Boot, PostgreSQL and MyBatis to build a RESTful web service. Please refer to [zenn.dev](https://zenn.dev/numacci/articles/202101_java_restapi-postgres) where the details of this repository explained.

### Prerequisites

* Java Development Kit (JDK) 11
* Docker and docker-compose

### How to Run

1. Start PostgreSQL

```Shell
$ cd /path/to/project/root
$ docker-compose up -d
```

2. Start Spring application by `bootRun`

```Shell
$ ./gradlew bootRun
```
