--Autho: Chaoneng Quan


--SalesRecord FK
ALTER TABLE chaonengquan.SalesRecord
ADD CONSTRAINT fk_member
  FOREIGN KEY (MemberId)
  REFERENCES chaonengquan.Member(id);

--Product FK
ALTER TABLE chaonengquan.Product
ADD CONSTRAINT fk_supplier
  FOREIGN KEY (SupplierId)
  REFERENCES chaonengquan.Supplier(id);


--OrderItem PK
ALTER TABLE chaonengquan.OrderItem
ADD CONSTRAINT pk_OrderItem
  PRIMARY KEY (SalesRecordId, ProductId);

--OrderItem FKs
ALTER TABLE chaonengquan.OrderItem
ADD CONSTRAINT fk_SalesRecordId
  FOREIGN KEY (SalesRecordId)
  REFERENCES chaonengquan.SalesRecord(id);

ALTER TABLE chaonengquan.OrderItem
ADD CONSTRAINT fk_ProductId
  FOREIGN KEY (ProductId)
  REFERENCES chaonengquan.Product(id);
