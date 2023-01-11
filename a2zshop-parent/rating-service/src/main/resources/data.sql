insert into user_ratings(id) values(1);
insert into user_ratings(id) values(2);

insert into product_ratings(id) values(1001);
insert into product_ratings(id) values(1002);

insert into rating(user_id, product_id, rating_value) values(1, 1001, 5);
insert into rating(user_id, product_id, rating_value) values(1, 1002, 4);
insert into rating(user_id, product_id, rating_value) values(2, 1001, 3);
insert into rating(user_id, product_id, rating_value) values(2, 1002, 2);
