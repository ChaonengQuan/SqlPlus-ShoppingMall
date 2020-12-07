--Author: Chaoneng Quan

--Drop it if already exist
DROP TABLE chaonengquan.SalesRecord;

--Create table
CREATE TABLE chaonengquan.SalesRecord(
id		INTEGER,
OrderDate	DATE		NOT NULL,
PaymentMethod	varchar(50)	NOT NULL,
TotalAmount	FLOAT		NOT NULL,		
MemberId	INTEGER		NOT NULL,
PRIMARY KEY(id)
);

-- --Create sequence
-- CREATE SEQUENCE salesRecord_id_sequence;
--
-- --create trigger
-- CREATE OR REPLACE TRIGGER salesRecord_on_insert
--   BEFORE INSERT ON chaonengquan.SalesRecord
--   FOR EACH ROW
-- BEGIN
--   SELECT salesRecord_id_sequence.nextval
--   INTO :new.id
--   FROM dual;
-- END;
-- /


--Example code to insert dummy data
/*

INSERT INTO chaonengquan.SalesRecord
(OrderDate, PaymentMethod, TotalAmount, MemberId)
VALUES
(TO_DATE('12/03/2020', 'MM/DD/YYYY'), 'VISA', 400, 1);

*/
