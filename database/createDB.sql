/*
CREATE DB
*/

DROP SCHEMA IF EXISTS `tripletclothes`;
CREATE SCHEMA `tripletclothes` ;
use tripletclothes;
CREATE TABLE `Users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`fullname` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL UNIQUE,
	`email` VARCHAR(255) NOT NULL UNIQUE,
	`avatar` TEXT,
	`phone` VARCHAR(255),
	`address` TEXT,
	`password` VARCHAR(255),
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp on update current_timestamp,
	`delete_time` timestamp null,
	PRIMARY KEY (`id`)
);

create table `Roles` (
	`id` INT NOT NULL auto_increment,
    `code` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

create table `UserRoles`(
	`id` INT NOT NULL auto_increment,
	`user_id` INT NOT NULL,
	`role_id` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Categories` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`parent_id` INT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Rates` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`value` ENUM('ONE','TWO','THREE','FOUR','FIVE') NOT NULL,
	`product_id` INT NOT NULL,
	`content` TEXT,
	`user_id` INT NOT NULL,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp on update current_timestamp,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Products` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`description` TEXT,
	`image` TEXT NOT NULL,
	`quantity` INT NOT NULL,
	`price` decimal(15,0) NOT NULL,
	`category_id` INT NOT NULL,
	`rate_avg` double,
    `create_time` timestamp default current_timestamp,
    `update_time` timestamp default current_timestamp on update current_timestamp,
    `delete_time` timestamp null,
	PRIMARY KEY (`id`)
);

ALTER TABLE `UserRoles` ADD CONSTRAINT `UserRoles_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);

ALTER TABLE `UserRoles` ADD CONSTRAINT `UserRoles_fk1` FOREIGN KEY (`role_id`) REFERENCES `Roles`(`id`);

ALTER TABLE `Products` ADD CONSTRAINT `Products_fk0` FOREIGN KEY (`category_id`) REFERENCES `Categories`(`id`);

ALTER TABLE `Categories` ADD CONSTRAINT `Categories_fk0` FOREIGN KEY (`parent_id`) REFERENCES `Categories`(`id`);

ALTER TABLE `Rates` ADD CONSTRAINT `Rates_fk0` FOREIGN KEY (`product_id`) REFERENCES `Products`(`id`);

ALTER TABLE `Rates` ADD CONSTRAINT `Rates_fk1` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);
