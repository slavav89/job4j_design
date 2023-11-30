insert into departments(name) values ('Legal');
insert into departments(name) values ('Transport');
insert into departments(name) values ('Finance');
insert into departments(name) values ('IT');

insert into employees(name, departments_id) values ('Ivan', 1);
insert into employees(name, departments_id) values ('Igor', 2);
insert into employees(name, departments_id) values ('Sergey', 3);
insert into employees(name, departments_id) values ('Roman', 1);
insert into employees(name, departments_id) values ('Elena', 2);

select e.name, d.name from employees e left join departments d on e.departments_id = d.id;
select e.name, d.name from employees e right join departments d on e.departments_id = d.id;
select e.name, d.name from employees e full join departments d on e.departments_id = d.id;
select e.name, d.name from employees e cross join departments d;

select d.name from departments d left join employees e on e.departments_id = d.id where e.id is null;

select e.name, d.name from employees e left join departments d on e.departments_id = d.id;
select e.name, d.name from departments d right join employees e on e.departments_id = d.id;

create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into teens(name, gender) values ('Irina', 'female');
insert into teens(name, gender) values ('Vasiliy', 'male');
insert into teens(name, gender) values ('Sergey', 'male');
insert into teens(name, gender) values ('Olga', 'female');
insert into teens(name, gender) values ('Maria', 'female');
insert into teens(name, gender) values ('Ivan', 'male');

select m.name as man, w.name as woman from teens m cross join teens w 
where m.gender > w.gender;