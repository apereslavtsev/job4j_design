
insert into role(id, name) values(1, 'admin');
insert into role(id, name) values(2, 'auditor');

insert into users(id, name, role_id) values(1, 'Ivanov Ivan', 1);
insert into users(id, name, role_id) values(2, 'Sokolova Svetlana', 2);

insert into rules(id, name) values(1, 'full rights');
insert into rules(id, name) values(2, 'read only');

insert into role_rules(id, role_id, rule_id) values(1, 1, 1);
insert into role_rules(id, role_id, rule_id) values(2, 2, 2);

insert into categorys(id, name) values(1, 'First category');
insert into categorys(id, name) values(2, 'Second category');

insert into states(id, name) values(1, 'Done');
insert into states(id, name) values(2, 'For approval');

insert into items(id, name, user_id, category_id, state_id) values(1, 'application for a chair', 1, 1, 2);

insert into comments(id, name, item_id) values(1, 'nice chair!', 1);

insert into attachs(id, name, filename, item_id) values(1, 'chair photo', 'D:\chairPhoto.png', 1);

