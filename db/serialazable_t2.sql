begin transaction isolation level serializable;

select * from pipes;

select sum(count) from pipes;

update pipes set count = 200 where price = 15000;

commit;