DROP TABLE If EXISTS Product;
CREATE TABLE Product (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(20),
  description varchar(50),
  price int(11),
  amount int(11)
);