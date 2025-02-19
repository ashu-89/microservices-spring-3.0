CREATE TABLE t_inventory (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY,
    sku_code VARCHAR(255) NOT NULL,
    quantity int(11) DEFAULT NULL
);