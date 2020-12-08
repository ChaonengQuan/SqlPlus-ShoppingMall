--Author: Chaoneng Quan
--Drop table if it exists
DROP TABLE chaonengquan.Employee;
DROP SEQUENCE chaonengquan.employee_id_sequence;
DROP TRIGGER chaonengquan.employee_on_insert;

--Create table
CREATE TABLE chaonengquan.Employee(
id		INTEGER,	
FirstName	VARCHAR2(50)	NOT NULL,
LastName	VARCHAR2(50)	NOT NULL,
Gender		VARCHAR2(10),
Address		VARCHAR2(100),
Phone		INTEGER		NOT NULL,
EmployeeGroup	VARCHAR2(50),
Salary		INTEGER,
PRIMARY KEY(id)
);

-- --Auto-Increment = sequence + trigger
-- CREATE SEQUENCE employee_id_sequence;
--
-- CREATE OR REPLACE TRIGGER employee_on_insert
--   BEFORE INSERT ON chaonengquan.Employee
--   FOR EACH ROW
-- BEGIN
--   SELECT employee_id_sequence.nextval
--   INTO :new.id
--   FROM dual;
-- END;
-- /

--Grant permission for teammates
GRANT SELECT ON chaonengquan.Employee TO PUBLIC;


--Example code to insert dummy data
/*

INSERT INTO chaonengquan.Employee
(FirstName, LastName, Gender, Address, Phone, EmployeeGroup, Salary)
VALUES
('John', 'Wick', 'Male', 'New York', 2131231234, 'Management Group', 100000);

INSERT INTO chaonengquan.Employee
(FirstName, LastName, Gender, Address, Phone, EmployeeGroup, Salary)
VALUES
('Gandalf', 'The Grey', 'Male', 'Middle Earth', 8008008080, 'Sales Group', 20000);

*/
