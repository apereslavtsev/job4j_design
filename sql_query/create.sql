create table role
(
	id serial primary key,
    name text    
);

create table users
(
	id serial primary key,
    name text,    
	role_id integer references role(id)
);

create table rules
(
	id serial primary key,
    name text    
);

create table role_rules
(
	id serial primary key,
    role_id int references role(id),
    rule_id int references rules(id)    
);

create table categorys
(
	id serial primary key,
    name text 	
);

create table states
(
	id serial primary key,
    name text 	
);

create table items
(
	id serial primary key,
    name text, 
    user_id integer references users(id),
    category_id integer references categorys(id),
    state_id integer references states(id)	
);

create table comments
(
	id serial primary key,
    name text, 
    item_id integer references items(id)
);

create table attachs
(
	id serial primary key,
    name text, 
	filename text,
    item_id integer references items(id)
);

