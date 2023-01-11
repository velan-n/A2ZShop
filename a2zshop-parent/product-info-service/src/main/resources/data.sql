insert into category(id,category_name) values(1,'Mobiles & Electronics');
insert into category(id,category_name) values(2,'Home & Kitchen');

insert into sub_category(id,sub_category_name,category_id) values(1,'Mobile Phones',1);
insert into sub_category(id,sub_category_name,category_id) values(2,'Computers',1);

insert into product_details(id,product_name,description,price,stock,rating,sub_category_id) values(1001,'Samsung Galaxy-F41','This is samsung mobile',12000, 10,0,1);
insert into product_details(id,product_name,description,price,stock,rating,sub_category_id) values(1002,'Redmi note 10 pro','This is redmi mobile',15000, 30,0,1);
insert into product_details(id,product_name,description,price,stock,rating,sub_category_id) values(1003,'HP 14s','This is hp laptop',55000, 20,0,2);
insert into product_details(id,product_name,description,price,stock,rating,sub_category_id) values(1004,'Dell Latitude','This is dell laptop',100000, 15,0,2);