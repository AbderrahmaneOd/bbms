CREATE DATABASE bbms;
USE bbms;

  CREATE TABLE `bbms`.`hospital` (
  `id_h` INT AUTO_INCREMENT,
  `name_h` VARCHAR(45) NOT NULL,
  `address_h` VARCHAR(60) NOT NULL,
  `email_h` VARCHAR(30) NOT NULL,
  `password_h` VARCHAR(10) NOT NULL,
  UNIQUE(id_h, password_h),
  PRIMARY KEY (`id_h`));
  
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Saada', 'bd de la Grande Ceinture Hay mohammadi- Casablanca', 'hospital-email@gmail.com', 'pass01');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Alhassani', 'rue Moulay Bouchaïb Radad bd. Oued Sebou - Casablanca', 'hospital-email@gmail.com', 'pass02');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Ben M\'sik', 'avenue Joulan - ex D Hay sidi othmane- Casablanca', 'hospital-email@gmail.com', 'pass03');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Cheikh Khalifa Ben Zayed Al Nahyan', 'bd Mohamed Taieb Naciri Oulfa - Casablanca', 'hospital-email@gmail.com', 'pass04');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Aïn-chock', 'route de Médiouna ang. bd. Berrechid Ain chok- Casablanca', 'hospital-email@gmail.com', 'pass05');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('20 Août 1953', '4 rue Lahcen El Arjoune Quartier des hopitaux- Casablanca', 'hospital-email@gmail.com', 'pass06');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Ibn Rochd', '1 rue des Hôpitaux Quartier des hopitaux- Casablanca', 'hospital-email@gmail.com', 'pass07');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Mohammed V', 'hay Mohammadi Aïn Sebaâ Hay mohammadi- Casablanca', 'hospital-email@gmail.com', 'pass08');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Moulay El Hassan', 'bd Moulay Youssef Quartier bourgogne- Casablanca', 'hospital-email@gmail.com', 'pass09');
INSERT INTO `hospital`(name_h, address_h, email_h, password_h) VALUES ('Sidi Bernoussi', 'avenue Souhaib Erroumi -ex H Sidi bernoussi- Casablanca', 'hospital-email@gmail.com', 'pass10');


CREATE TABLE `bbms`.`blood_bank` (
  `id_bk` INT AUTO_INCREMENT,
  `name_bk` VARCHAR(45) NOT NULL,
  `address_bk` VARCHAR(60) NOT NULL,
  `email_bk` VARCHAR(30) NOT NULL,
  `password_bk` VARCHAR(10) NOT NULL,
  UNIQUE(id_bk, password_bk),
  PRIMARY KEY (`id_bk`));
  
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre De Santé Cité Jemaa', 'bd de la Grande Ceinture Hay mohammadi- Casablanca', 'bloodbank-email@gmail.com', 'pass01');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre De Santé D\'elhank', 'rue Moulay Bouchaïb Radad bd. Oued Sebou - Casablanca', 'bloodbank-email@gmail.com', 'pass02');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre De Santé El Fida', 'avenue Joulan - ex D Hay sidi othmane- Casablanca', 'bloodbank-email@gmail.com', 'pass03');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Cheikh Khalifa Ben Zayed Al Nahyan', 'bd Mohamed Taieb Naciri Oulfa - Casablanca', 'bloodbank-email@gmail.com', 'pass04');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre De Santé Nouvelle Médina', 'route de Médiouna ang. bd. Berrechid Ain chok- Casablanca', 'bloodbank-email@gmail.com', 'pass05');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre Hospitalier Universitaire Ibn Rochd', '4 rue Lahcen El Arjoune Quartier des hopitaux- Casablanca', 'bloodbank-email@gmail.com', 'pass06');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre Santé Saâda Hay Mohammedi', '1 rue des Hôpitaux Quartier des hopitaux- Casablanca', 'bloodbank-email@gmail.com', 'pass07');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre régional de transfusion sanguine', 'hay Mohammadi Aïn Sebaâ Hay mohammadi- Casablanca', 'bloodbank-email@gmail.com', 'pass08');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre national de transfusion sanguinen', 'bd Moulay Youssef Quartier bourgogne- Casablanca', 'bloodbank-email@gmail.com', 'pass09');
INSERT INTO `blood_bank` (name_bk, address_bk, email_bk, password_bk) VALUES ('Centre de transfusion de Casa', 'avenue Souhaib Erroumi -ex H Sidi bernoussi- Casablanca', 'bloodbank-email@gmail.com', 'pass10');  

  
CREATE TABLE `bbms`.`camp` (
  `id_camp` INT AUTO_INCREMENT,
  `name_camp` VARCHAR(45) NOT NULL,
  `address_camp` VARCHAR(60) NOT NULL,
  `phone_camp` VARCHAR(15) NOT NULL,
  `organized_by` VARCHAR(45) NOT NULL,
  `start_date` VARCHAR(15) DEFAULT (CURRENT_DATE),
  `end_date` VARCHAR(15),
  PRIMARY KEY (`id_camp`));

INSERT INTO `camp`(name_camp, address_camp, phone_camp, organized_by, start_date, end_date) VALUES ('LifeBlood', 'bd Moulay Youssef Quartier bourgogne- Casablanca', '0576-2354', 'Centre de transfusion de Casa', DATE_FORMAT(current_date() - 200, '%Y-%m-%d'), DATE_FORMAT(current_date() - 100, '%Y-%m-%d') );
INSERT INTO `camp`(name_camp, address_camp, phone_camp, organized_by, start_date, end_date) VALUES ('Red Cross', 'rue des Hôpitaux Quartier des hopitaux- Tanger', '0680-0937', 'Centre Santé Saâda Hay Mohammedi', DATE_FORMAT(current_date() -300, '%Y-%m-%d'), DATE_FORMAT(current_date() - 200, '%Y-%m-%d') );
INSERT INTO `camp`(name_camp, address_camp, phone_camp, organized_by, start_date, end_date) VALUES ('Blood Services', 'rue Lahcen El Arjoune Quartier des hopitaux- Agadir', '0612-1278', 'Centre Hospitalier Universitaire Ibn Rochd', DATE_FORMAT(current_date() -95, '%Y-%m-%d'), DATE_FORMAT(current_date(), '%Y-%m-%d') );
INSERT INTO `camp`(name_camp, address_camp, phone_camp, organized_by, start_date, end_date) VALUES ('Red Cross Blood Transfusion Service', 'rue Lahcen El Arjoune Quartier des hopitaux- Agadir', '0670-9075', 'Centre De Santé El Fida', DATE_FORMAT(current_date() - 1000, '%Y-%m-%d'), DATE_FORMAT(current_date() - 900, '%Y-%m-%d') );
INSERT INTO `camp`(name_camp, address_camp, phone_camp, organized_by, start_date, end_date) VALUES ('Friends2support', 'bd Mohamed Taieb Naciri Oulfa - Casablanca', '0670-9070', 'Cheikh Khalifa Ben Zayed Al Nahyan', DATE_FORMAT(current_date() - 700, '%Y-%m-%d'), DATE_FORMAT(current_date() - 600, '%Y-%m-%d') );

CREATE TABLE `bbms`.`donor` (
  `id_donor` INT AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `address_donor` VARCHAR(60) NOT NULL,
  `phone_donor` VARCHAR(15) NOT NULL,
  `email_donor` VARCHAR(30) NOT NULL,
  `bloodtype_donor` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id_donor`),
  CHECK (`bloodtype_donor` IN ('A+', 'B+', 'AB+', 'O+','A-', 'B-', 'AB-', 'O-')) );
  
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Ahmad', 'Sidi bernoussi- Casablanca', 'donor-email@gmail.com', '0576-2354', 'A+');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Kamal', 'bd Moulay Youssef Quartier bourgogne- Casablanca', 'donor-email@gmail.com', '0680-0937', 'B+');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Amine', 'hay Mohammadi Aïn Sebaâ Hay mohammadi- Casablanca', 'donor-email@gmail.com', '0612-1278', 'AB-');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Walid', 'rue des Hôpitaux Quartier des hopitaux- Tanger', 'donor-email@gmail.com', '0670-5600', 'B+');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Hamza', '4 rue Lahcen El Arjoune Quartier des hopitaux- Agadir', 'donor-email@gmail.com', '0670-2390', 'O+');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Alaa', 'route de Médiouna ang. bd. Berrechid Ain chok- Kenitra', 'donor-email@gmail.com', '0576-2354', 'A+');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Soufiane', 'rue Lahcen El Arjoune Quartier des hopitaux- Agadir', 'donor-email@gmail.com', '0680-0937', 'O-');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Mouad', 'avenue Joulan - ex D Hay sidi othmane- Taroudant', 'donor-email@gmail.com', '0612-1278', 'AB-');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Marouane', 'bd Mohamed Taieb Naciri Oulfa - Casablanca', 'donor-email@gmail.com', '0670-8769', 'A-');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Zineb', 'Aïn Sebaâ Hay mohammadi - Tanger', 'donor-email@gmail.com', '0670-9054', 'B-');
INSERT INTO `donor`(full_name, address_donor, email_donor, phone_donor, bloodtype_donor) VALUES ('Khadija', 'avenue Joulan bd Moulay Youssef- Taroudant', 'donor-email@gmail.com', '0600-7756', 'AB+');


CREATE TABLE `bbms`.`blood_stock_hospital` (
  `id_stock_h` INT AUTO_INCREMENT,
  `bloodtype_stock_h` VARCHAR(3) NOT NULL,
  `quantity_stock_h` INT NOT NULL,
  `id_hospital` INT NOT NULL,
  PRIMARY KEY (`id_stock_h`),
  FOREIGN KEY (`id_hospital`) REFERENCES `hospital` (`id_h`),
  CHECK (`bloodtype_stock_h` IN ('A+', 'B+', 'AB+', 'O+','A-', 'B-', 'AB-', 'O-')) );

INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("A+",4, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("A-", 2, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("AB+", 5, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("AB-", 4, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("O+", 7, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("O-", 3, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("B-", 6, 1);
INSERT INTO blood_stock_hospital(bloodtype_stock_h,quantity_stock_h, id_hospital) VALUES ("B+", 9, 1);


CREATE TABLE `bbms`.`blood_stock_bloodbank` (
  `id_stock_bk` INT AUTO_INCREMENT,
  `bloodtype_stock_bk` VARCHAR(3) NOT NULL,
  `quantity_stock_bk` INT NOT NULL,
  `id_bk` INT NOT NULL,
  PRIMARY KEY (`id_stock_bk`),
  FOREIGN KEY (`id_bk`) REFERENCES `blood_bank` (`id_bk`),
  CHECK (`bloodtype_stock_bk` IN ('A+', 'B+', 'AB+', 'O+','A-', 'B-', 'AB-', 'O-')) );

INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("A+",4, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("A-",2, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("B+", 2, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("B-", 6, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("O+", 7, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("O-", 9, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("AB-", 4, 1);
INSERT INTO blood_stock_bloodbank(bloodtype_stock_bk,quantity_stock_bk, id_bk) VALUES ("AB+", 5, 1);



CREATE TABLE `bbms`.`blood_request` (
  `id_request` INT NOT NULL AUTO_INCREMENT,
  `date_request` VARCHAR(15) DEFAULT (CURRENT_DATE),
  `bloodtype_request` VARCHAR(3) NOT NULL,
  `quantity_request` INT NOT NULL,
  `status` VARCHAR(10) DEFAULT 'pending',
  `priority` VARCHAR(10) NOT NULL,
  `id_hospital` INT NULL,
  PRIMARY KEY (`id_request`),
  FOREIGN KEY (`id_hospital`) REFERENCES `hospital` (`id_h`),
  CHECK (`bloodtype_request` IN ('A+', 'B+', 'AB+', 'O+','A-', 'B-', 'AB-', 'O-')),
  CHECK (`status` IN ('pending', 'delivered', 'cancelled')),
  CHECK (`priority` IN ('low', 'medium', 'high'))		);


INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("AB+", 6, "high" , "pending", 1, DATE_FORMAT(current_date() - 1000, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("AB+", 1, "low", "delivered", 9, DATE_FORMAT(current_date() - 500, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("AB-", 3, "medium", "delivered", 5, DATE_FORMAT(current_date() - 300, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("AB-", 4, "low", "cancelled", 1, DATE_FORMAT(current_date(), '%Y-%m-%d') );

INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("A+", 9, "low", "pending", 1, DATE_FORMAT(current_date() - 700, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("A+", 2, "high", "delivered", 2, DATE_FORMAT(current_date() - 1098, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("A+", 3, "medium", "cancelled", 8, DATE_FORMAT(current_date() - 700, '%Y-%m-%d') );

INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("O+", 2, "high", "delivered", 2, DATE_FORMAT(current_date() - 110, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("O+", 8, "low", "pending", 4, DATE_FORMAT(current_date() - 600, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("O-", 4, "medium", "cancelled", 3, DATE_FORMAT(current_date() , '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("O-", 4, "medium", "delivered", 1, DATE_FORMAT(current_date() - 1000, '%Y-%m-%d') );

INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("B+", 7, "high", "delivered", 7, DATE_FORMAT(current_date() - 309, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("B+", 6, "low", "cancelled", 8, DATE_FORMAT(current_date() - 50000, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("B-", 3, "medium", "pending", 10, DATE_FORMAT(current_date() - 500, '%Y-%m-%d') );
INSERT INTO blood_request (bloodtype_request, quantity_request, priority, status, id_hospital, date_request) VALUES ("B+", 4, "high", "delivered", 7, DATE_FORMAT(current_date() - 400, '%Y-%m-%d') );