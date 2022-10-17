# Java Spring Boot Skeleton
## N-tier layered architecture using DI and best practices

## Includes:
- n-tier layers: Models, Dao, Service & Web API as default layers.
- Extra layer: security. Seperated from services to be able to mock security
- Dao & Services exposed using interfaces, keeping everything decoupled and clean.
- Mocking for DAO
- application.properties to control each dao (choose mock or real for each data source you have)
- Unit testing with JUnit
- Swagger UI (Auto generate docs using javadoc standard) - keep your code documented, document Interfaces & Controller
- Secured Routes With Roles

## Coming up features:
- Security headers and api throttle
- Integration Tests
- Add docker compose

## Open issues
- Post, Put, Remove - test and make it work better
- Add swagger schema examples
- Add unit tests for the brand-new security service

# Prerequisite
- Make sure you have java installed
- Install JDK 19

# Usage
1. Clone project to your local computer
2. Use your preffered IDE to open the skeleton
3. Edit according to needs (or use example to check it out)
4. Run using the pre-build run configuration under .run or using the command:
```bash
./mvnw spring-boot:run
```

5. API is available on `http://localhost:8080` by default
6. Swagger access is granted by `http://localhost:8080/swagger`

# Build
* Run `./mvnw package` inside the root folder to build executable jar
* Build output is under target/your-build-name.SNAPSHOT.jar
# Test executable
* Run 
```
java -jar build-output-name.jar
``` 
* To add profiles add flag: `-Dspring.profiles.active=prod`
* Example with profile: 
```
 ava -jar -Dspring.profiles.active=prod build-output-name.jar
