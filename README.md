# Java Spring Boot Skeleton
## N-tier layered architecture using DI and best practices

## Includes:
- n-tier layers: Models, Dao, Service & Web API as default layers.
- Dao & Services exposed using interfaces, keeping everything decoupled and clean.
- Mocking for DAO
- application.properties to control each dao (choose mock or real for each data source you have)
- Unit testing against mock
- Swagger (Auto generate docs using javadoc standard) - keep your code documented, document Interfaces & Controller

## TODO:
- Security headers
- Integration Tests

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
