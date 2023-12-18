select * from pipes;

begin transaction;

insert into pipes(diameter_mm, wall_mm, count, price) 
VALUES (1020, 12, 100, 14000);

savepoint first;

select * from pipes;

delete from pipes where price = 10000;

savepoint second;

update pipes set count = 250 where diameter_mm = 1420;

select * from pipes;

rollback to second;

select * from pipes;

rollback to first;

select * from pipes;

rollback;