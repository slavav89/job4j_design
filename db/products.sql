create table type(
	id serial primary key,
	name text
);
create table product(
	id serial primary key,
	name text,
	expired_date date,
	price int,
	type_id int references type (id)
);
insert into type(name) values ('сыр');
insert into type(name) values ('молоко');
insert into type(name) values ('хлеб');

insert into product(name, expired_date, price, type_id)
values ('сыр сулугуни', '2023-11-22', 100, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр моцарелла', '2023-11-25', 150, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр рикотта', '2023-11-27', 160, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр рокфор', '2023-11-26', 170, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр адыгейский', '2023-11-30', 120, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр брынза', '2023-11-29', 180, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр гауда', '2023-12-01', 200, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр пармезан', '2023-12-10', 250, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр российский', '2023-12-05', 90, 1);
insert into product(name, expired_date, price, type_id)
values ('сыр пекорино', '2023-11-20', 300, 1);
insert into product(name, expired_date, price, type_id)
values ('молоко топленое', '2023-12-02', 70, 2);
insert into product(name, expired_date, price, type_id)
values ('молоко коровье', '2023-11-29', 80, 2);
insert into product(name, expired_date, price, type_id)
values ('молоко мороженое', '2023-11-25', 85, 2);
insert into product(name, expired_date, price, type_id)
values ('хлеб бородинский', '2023-11-30', 50, 3);
insert into product(name, expired_date, price, type_id)
values ('хлеб белый', '2023-11-28', 50, 3);
insert into product(name, expired_date, price, type_id)
values ('хлеб особенный', '2023-11-29', 300, 3);

select p.name as product, p.expired_date, p.price
from product p join type t on p.type_id = t.id
where t.name = 'сыр';

select * from product where name like '%мороженое%';

select * from product where current_date > expired_date;

select name, price from product
group by name, price
having price = (select max(price) from product);

select t.name as type_name, count(p.name) as count
from type t join product p on p.type_id = t.id
group by t.name;

select t.name as type_name, p.name as product_name,
p.expired_date, p.price
from type t join product p on p.type_id = t.id
where t.name in ('сыр', 'молоко');

select t.name as type_name, count(p.name) as count
from type t join product p on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select * from product
join type on product.type_id = type.id;