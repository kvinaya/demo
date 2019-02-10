# demo
This is a Demo application demonstrating the User CRUD operations exposed as an API. 
- Implemented using Spring Boot.
- H2 In-memory Database
- JUnit test cases (Spríng-boot-starter-test)
- Contains Postman request collection export


To test this API, please download/clone and create a Maven Project in IDE(Choosing the option - create from existing Repository)

The main methos  is in the Class: demo/src/main/java/com/vk/UserApp.java 

The application will run on port 8080.

The URL http://localhost:8080/users should give back the json object containing 2 users.

The Postman request collection is also added to this project at the root level: 
**WebAPI_postman_collection.json**
One can import this collection into Postman tool and run the tests.

Test cases have been written in the application.
When you do a mvn clean install, the test cases should execute.
