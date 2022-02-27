DROP TABLE If EXISTS Product;
CREATE TABLE Product (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(20),
  description varchar(50),
  price int(11),
  amount int(11)
);

DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    userid VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    province VARCHAR(50) NOT NULL
);

DROP TABLE If EXISTS basket_product;
CREATE TABLE basket_product (
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  product int(11) NOT NULL,
  customer int(11) NOT NULL
);

ALTER TABLE basket_product ADD FOREIGN KEY (product) REFERENCES Product(id);
ALTER TABLE basket_product ADD FOREIGN KEY (customer) REFERENCES Customer(id);

DROP TABLE IF EXISTS transaction_head;
CREATE TABLE transaction_head (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer int(11) NOT NULL,
  createat VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS transaction_detail;
CREATE TABLE transaction_detail (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  product int(11) NOT NULL,
  name VARCHAR(50) NOT NULL,
  price int(11),
  amount int(11),
  head int(11) NOT NULL
);

ALTER TABLE transaction_detail ADD FOREIGN KEY (head) REFERENCES transaction_head(id);
ALTER TABLE transaction_detail ADD FOREIGN KEY (product) REFERENCES Product(id);

ALTER TABLE transaction_head ADD FOREIGN KEY (customer) REFERENCES Customer(id);