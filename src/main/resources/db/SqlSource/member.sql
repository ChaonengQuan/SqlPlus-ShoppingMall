--Author: Chaoneng Quan

--Drop it if already exists
DROP TABLE chaonengquan.Member;

--Create table
CREATE TABLE chaonengquan.Member(
id              INTEGER,
FirstName       VARCHAR2(50)    NOT NULL,
LastName        VARCHAR2(50)    NOT NULL,
DateOfBirth	DATE,
Address		VARCHAR2(100),
Phone		INTEGER		NOT NULL,
RewardPoint	INTEGER,
MembershipPaid  VARCHAR2(10),
PRIMARY KEY(id)
);

-- --Create sequence
-- CREATE SEQUENCE member_id_sequence;
--
--
-- --Create Trigger
-- CREATE OR REPLACE TRIGGER member_on_insert
--   BEFORE INSERT ON chaonengquan.Member
--   FOR EACH ROW
-- BEGIN
--   SELECT member_id_sequence.nextval
--   INTO :new.id
--   FROM dual;
-- END;
-- /


--Grant permission for teammates
--GRANT SELECT ON chaonengquan.Member TO PUBLIC;


--Example code to insert dummy data
/*

INSERT INTO chaonengquan.Member
(FirstName, LastName, DateOfBirth, Address, Phone, RewardPoint, MembershipPaid)
VALUES
('Ice', 'Cube', TO_DATE('12/01/1996', 'MM/DD/YYYY'), 'Compton, California', 2132132133, 100, 'Yes');

*/
