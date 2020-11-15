# README #

This is demo application. 

### Technical requirements ###

1. Use Java 1.8, Spring Framework and Maven.
2. Use other Java libraries as needed.
3. Use HSQLDB for storing data. It is ok NOT to persist data across
application launches.
4. Try following all the good principles of writing qualitative and
testable code.
5. Fill in missing requirements as you feel suitable.
6. Include a short README file describing how the application works
and how to build and run the project.

### Before start ###

1. Check java 8 are installed. For this execute following command `mvn spring-boot:run`
2. Check Maven are installed. For this execute following command `java -version`

### Run application ###

1. Unpack project or download from git
2. Go to the root folder
3. Open console
4. Run application: For this execute following command `mvn spring-boot:run`
5. Go to `http://localhost:8080/swagger-ui/` 
6. Login using one of the available users. For this use endpoint `/v1/auth/login` to get token. Then click to the **Authorize** button and copy token from the response to the value field.
Credentials:
 * id: 1000, login: admin, password: admin
 * id: 1001, login: user1, password: password
 * id: 1002, login: user2, password: password
 * id: 1003, login: user3, password: password
 * id: 1004, login: user4, password: password
7. Enjoy application

### TO DO ###

1. Add logging (Done only for one class as example: MetricsServiceImpl.java)
2. Add logging configuration
3. Add test coverage(Done only for one class as example: MetricsServiceImplTest.java)
4. Add JaCoCo plugin to ensure test coverage
5. Finish spring security configuration

### Additional comments about implementation ###
1. Controllers have no interfaces as we must not have abstraction on this level and must not have possibility for one more implementation.
2. Mapping from and to DTO has to be done at controller layer. Service layer always must works only with business model (For more details see: Ross Venables, Eric Evans, John Fuller "Domain-Driven Design: Tackling Complexity in the Heart of Software 1st Edition." Chapter 4). 