create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function taxation()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger taxation_trigger
	after insert on products
	referencing new table as inserted
	execute procedure taxation();
	
create or replace function taxation2()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.5;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger taxation2_trigger
    before insert on products
    for each row
    execute procedure taxation2();
	
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
		values (new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_trigger
    after insert on products
    for each row
    execute procedure history();