create table zoo(
	id serial primary key,
	name varchar(255)
);
create table animals(
	id serial primary key,
	name varchar(255),
	zoo_id int references zoo(id)
);