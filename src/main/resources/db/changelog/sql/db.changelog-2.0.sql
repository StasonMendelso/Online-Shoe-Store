-- liquibase formatted sql

-- changeset Shift product_status to shoes table:1
ALTER TABLE shoes
    ADD product_status ENUM ('В наявності','Немає в наявності') NOT NULL DEFAULT 'Немає в наявності';

UPDATE shoes
    JOIN product_status on product_status.id = product_status_id
SET product_status = (SELECT IF(product_status.name = 'В наявності',
                                'В наявності', 'Немає в наявності'));

ALTER TABLE shoes
    DROP FOREIGN KEY fk_shoes_product_status1;

ALTER TABLE shoes
    DROP COLUMN product_status_id;

DROP TABLE product_status;
-- changeset Modify column type in files table table:2
ALTER TABLE shoe_files
    DROP FOREIGN KEY fk_shoe_files_files1;

ALTER TABLE files
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE shoe_files
    MODIFY COLUMN files_id BIGINT NOT NULL;

ALTER TABLE shoe_files
    ADD CONSTRAINT `fk_shoe_files_files` FOREIGN KEY (files_id) REFERENCES files (id);
-- changeset Modify column type in sex table table:3
ALTER TABLE shoes
    DROP FOREIGN KEY fk_shoes_sex1;

ALTER TABLE sex
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE shoes
    MODIFY COLUMN sex_id BIGINT NOT NULL;

ALTER TABLE shoes
    ADD CONSTRAINT `fk_shoes_sex` FOREIGN KEY (sex_id) REFERENCES sex (id);
