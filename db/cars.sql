create table cars(
	id serial primary key,
	brand varchar(255),
	model text,
	release date
);
insert into cars(brand, model, release) values('Kia', 'Rio', '2016.11.25');
select * from cars;
update cars set brand = 'KIA';
select * from cars;
delete from cars;
select * from cars;