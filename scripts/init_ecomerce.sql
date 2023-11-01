-- Create the 'ecommerce' database if it doesn't exist
CREATE DATABASE IF NOT EXISTS ecommerce;

-- GRANT ALL PRIVILEGES ON 'ecommerce'.* TO 'iotsens'@localhost;
GRANT ALL PRIVILEGES ON ecommerce.* TO 'iotsens'@'%';
FLUSH PRIVILEGES;

-- Switch to the 'ecommerce' database
USE ecommerce;

-- Create the 'prices' table
CREATE TABLE IF NOT EXISTS prices (
    price_id INT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT,
    start_date DATETIME,
    end_date DATETIME,
    price_list INT,
    product_id INT,
    priority INT,
    price DECIMAL(10, 2),
    curr VARCHAR(3)
);

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr) VALUES
(1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

