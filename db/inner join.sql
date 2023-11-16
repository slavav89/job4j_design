create table supermarket(
	id serial primary key,
	department text
);
create table product(
	id serial primary key,
	name text,
	supermarket_id int references supermarket(id)
);
insert into supermarket(department) values ('foodstuff');
insert into supermarket(department) values ('chemistry');
insert into supermarket(department) values ('chancellery');

insert into product(name, supermarket_id) values ('rice', 1);
insert into product(name, supermarket_id) values ('milk', 1);
insert into product(name, supermarket_id) values ('detergent', 2);
insert into product(name, supermarket_id) values ('pencil', 3);
insert into product(name) values ('car wheels');

select p.name, s.department
from product as p join supermarket as s on p.supermarket_id = s.id;

select p.name as Товар, s.department as Отдел
from product as p join supermarket as s on p.supermarket_id = s.id;

select p.name Товар, s.department as "Отдел супермаркета"
from product p join supermarket s on p.supermarket_id = s.id;