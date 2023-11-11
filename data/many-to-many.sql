create table producers(
	id serial primary key,
	name varchar(255)
);
create table movies(
	id serial primary key,
	name varchar(255)
);
create table producers_movies(
	id serial primary key,
	producer_id int references producers(id),
	movie_id int references movies(id)
);