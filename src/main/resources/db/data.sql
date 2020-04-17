--class created for tutorial reasons
DROP TABLE IF EXISTS accounts;

CREATE TABLE account (
id INT,
description VARCHAR(250) NOT NULL,
sumOfMoney INT
);

INSERT INTO account (id, description, sumOfMoney) VALUES
(1, 'PKO bank', 1000),
(2, 'Millenium bank', 800),
(3, 'Mbank', 1500),
(4, 'Santander bank', 2000);