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
