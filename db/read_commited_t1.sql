begin transaction;

select * from pipes;

insert into pipes(diameter_mm, wall_mm, count, price)
values (530, 8, 300, 2000);

delete from pipes where count = 50;

update pipes set count = 100 where price = 10000;

select * from pipes;

commit;