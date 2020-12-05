--Author: Chaoneng Quan

-- Drop if exists
DROP TABLE chaonengquan.Supplier

-- Create table
CREATE TABLE chaonengquan.Supplier(
id		INTEGER,
Name		VARCHAR2(50)	NOT NULL,
RestockDate	DATE,
SupplyPrice	INTEGER,
Amount		INTEGER,
PRIMARY KEY(id)
);

-- Create sequence
CREATE SEQUENCE supplier_id_sequence;

-- Create trigger
CREATE OR REPLACE TRIGGER supplier_on_insert
  BEFORE INSERT ON chaonengquan.Supplier
  FOR EACH ROW
BEGIN
  SELECT supplier_id_sequence.nextval
  INTO :new.id
  FROM dual;
END;
/

--example code to insert dummy data
/*

INSERT INTO chaonengquan.Supplier
(Name, RestockDate, SupplyPrice, Amount)
VALUES
('Supply Dealer', TO_DATE('12/25/2020', 'MM/DD/YYYY'), 299, 10);

*/
