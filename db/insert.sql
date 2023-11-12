insert into roles(name) values('roles');
insert into rules(name) values('rules');
insert into categories(name) values('categories');
insert into states(name) values('states');
insert into users(name, role_id) values('Ivan', 1);
insert into roles_rules(role_id, rule_id) values(1, 1);
insert into items(name, user_id, category_id, state_id) values ('items', 1, 1, 1);
insert into comments(name, item_id) values('comments', 1);
insert into attachs(name, item_id) values('attachs', 1);
