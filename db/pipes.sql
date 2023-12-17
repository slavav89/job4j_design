create table pipes(
	id serial primary key,
	diameter_mm integer,
	wall_mm integer,
	count integer,
	price integer
);

insert into pipes(diameter_mm, wall_mm, count, price)
values (1420, 21, 100, 10000), (1220, 17, 50, 8000), (820, 14, 200, 5000);
