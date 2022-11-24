CREATE DATABASE bbms;

CREATE TABLE `bbms`.`blood_bank` (
  `id_bk` INT AUTO_INCREMENT,
  `name_bk` VARCHAR(45) NOT NULL,
  `address_bk` VARCHAR(45) NOT NULL,
  `email_bk` VARCHAR(45) NOT NULL,
  `password_bk` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_bk`));
  
  CREATE TABLE `bbms`.`hospital` (
  `id_h` INT AUTO_INCREMENT,
  `name_h` VARCHAR(45) NOT NULL,
  `address_h` VARCHAR(45) NOT NULL,
  `email_h` VARCHAR(45) NOT NULL,
  `password_h` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_h`));
  
  CREATE TABLE `bbms`.`camp` (
  `id_camp` INT NOT NULL AUTO_INCREMENT,
  `name_camp` VARCHAR(45) NOT NULL,
  `address_camp` VARCHAR(45) NOT NULL,
  `phone_camp` VARCHAR(45) NOT NULL,
  `organized_by` VARCHAR(45) ,
  `start_date` VARCHAR(45) NOT NULL,
  `end_date` VARCHAR(45) ,
  PRIMARY KEY (`id_camp`));

CREATE TABLE `bbms`.`donor` (
  `id_donor` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `address_donor` VARCHAR(45) NOT NULL,
  `phone_donor` VARCHAR(45) NOT NULL,
  `email_donor` VARCHAR(45) NOT NULL,
  `blood_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_donor`));
  
  CREATE TABLE `bbms`.`blood_stock` (
  `id_stock` INT NOT NULL AUTO_INCREMENT,
  `blood_type_stock` VARCHAR(3) NOT NULL,
  `quantity_stock` INT NOT NULL,
  PRIMARY KEY (`id_stock`));
  
  CREATE TABLE `bbms`.`admin` (
  `id_admin` INT NOT NULL AUTO_INCREMENT,
  `name_admin` VARCHAR(30) NOT NULL,
  `address_admin` VARCHAR(30) NOT NULL,
  `phone_admin` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_admin`));

CREATE TABLE `bbms`.`blood_request` (
  `id_request` INT NOT NULL AUTO_INCREMENT,
  `date_request` VARCHAR(15) DEFAULT (CURRENT_DATE),
  `bloodtype_request` VARCHAR(5) NOT NULL,
  `quantity_request` INT NOT NULL,
  `status` VARCHAR(10) DEFAULT 'pending',
  `priority` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_request`));
  
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("A+",4);
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("B+",2);
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("AB+",5);
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("O+",7);
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("O-",3);
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("AB-",4);
INSERT INTO blood_stock(blood_type_stock,quantity_stock) VALUES ("B-",6);

SELECT * FROM blood_stock;
drop table blood_stock;
drop table blood_request;
SELECT * FROM blood_request;
truncate table blood_request;

INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status) VALUES ("AB+", 6, "high" , "delivered");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status) VALUES ("AB+", 1, "low", "delivered");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status) VALUES ("AB-", 4, "medium", "delivered");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status) VALUES ("A+", 9, "low", "delivered");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("A+", 12, "high");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("A+", 3, "medium");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("O+", 2, "high");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("O+", 8, "low");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("O-", 4, "medium");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("B+", 7, "high");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("B+", 4, "low");
INSERT INTO blood_request (bloodtype_request, quantity_request, priority) VALUES ("B-", 6, "medium");