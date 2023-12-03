create table car_bodies(
	id serial primary key,
	name text
);

create table car_engines(
	id serial primary key,
	name text
);

create table car_transmissions(
	id serial primary key,
	name text
);

create table cars(
	id serial primary key,
	name text,
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) 
values ('Седан'), ('Универсал'), ('Хэтчбек'), ('Пикап');
insert into car_engines(name) 
values ('Карбюратор'), ('Атмосферный'), ('Турбированный');
insert into car_transmissions(name) 
values ('Механика'), ('Автомат'), ('Вариатор'), ('Робот');
insert into cars(name, body_id, engine_id, transmission_id)
values ('KIA', 1, 2, 2), ('BMW', 2, 3, 1), ('Skoda', 2, 3, 4), ('Toyota', 4, 3, 2),
('Volvo', 1, null, 1), ('Skania', null, 3, 2);

select c.id, c.name as car_name, b.name as body_name, 
e.name as engine_name, t.name as transmission_name 
from cars c left join car_bodies b on c.body_id = b.id 
left join car_engines e on c.engine_id = e.id 
left join car_transmissions t on c.transmission_id = t.id;

select b.name from car_bodies b left join cars c 
on c.body_id = b.id 
where c.id is null;

select e.name from car_engines e left join cars c
on c.engine_id = e.id 
where c.id is null;

select t.name from car_transmissions t left join cars c
on c.transmission_id = t.id
where c.id is null;


