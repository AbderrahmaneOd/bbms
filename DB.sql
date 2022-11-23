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