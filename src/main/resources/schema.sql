CREATE TABLE `Product` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `uid` VARCHAR(255) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `productType` VARCHAR(30) NOT NULL,
    UNIQUE KEY(`uid`)
);

CREATE TABLE `CatalogProduct` (
    `id` INT NOT NULL PRIMARY KEY,
    `money` INT NOT NULL,
    `discount` INT DEFAULT NULL,
    CONSTRAINT `FK_CP_P` FOREIGN KEY (`id`) REFERENCES `Product` (`id`)
);