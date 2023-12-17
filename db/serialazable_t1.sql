begin transaction isolation level serializable;

select * from pipes;

select sum(count) from pipes;

update pipes set count = 300 where price = 13000;

commit;