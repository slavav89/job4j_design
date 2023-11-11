create table engine(
	id serial primary key,
	power int
);
create table car(
	id serial primary key,
	name varchar(255),
	engine_id int references engine(id) unique
);
