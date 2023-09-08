-- liquibase formatted sql

-- changeset Create database:1
-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Table `file_extensions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `file_extensions`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `files`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `files`
(
    `id`                 INT         NOT NULL AUTO_INCREMENT,
    `name`               VARCHAR(45) NOT NULL,
    `file_extensions_id` BIGINT      NOT NULL,
    `relative_path`      VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `relative_path_UNIQUE` (`relative_path` ASC) VISIBLE,
    INDEX `fk_files_file_extensions1_idx` (`file_extensions_id` ASC) VISIBLE,
    CONSTRAINT `fk_files_file_extensions1`
        FOREIGN KEY (`file_extensions_id`)
            REFERENCES `file_extensions` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `manufacturer_countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `manufacturer_countries`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `country_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `country_name_UNIQUE` (`country_name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `product_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `product_status`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `seasonalities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seasonalities`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sex`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sex`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_brand`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_category`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(45) NOT NULL,
    `parent_id` BIGINT      NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    INDEX `fk_shoe_sock_shape_copy1_shoe_sock_shape_copy11_idx` (`parent_id` ASC) VISIBLE,
    CONSTRAINT `fk_shoe_sock_shape_copy1_shoe_sock_shape_copy11`
        FOREIGN KEY (`parent_id`)
            REFERENCES `shoe_category` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_colors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_colors`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `rgb`  VARCHAR(7)   NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    UNIQUE INDEX `rgb_UNIQUE` (`rgb` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_fastener_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_fastener_type`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_heel_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS shoe_heel_height
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_insole_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_insole_material`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_product_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS shoe_product_material
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `shoe_sock_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_sock_type`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_sole_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_sole_material`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_sole_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_sole_type`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_top_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_top_material`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoes`
(
    `id`                        BIGINT         NOT NULL AUTO_INCREMENT,
    `name`                      VARCHAR(300)   NOT NULL,
    `model`                     VARCHAR(45)    NOT NULL,
    `shoe_brand_id`             BIGINT         NOT NULL,
    `manufacturer_countries_id` BIGINT         NOT NULL,
    `shoe_sole_material_id`     BIGINT         NOT NULL,
    `shoe_top_material_id`      BIGINT         NOT NULL,
    `shoe_insole_material_id`   BIGINT         NOT NULL,
    shoe_product_material_id    BIGINT         NOT NULL,
    shoe_heel_height_id         BIGINT         NOT NULL,
    `shoe_sole_type_id`         BIGINT         NOT NULL,
    `sex_id`                    INT            NOT NULL,
    `shoe_fastener_type_id`     BIGINT         NOT NULL,
    `shoe_sock_type_id`         BIGINT         NOT NULL,
    `shoe_category_id`          BIGINT         NOT NULL,
    `shoe_color_id`             BIGINT         NOT NULL,
    `price`                     DECIMAL(38, 8) NOT NULL,
    `article`                   VARCHAR(50)    NOT NULL,
    `description`               TEXT           NOT NULL,
    `product_status_id`         INT            NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idshoes_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_shoes_manufacturer_countries1_idx` (`manufacturer_countries_id` ASC) VISIBLE,
    INDEX `fk_shoes_product_status1_idx` (`product_status_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_sole_material1_idx` (`shoe_sole_material_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_top_material1_idx` (`shoe_top_material_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_insole_material1_idx` (`shoe_insole_material_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_product_material1_idx` (shoe_product_material_id ASC) VISIBLE,
    INDEX `fk_shoes_shoe_heel_type1_idx` (shoe_heel_height_id ASC) VISIBLE,
    INDEX `fk_shoes_shoe_sole_type1_idx` (`shoe_sole_type_id` ASC) VISIBLE,
    INDEX `fk_shoes_sex1_idx` (`sex_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_fastener_type1_idx` (`shoe_fastener_type_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_sock_type1_idx` (`shoe_sock_type_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_category1_idx` (`shoe_category_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_color1_idx` (`shoe_color_id` ASC) VISIBLE,
    INDEX `fk_shoes_shoe_brand1_idx` (`shoe_brand_id` ASC) VISIBLE,
    CONSTRAINT `fk_shoes_manufacturer_countries1`
        FOREIGN KEY (`manufacturer_countries_id`)
            REFERENCES `manufacturer_countries` (`id`),
    CONSTRAINT `fk_shoes_product_status1`
        FOREIGN KEY (`product_status_id`)
            REFERENCES `product_status` (`id`),
    CONSTRAINT `fk_shoes_sex1`
        FOREIGN KEY (`sex_id`)
            REFERENCES `sex` (`id`),
    CONSTRAINT `fk_shoes_shoe_brand1`
        FOREIGN KEY (`shoe_brand_id`)
            REFERENCES `shoe_brand` (`id`),
    CONSTRAINT `fk_shoes_shoe_category1`
        FOREIGN KEY (`shoe_category_id`)
            REFERENCES `shoe_category` (`id`),
    CONSTRAINT `fk_shoes_shoe_color1`
        FOREIGN KEY (`shoe_color_id`)
            REFERENCES `shoe_colors` (`id`),
    CONSTRAINT `fk_shoes_shoe_fastener_type1`
        FOREIGN KEY (`shoe_fastener_type_id`)
            REFERENCES `shoe_fastener_type` (`id`),
    CONSTRAINT `fk_shoes_shoe_heel_type1`
        FOREIGN KEY (shoe_heel_height_id)
            REFERENCES shoe_heel_height (`id`),
    CONSTRAINT `fk_shoes_shoe_insole_material1`
        FOREIGN KEY (`shoe_insole_material_id`)
            REFERENCES `shoe_insole_material` (`id`),
    CONSTRAINT `fk_shoes_shoe_product_material1`
        FOREIGN KEY (shoe_product_material_id)
            REFERENCES shoe_product_material (`id`),
    CONSTRAINT `fk_shoes_shoe_sock_type1`
        FOREIGN KEY (`shoe_sock_type_id`)
            REFERENCES `shoe_sock_type` (`id`),
    CONSTRAINT `fk_shoes_shoe_sole_material1`
        FOREIGN KEY (`shoe_sole_material_id`)
            REFERENCES `shoe_sole_material` (`id`),
    CONSTRAINT `fk_shoes_shoe_sole_type1`
        FOREIGN KEY (`shoe_sole_type_id`)
            REFERENCES `shoe_sole_type` (`id`),
    CONSTRAINT `fk_shoes_shoe_top_material1`
        FOREIGN KEY (`shoe_top_material_id`)
            REFERENCES `shoe_top_material` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `shoes_has_siblings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoes_has_siblings`
(
    `shoes_id`         BIGINT NOT NULL,
    `shoes_sibling_id` BIGINT NOT NULL,
    PRIMARY KEY (`shoes_id`, `shoes_sibling_id`),
    INDEX `fk_shoe_siblings_shoes_idx` (`shoes_id` ASC) VISIBLE,
    CONSTRAINT `fk_shoe_id`
        FOREIGN KEY (`shoes_id`)
            REFERENCES `shoes` (`id`),
    CONSTRAINT `fk_shoe_has_sibling`
        FOREIGN KEY (`shoes_sibling_id`)
            REFERENCES `shoes` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;
-- -----------------------------------------------------
-- Table `shoe_files`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_files`
(
    `shoes_id`        BIGINT   NOT NULL,
    `files_id`        INT      NOT NULL,
    `main_photo`      TINYINT  NOT NULL DEFAULT '0',
    `sequence_number` SMALLINT NOT NULL,
    PRIMARY KEY (`files_id`),
    INDEX `fk_shoe_files_shoes_idx` (`shoes_id` ASC) VISIBLE,
    CONSTRAINT `fk_shoe_files_files1`
        FOREIGN KEY (`files_id`)
            REFERENCES `files` (`id`),
    CONSTRAINT `fk_shoe_files_shoes`
        FOREIGN KEY (`shoes_id`)
            REFERENCES `shoes` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoe_sizes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoe_sizes`
(
    `shoes_id` BIGINT         NOT NULL,
    `size`     INT NOT NULL,
    `quantity` INT            NOT NULL DEFAULT '0',
    INDEX `fk_shoe_sizes_shoes1_idx` (`shoes_id` ASC) VISIBLE,
    CONSTRAINT `fk_shoe_sizes_shoes1`
        FOREIGN KEY (`shoes_id`)
            REFERENCES `shoes` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `shoes_has_seasonalities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shoes_has_seasonalities`
(
    `shoes_id`         BIGINT NOT NULL,
    `seasonalities_id` BIGINT NOT NULL,
    PRIMARY KEY (`shoes_id`, `seasonalities_id`),
    INDEX `fk_shoes_has_seasonalities_seasonalities1_idx` (`seasonalities_id` ASC) VISIBLE,
    INDEX `fk_shoes_has_seasonalities_shoes1_idx` (`shoes_id` ASC) VISIBLE,
    CONSTRAINT `fk_shoes_has_seasonalities_seasonalities1`
        FOREIGN KEY (`seasonalities_id`)
            REFERENCES `seasonalities` (`id`),
    CONSTRAINT `fk_shoes_has_seasonalities_shoes1`
        FOREIGN KEY (`shoes_id`)
            REFERENCES `shoes` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
-- changeset Add check_size trigger:2 splitStatements:false
-- delimiter $$
CREATE TRIGGER check_size_exists
    BEFORE INSERT
    ON shoe_sizes
    FOR EACH ROW
BEGIN
    DECLARE sizes INT;



    IF NEW.size IN (SELECT size
                    FROM shoe_sizes
                    WHERE shoes_id = NEW.shoes_id)
    THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT =
                'There is already size for this shoe. Change quantity';
    END IF;
END;
-- $$
-- delimiter ;
-- rollback DROP TRIGGER IF EXISTS check_size_exists;

-- changeset Add check_shoes_sibling_id trigger:3 splitStatements:false
-- delimiter $$
CREATE TRIGGER check_shoes_sibling_id
    BEFORE INSERT
    ON shoes_has_siblings
    FOR EACH ROW
BEGIN
    IF NEW.shoes_sibling_id = NEW.shoes_id THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT =
                'The sibling_id can\'t be the same as shoes_id.';
    END IF;
END;
-- $$
-- delimiter ;
-- rollback DROP TRIGGER IF EXISTS check_shoes_sibling_id;