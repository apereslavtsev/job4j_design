create table car_bodies(
    id serial primary key,
    name varchar(255)   
);

create table car_engines(
    id serial primary key,
    name varchar(255)	
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)	
);

create table cars(
    id serial primary key,
    name varchar(255), 
	body_id int references car_bodies(id), 
	engine_id int references car_engines(id), 
	transmission_id int references car_transmissions(id) 		
);

insert into car_bodies(name) values ('Универсал');
insert into car_bodies(name) values ('Седан');
insert into car_bodies(name) values ('Хэтчбэк');
insert into car_bodies(name) values ('Пикап');
insert into car_bodies(name) values ('Рамный кузов (внедорожник)');

insert into car_engines(name) values ('Бензин V 1,6 л.');
insert into car_engines(name) values ('Бензин V 2 л.');
insert into car_engines(name) values ('Дизель V 2,5 л.');
insert into car_engines(name) values ('Дизель V 5 л.');
insert into car_engines(name) values ('Гибрид V 1.0 л.');
insert into car_engines(name) values ('Ротор V 1,3 л.');


insert into car_transmissions(name) values ('АКПП');
insert into car_transmissions(name) values ('МКПП');
insert into car_transmissions(name) values ('Вариатор');
insert into car_transmissions(name) values ('Роботизированная АКПП');

insert into cars(name, body_id, engine_id, transmission_id) 
values ('ВАЗ 2107', 2, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('LADA VESTA', 1, 1, 3);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('Hyundai Creta', 3, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('VW GOLF', 3, 2, 4);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('MAZDA cx 7', 3, 6, 1);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('ГАЗ 66', 5, 4, 2);

-- Все машины
select 
cars.id as id, 
cars.name as car_name, 
car_bodies.name as body_name, 
car_engines.name as engine_name, 
car_transmissions.name as transmission_name
from cars 
left join car_bodies 
on cars.body_id = car_bodies.id
left join car_engines 
on cars.engine_id = car_engines.id
left join car_transmissions 
on cars.transmission_id = car_transmissions.id
ORDER BY cars.id;

-- Неиспользуемые кузова
select 
car_bodies.id as body_id, 
car_bodies.name as body_name 
from car_bodies 
left join cars 
on car_bodies.id = cars.body_id 
where cars.id is NULL;

-- Неиспользуемые двигатели
select 
car_engines.id as engine_id, 
car_engines.name as engine_name 
from car_engines 
left join cars 
on car_engines.id = cars.engine_id 
where cars.id is NULL;

-- Неиспользуемые трансмиссии
select 
car_transmissions.id as transmission_id, 
car_transmissions.name as transmission_name 
from car_transmissions 
left join cars 
on car_transmissions.id = cars.transmission_id 
where cars.id is NULL;