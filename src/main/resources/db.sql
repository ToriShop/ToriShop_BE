CREATE DATABASE tori;
USE tori;

DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
	id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS tier;
CREATE TABLE tier(
	id INT PRIMARY KEY AUTO_INCREMENT,
    tier VARCHAR(10) NOT NULL,
    standard_amount INT NOT NULL
);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer(
	id INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(15),
    email VARCHAR(20),
    address VARCHAR(50),
    total_order_amount INT NOT NULL DEFAULT 0,
    tier_id INT NOT NULL DEFAULT 1,
    join_date DATE NOT NULL DEFAULT (current_date),
    FOREIGN KEY (tier_id) REFERENCES tier(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS user;
CREATE TABLE user(
	id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    role VARCHAR(10) NOT NULL,
    admin_id INT,
    customer_id INT,
    FOREIGN KEY (admin_id) REFERENCES admin(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS product;
CREATE TABLE product(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    category VARCHAR(20) NOT NULL,
    description VARCHAR(500),
    image VARCHAR(100),
    create_date DATE NOT NULL,
    update_date DATE NOT NULL
);

DROP TABLE IF EXISTS cart;
CREATE TABLE cart(
	customer_id INT,
    product_id INT,
    is_in_order BOOLEAN NOT NULL DEFAULT TRUE,
    quantity INT NOT NULL DEFAULT 1,
    price INT NOT NULL,
    PRIMARY KEY (customer_id, product_id),
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS _order;
CREATE TABLE _order(
	id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_number VARCHAR(10) NOT NULL,
    total_price INT NOT NULL,
    recipient_name VARCHAR(10) NOT NULL,
    recipient_phone VARCHAR(15) NOT NULL,
    recipient_address VARCHAR(50) NOT NULL,
    order_date DATE NOT NULL DEFAULT (current_date),
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS order_item;
CREATE TABLE order_item(
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    delivery_status VARCHAR(10) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price INT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES _order(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE ON UPDATE CASCADE
);