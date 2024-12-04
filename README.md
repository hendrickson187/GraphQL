# Introduction to the GraphQL Template

This repository contains a template to show how to set-up a GraphQL project. 

## How to start the Server in the IDE

* Run class `src/main/java/de/thws/fiw/backendsystems/templates/Main.java`.
* Two Java servlets are registered: `HelloServlet` is to populate the database. Call `http://localhost:8080/filldatabase` in a Web browser.  
* The second servlet is available under `http://localhost:8080/graphql` and is the entry point for GraphQL queries. 
* Check this by using Postman to send a GraphQL query as described below.

## Using Postman to send GraphQL requests

Start a `POST` request to URL `http://localhost:8080/graphql` with request body:

```
{
     persons { firstName lastName }
}
```

The server should respond with status `200` and an empty response like this, if you have not populated any
data to the database.

```
{
    "data": {
        "persons": [],
    }
}
```

## How to start the integration tests

* You must use the project configuration `pom-docker.xml`.
* Execute `mvn -f pom-docker.xml clean verify` to start the server and execute test cases of the integration phase. 
* This template does not show how to implement integration tests for GraphQL.
