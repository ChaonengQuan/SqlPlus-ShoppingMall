--Author Chaoneng Quan

--drop if it exist
DROP TABLE chaonengquan.OrderItem;

--Create table
CREATE TABLE chaonengquan.OrderItem(
SalesRecordId		INTEGER		NOT NULL,
ProductId		INTEGER		NOT NULL,
PaidPrice		FLOAT,
Quntity			FLOAT
);




--Example code to insert dummy data
/*

INSERT INTO chaonengquan.OrderItem
(SalesRecordID, ProductID, PaidPrice, Quntity)
VALUES
(1, 1, 500, 1);
*/
