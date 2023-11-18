create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('mobile', 3000.0);
insert into devices(name, price) values ('TV', 10000.0);
insert into devices(name, price) values ('tablet', 4000.0);
insert into people(name) values ('Ivan');
insert into people(name) values ('Igor');
insert into people(name) values ('Sergey');
insert into devices_people(device_id, people_id) values (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1), (2, 3);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2);

select avg(price) from devices;

select p.name, avg(d.price)
from people p join devices_people dp join devices d
on dp.device_id = d.id on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from people p join devices_people dp join devices d
on dp.device_id = d.id on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;