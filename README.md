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
- Inside the Test package -> create a new package and name it resources
- Inside the resource package -> create a new file named application.properties. Copy paste the code below:

```

# application properties for integration tests
springdoc.swagger-ui.enabled=false
spring.main.allow-bean-definition-overriding=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
app.clientUrl=http://localhost:4200
app.jwtSecret=Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=
app.version=1.0.0
app.name=SuperheroesApplication
app.description=just another Spring Boot REST API application
```

- Inside the Test package -> and inside your root project test package, create a new package and name it user. (test/java/com.blogposttodo.blogposttodo/user)
- Inside the user package -> create two folders: controller and service
- In the controller -> add a new file and name it userControllerTest
- In the service -> add a new file and name it userServiceTest

###REST-Client for Postman/Insomnia replacement

- Create REST-Client folder in the root directory
- Add user.http file
- send/trigger GET http://localhost:8080/api/v1/users  -> [] empty array should be be the response
- send/trigger GET BY ID: http://localhost:8080/api/v1/users/{id}
- send/trigger POST http://localhost:8080/api/v1/users
- send/trigger PUT http://localhost:8080/api/v1/users/{id}

##SWAGGER UI or OPEN API

Add springdoc-openapi-ui and springdoc-openapi-data-rest in the pom.xml

Write SwaggerConfig configuration inside the config package for custom Swagger UI

Update the application.properties with app.version, app.name, and app.description

Run the app and then go to this link:

* [http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/)

In the Edit Configugations:

###USER LOGIN

_ Create a new package named userlogin in the com.blogposttodo.blogposttodo

_ Inside the userlogin package, create the following packges: entity, dto, repository, service, controller

###JWT

_ Create a jwt package under the .comblogposttodo.blogposttodo

_ Create a models package under the jwt


* write UserPrincipal class, jwt/models/UserPrincipal.java
* write AuthenticationRequest class, jwt/models/AuthenticationRequest.java
* write AuthenticationResponse class, jwt/models/AuthenticationResponse.java
* create services package under the jwt folder
* add jjwt-api, jjwt-impl, and jjwt-jackson in the pom.xml
* write an application.yml that has jwt secret in the resources
* write AppProperties configuration in config package. For jwt only for now.
* write PasswordConfig configuration in config package.
* update the SecurityConfig with userDetailsService, jwtFilter, configure, and http.addFilterBefore
* create filters package under the jwt folder
* write JwtFilter class, jwt/filters/JwtRequestFilter.java
* create controllers package under the jwt folder
* write AuthenticateController class, jwt/controllers/AuthenticateController.java
* write an auth.http file inside the REST-Client folder
* write a users.http file inside the REST-Client folder
* run the application
* trigger the POST [http://localhost:8080/register](http://localhost:8080/register)
* check the response if an object is returned
* trigger the POST [http://localhost:8080/authenticate](http://localhost:8080/authenticate)
* check if a token is returned

## Protected endpoint

* add @PreAuthorize in the AntiHeroController
* send a get request to [http://localhost:8080/api/v1/users]
* check if the response is 403 Forbidden
* login as a user by,
* triggering the POST [http://localhost:8080/register](http://localhost:8080/register)
* triggering the POST [http://localhost:8080/authenticate](http://localhost:8080/authenticate)
* use the token in the Authorization header of REST-Client/anti-heroes.http
* send another get request to [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/users)
* check if the response is 200 OK []

## Redis Caching

* add spring-data-redis and jedis in the pom.xml
* write RedisConfig configuration in config package.
* update the SuperheroesApplication class with @EnableCaching
* run the application

## Health Checks

* add spring-boot-starter-actuator in the pom.xml
* write health.http file inside the REST-Client folder
* trigger GET [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) to see status UP

## Automated Tests

* setup application.properties for the unit and integration tests inside the src/test/resources folder
* add javafaker in the pom.xml
* start writing tests for services and for controllers

## Postgres SQL Database

* check if the docker client is running
* check if a postgres container is running
* create a new postgres database and name it springDevDb
* update the application.properties with the postgres database url etc.
* go to Run menu of Intellij IDE and select Edit Configuration
* click the plus button and select the Application
* name it SuperheroesApplication
* add the path of the main class which is SuperheroesApplication under com.example.superheroes
* add a new Environment variable and name it DATABASE_URL, DATABASE_USERNAME, and DATABASE_PASSWORD
* click apply and ok
* run the application
* send POST [http://localhost:8080/register](http://localhost:8080/register)
* send POST [http://localhost:8080/authenticate](http://localhost:8080/authenticate)
* send GET [http://localhost:8080/api/v1/userslogin, [] empty array should be the response
* send POST [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/users)
* send GET [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/users, [{...}] should be the response
* send PUT [http://localhost:8080/api/v1/users/{:id}](http://localhost:8080/api/v1/users/%7B:id%7D)
* full crud should be working
* [https://www.mockaroo.com](https://www.mockaroo.com/) to generate SQL queries

## Heroku

* create a GitHub account
* create a Heroku account
* create a new app on Heroku and choose your region without pipeline
* create a new postgres database on Heroku by using the add-on Postgres
* connect the database to the app
* create tables for the database through here [https://data.heroku.com/dataclips](https://data.heroku.com/dataclips)
* copy the sql scripts from the sql file in the root folder and paste it on the dataclips query dashboard
* you can also create your own sql scripts and paste them on the dataclips query dashboard
* add a system.properties file in the root directory
* add a GitHub secret, HEROKU_API_KEY, for the heroku api key which can be found on your account settings
* add a JWT_SECRET in the heroku's settings -> config vars

## GitHub Actions

* create a GitHub workflow yml file
* see [https://github.com/marketplace/actions/deploy-to-heroku](https://github.com/marketplace/actions/deploy-to-heroku)
* rename your master branch to main branch on the branches page of your GitHub Repo
* future push to your repo will deploy to Heroku


* create jwt package under the com.example.superheroes folder
* create models package under the jwt folder
* write UserPrincipal class, jwt/models/UserPrincipal.java
* write AuthenticationRequest class, jwt/models/AuthenticationRequest.java
* write AuthenticationResponse class, jwt/models/AuthenticationResponse.java
* create services package under the jwt folder
* add jjwt-api, jjwt-impl, and jjwt-jackson in the pom.xml
* write an application.yml that has jwt secret in the resources
* write AppProperties configuration in config package. For jwt only for now.
* write PasswordConfig configuration in config package.
* update the SecurityConfig with userDetailsService, jwtFilter, configure, and http.addFilterBefore
* create filters package under the jwt folder
* write JwtFilter class, jwt/filters/JwtRequestFilter.java
* create controllers package under the jwt folder
* write AuthenticateController class, jwt/controllers/AuthenticateController.java
* write an auth.http file inside the REST-Client folder
* write a users.http file inside the REST-Client folder
* run the application
* trigger the POST [http://localhost:8080/register](http://localhost:8080/register)
* check the response if an object is returned
* trigger the POST [http://localhost:8080/authenticate](http://localhost:8080/authenticate)
* check if a token is returned

## Protected endpoint

* add @PreAuthorize in the AntiHeroController
* send a get request to [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/anti-heroes)
* check if the response is 403 Forbidden
* login as a user by,
* triggering the POST [http://localhost:8080/register](http://localhost:8080/register)
* triggering the POST [http://localhost:8080/authenticate](http://localhost:8080/authenticate)
* use the token in the Authorization header of REST-Client/anti-heroes.http
* send another get request to [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/anti-heroes)
* check if the response is 200 OK []

## Redis Caching

* add spring-data-redis and jedis in the pom.xml
* write RedisConfig configuration in config package.
* update the SuperheroesApplication class with @EnableCaching
* run the application

## Health Checks

* add spring-boot-starter-actuator in the pom.xml
* write health.http file inside the REST-Client folder
* trigger GET [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) to see status UP

## Automated Tests

* setup application.properties for the unit and integration tests inside the src/test/resources folder
* add javafaker in the pom.xml
* start writing tests for services and for controllers

## Postgres SQL Database

* check if the docker client is running
* check if a postgres container is running
* create a new postgres database and name it springDevDb
* update the application.properties with the postgres database url etc.
* go to Run menu of Intellij IDE and select Edit Configuration
* click the plus button and select the Application
* name it SuperheroesApplication
* add the path of the main class which is SuperheroesApplication under com.example.superheroes
* add a new Environment variable and name it DATABASE_URL, DATABASE_USERNAME, and DATABASE_PASSWORD
* click apply and ok
* run the application
* send POST [http://localhost:8080/register](http://localhost:8080/register)
* send POST [http://localhost:8080/authenticate](http://localhost:8080/authenticate)
* send GET [http://localhost:8080/api/v1/login](http://localhost:8080/api/v1/userslogin, [] empty array should be the response
* send POST [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/anti-heroes)
* send GET [http://localhost:8080/api/v1/users](http://localhost:8080/api/v1/anti-heroes), [{...}] should be the response
* send PUT [http://localhost:8080/api/v1/users/{:id}](http://localhost:8080/api/v1/anti-heroes/%7B:id%7D)
* full crud should be working
* [https://www.mockaroo.com](https://www.mockaroo.com/) to generate SQL queries

## Heroku

* create a GitHub account
* create a Heroku account
* create a new app on Heroku and choose your region without pipeline
* create a new postgres database on Heroku by using the add-on Postgres
* connect the database to the app
* create tables for the database through here [https://data.heroku.com/dataclips](https://data.heroku.com/dataclips)
* copy the sql scripts from the sql file in the root folder and paste it on the dataclips query dashboard
* you can also create your own sql scripts and paste them on the dataclips query dashboard
* add a system.properties file in the root directory
* add a GitHub secret, HEROKU_API_KEY, for the heroku api key which can be found on your account settings
* add a JWT_SECRET in the heroku's settings -> config vars

## GitHub Actions

* create a GitHub workflow yml file
* see [https://github.com/marketplace/actions/deploy-to-heroku](https://github.com/marketplace/actions/deploy-to-heroku)
* rename your master branch to main branch on the branches page of your GitHub Repo
* future push to your repo will deploy to Heroku
