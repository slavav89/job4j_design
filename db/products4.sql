create or replace procedure delete_data(d_count integer)
language 'plpgsql'
as $$
	BEGIN
	delete from products where count < d_count;
	END
$$;	

call delete_data(10);

create or replace function f_delete_data(d_price integer)
returns void
language 'plpgsql'
as $$
	begin
		delete from products where price >= d_price;
	end;
$$;

select f_delete_data(100);