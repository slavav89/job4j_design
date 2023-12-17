begin transaction isolation level repeatable read;

select * from pipes;

update pipes set price = 3000 where diameter_mm = 530;

rollback;

update pipes set price = 3000 where diameter_mm = 530;

commit;

select * from pipes;
