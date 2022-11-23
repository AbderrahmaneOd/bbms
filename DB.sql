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