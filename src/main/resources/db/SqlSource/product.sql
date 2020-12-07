--Author: Chaoneng Quan

--drop if it exists
DROP TABLE chaonengquan.Product;

--Create table
CREATE TABLE chaonengquan.Product(
id		INTEGER,
Name		VARCHAR2(50)	NOT NULL,
RetailPrice	FLOAT		NOT NULL,
Category	VARCHAR2(50),
MemberDiscount	INTEGER,
StockInfo	VARCHAR2(50),
SupplierID	INTEGER,
PRIMARY KEY(id)
);

-- --Create sequence
-- CREATE SEQUENCE product_id_sequence;
--
-- --Create trigger
-- CREATE OR REPLACE TRIGGER product_on_insert
--   BEFORE INSERT ON chaonengquan.Product
--   FOR EACH ROW
-- BEGIN
--   SELECT product_id_sequence.nextval
--   INTO :new.id
--   FROM dual;
-- END;
-- /

--Example code to insert dummy data
/*

INSERT INTO chaonengquan.Product
(id, Name, RetailPrice, Category, MemberDiscount, StockInfo, SupplierID)
VALUES
(44, 'iPhone6', 500, 'Electronic', 20 , 'In Stock', 1);

*/
