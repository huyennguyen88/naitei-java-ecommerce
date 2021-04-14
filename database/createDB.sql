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

ALTER TABLE `UserRoles` ADD CONSTRAINT `UserRoles_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);

ALTER TABLE `UserRoles` ADD CONSTRAINT `UserRoles_fk1` FOREIGN KEY (`role_id`) REFERENCES `Roles`(`id`);