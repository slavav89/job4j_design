create table people(
	id serial primary key,
	gender varchar(1)
);
create table shop(
	id serial primary key,
	name text
);
create table product(
	id serial primary key,
	name text,
	shop_id int references shop(id) 
);
create table pay(
	id serial primary key,
	product_id int references product(id),
	people_id int references people(id)
);

insert into people(gender) values ('М'), ('Ж');
insert into shop(name) values ('строительный'), ('продуктовый'), ('игрушки');
insert into product(name, shop_id) 
values ('саморезы', 1), ('бананы', 2), ('молоко', 2),
('куклы', 3), ('гайки', 1), ('клей', 1), 
('роботы', 3), ('шоколад', 2), ('хлеб', 2);
insert into pay(product_id, people_id) 
values (1, 1), (2, 1), (3, 2), (4, 2), (5, 1), (6, 2), (7, 1), (8, 2), (9, 2);

create view show_gender_with_3_or_more_product
as select p.gender as gender, count(s.name), s.name as shop from people as p
join pay on p.id = pay.people_id
join product as pr on pr.id = pay.product_id
join shop as s on s.id = pr.shop_id
group by (p.gender, s.name) having count(s.name) > 1;

select * from show_gender_with_3_or_more_product;

