#Steps:

##Project Starter:

- 1. Check first in terminal first your Java version. $ java -version
- 2. ATM latest is Java version 17.0.1
- 3. Navigate to Spring Initializr and create a new Spring Boot project.
- 4. Choose Spring 2.6.0.RELEASE and Java 17 and Maven
- 5. Add the following dependencies:

  - Spring Boot DevTools,
  - Lombok,
  - Spring Web,
  - Rest Repositories,
  - Spring Security,
  - Spring Data JPA,
  - H2 Database,
  - PostgresSQL Driver
- 6. Download the project and open in your IDE.

#### POM.XML FILE

- Add javax.validation under the dependencies in the pom.xml file.

Ex:

<dependency>
<groupId>javax.xml.bind</groupId>
<artifactId>jaxb-api</artifactId>
<version>2.4.0-b180830.0359</version>
</dependency>

2. Add the spring-boot-starter-validation under the dependencies in the poml.xml file.
   Ex:   <!--Spring Boot-->
   <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
   </dependency>

####Creating Packages
NOTE: com.blogposttodo.blogposttodo is the name of the project.

1. Inside the project (see NOTE),  create a new package and name it user
   ex: com.blogposttodo.blogposttodo.user
2. Inside the com.blogposttodo.blogposttodo.user package, create the ff 6 packages:
   controller, service, repository, dto, entity, contract.
   ####Creating User Entities

- Under the entity package, create a new file and name it UserEntity.java.

####Creating User DTO

- Under the dto package, create a new file and name it UserDTO.java.

####Creating User Contract

- Under the contract package, create a new file and name it UserContract.java.

####Creating User Repository

- Under the repository package, create a new file and name it UserRepository.java.

####Creating Exception Package

- Under the exception package, create a new file and name it NotFoundException.java.
- Inside the NotFoundException.java file, create the following code:

public class NotFoundException extends RuntimeException {
public NotFoundException(String message) { super(message); } }

#### Creating User Service

- Under the service package, create a new file and name it UserService.java.
  ####Adding ModelMapper and MapStruct dependencies (one-time setup)
- Inside the pom.xml file, add the following dependencies:
- ModelMapper,
- MapStruct

#### Adding Logging Package (one-time setup)

- Inside the pom.xml file, add the following dependencies:
  - Spring Boot Started log4js

#### Adding Log4js2-spring.xml in the main/resource folder

#### Updating Application Properties

- Update application.properties file by adding h2 inmemory database for proof of concept.
- Replace it with the text below:

```manifest.yml
# application properties for inmemory development
spring.main.allow-bean-definition-overriding=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
app.clientUrl=${CLIENT_URL}
```

#### Adding Configurations (no-user and no-auth yet)

- Inside the pom.xml file, add the following dependencies:
  - Spring Boot Configuration processor

#### Creating a config package:

- Inside the project (see NOTE),  create a new package and name it config

#### Adding files in the config package:

- Add a new file and name it:  CorsConfig.java. -- but don't put anything related to security yet.
- Add a new file and name it:  AppProperties.java.
- Add a new file and name it:  ModelMapperConfig.java.
- Add a new file and name it: SecurityConfig. ---- but don't put anything related to security yet.

#### Adding Controller (no-auth yet)

- Create a new package inside the project and name it: UserController.java.

###RUN the application to check if you've successfully sending the endpoint. In the localhost, an empty array should appear

since we don't have data yet.

#### Connecting to Postgres

- For now, we comment out the h2 file in the application.properties since we alreayd a POF when we're able to send an endpoint.
- In the application.xml, copy the text below:

```
spring.main.allow-bean-definition-overriding=true
# jdbc:postgresql://localhost:5432/springDevDb for the value in environment variable DATABASE_URL
spring.datasource.url=${DATABASE_URL}
# postgres for the value in environment variable DATABASE_USERNAME
spring.datasource.username=${DATABASE_USERNAME}
#spring.datasource.username=
# pass for the value in environment variable DATABASE_PASSWORD
spring.datasource.password=${DATABASE_PASSWORD}
#spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

NOTE:

Navigate to -> Run -> EditConfigurations -> Click on $ of Working Directory and browse to your root package or working directory of your app.

Make sure your Docker client is running.

Open the PGAdmin and make sure you've created a database. Take note of your database name and password.

#### Seeding the Database (for FOC)

- In the root project, create a new file and name it DataLoader


#### Automated Testing

- Add the Javafaker dependency in the pom.xml
