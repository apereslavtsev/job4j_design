create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name)
values ('Ivan');
insert into people(name)
values ('Igor');
insert into people(name)
values ('Anton');
insert into people(name)
values ('Alex');
insert into people(name)
values ('Petr');

insert into devices(name, price)
values ('Iphone', 1000);
insert into devices(name, price)
values ('mackbook', 3000);
insert into devices(name, price)
values ('ipad', 2000);
insert into devices(name, price)
values ('ipod', 300);
insert into devices(name, price)
values ('nokia 3300', 8000);

insert into devices_people(people_id, device_id)
values (1, 1);
insert into devices_people(people_id, device_id)
values (1, 2);
insert into devices_people(people_id, device_id)
values (1, 3);
insert into devices_people(people_id, device_id)
values (1, 4);

insert into devices_people(people_id, device_id)
values (2, 5);

insert into devices_people(people_id, device_id)
values (3, 2);
insert into devices_people(people_id, device_id)
values (3, 5);
insert into devices_people(people_id, device_id)
values (4, 1);
insert into devices_people(people_id, device_id)
values (5, 4);

select avg(price) from devices;

select people.name, avg(devices.price) 
from devices_people as dp 
join people   
on dp.people_id = people.id
join devices
on dp.device_id = devices.id
group by people.name;

select people.name, avg(devices.price) 
from devices_people as dp 
join people   
on dp.people_id = people.id
join devices
on dp.device_id = devices.id
group by people.name
having avg(devices.price) > 5000;



