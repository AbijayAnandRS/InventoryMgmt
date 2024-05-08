# Getting Started

### Setting up database

* Download and Install [Sql](https://dev.mysql.com/) suitable for your machine or use a cloud db
* Setup your username(root) and password(password) of your choice
* Run your local server
* Create database inventory by executing
```sql
CREATE DATABASE inventory;
```


* Create required tables
```sql
 CREATE TABLE `product_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `supplier_id` int NOT NULL,
  `price` double NOT NULL,
  `stock_qty` int NOT NULL,
  PRIMARY KEY (`id`,`supplier_id`),
  KEY `Fk_suppllier_idx` (`supplier_id`),
  CONSTRAINT `Fk_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
  ```

```sql
CREATE TABLE `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `contact` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
```sql
CREATE TABLE `product_images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `url` varchar(500) NOT NULL,
  `size` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `Fk_product_idx` (`product_id`),
  CONSTRAINT `Fk_product` FOREIGN KEY (`product_id`) REFERENCES `product_items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

* Add items in suppliers so that we don't need seperate service to manage it and we can use these suppliers in product service

### Running the project
* Pull the project and import it by opening build.gradle file and wait for project opens
* make sure project java version 21 available for you
* update the application.properties with your local mysql server username and password
* run below command to run the project
```gradle
./gradlew bootrun
```
### Testing the Apies
* [Information and testing steps are given here](https://documenter.getpostman.com/view/6498328/2sA3JKcMhX)




### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

