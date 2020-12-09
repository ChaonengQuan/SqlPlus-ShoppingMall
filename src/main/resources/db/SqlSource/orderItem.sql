--Author Chaoneng Quan

--drop if it exist
DROP TABLE chaonengquan.OrderItem;

--Create table
CREATE TABLE chaonengquan.OrderItem(
id          INTEGER,
SalesRecordId		INTEGER		NOT NULL,
ProductId		INTEGER		NOT NULL,
PaidPrice		FLOAT,
Quantity			INTEGER,
PRIMARY KEY (id)
);




--Example code to insert dummy data
/*

INSERT INTO chaonengquan.OrderItem
(SalesRecordID, ProductID, PaidPrice, Quantity)
VALUES
(1, 1, 500, 1);
*/
