DB
------------------------------
CREATE SCHEMA ims;//if u want change the schema name then url also need to change in application.properties

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `loginId` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `vendor` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`id`));
  
resource/application.properties
------------------------------------------
spring.security.user.name=spuser
spring.security.user.password=spuser@123

db.url=jdbc:mysql://localhost:3306/ims
db.username=root
db.password=root
db.driver=com.mysql.cj.jdbc.Driver

Run from mvn
----------------
mvn spring-boot:run

Run from jar
-----------------
mvn clean install
java -jar demo-0.0.1-SNAPSHOT.jar com.rewaa.ims.IMSApplication