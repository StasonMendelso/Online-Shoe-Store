-- liquibase formatted sql

-- changeset Shift product_status to shoes table:1
ALTER TABLE online_shoe_store_dev.shoes
    ADD product_status ENUM ('В наявності','Немає в наявності') NOT NULL DEFAULT 'Немає в наявності';

UPDATE online_shoe_store_dev.shoes
    JOIN product_status on product_status.id = product_status_id
SET product_status = (SELECT IF(product_status.name = 'В наявності',
                                'В наявності', 'Немає в наявності'));

ALTER TABLE online_shoe_store_dev.shoes
    DROP FOREIGN KEY fk_shoes_product_status1;

ALTER TABLE online_shoe_store_dev.shoes
    DROP COLUMN product_status_id;

DROP TABLE online_shoe_store_dev.product_status;

