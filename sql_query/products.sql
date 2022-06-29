create table types
(
    id   serial primary key,
    name text
);

create table products
(
    id serial primary key,
    name text,
	type_id int references types (id), 
	expired_date timestamp,
    price float

);

insert into types(name) values ('СЫР');
insert into types(name) values ('МОЛОКО');
insert into types(name) values ('МАКАРОНЫ');

insert into products(name, type_id, expired_date, price) 
values ('Сыр плавленный', 1, date '2018-09-03', 55);
insert into products(name, type_id, expired_date, price) 
values ('Сыр творожный', 1, date '2022-08-01', 35);
insert into products(name, type_id, expired_date, price) 
values ('сулугуни', 1, date '2022-06-30', 150);
insert into products(name, type_id, expired_date, price) 
values ('моцарелла', 1, date '2022-09-01', 110);
insert into products(name, type_id, expired_date, price) 
values ('ряженка', 2, date '2022-10-01', 31);
insert into products(name, type_id, expired_date, price) 
values ('мороженое Вологодское', 2, date '2028-10-01', 69);
insert into products(name, type_id, expired_date, price) 
values ('кефир', 2, date '2022-11-01', 29);
insert into products(name, type_id, expired_date, price) 
values ('молоко Вологодское', 2, date '2022-06-30', 29);
insert into products(name, type_id, expired_date, price) 
values ('Макароны по флотски, консервированные. ДЯДЯ ВАНЯ', 3, date '2025-01-01', 220);
insert into products(name, type_id, expired_date, price) 
values ('Спагетти', 3, date '2030-01-01', 110);
insert into products(name, type_id, expired_date, price) 
values ('диетические макароны 0.1 ккал', 3, date '2050-01-01', 560);


-- СЫР
select pr.name, t.name as type

from products as pr
join types as t
on pr.type_id = t.id 
where t.name = 'СЫР'
ORDER BY t.name, pr.name;

-- мороженое
select pr.name, t.name as type

from products as pr
join types as t
on pr.type_id = t.id 
where pr.name like '%мороженое%'
ORDER BY t.name, pr.name;

-- срок годности истек
select pr.name, t.name as type, expired_date as СрокГодности

from products as pr
join types as t
on pr.type_id = t.id 
where pr.expired_date <  CURRENT_TIMESTAMP
ORDER BY t.name, pr.name;

-- Самый дорогой продукт
select pr.name, t.name as type, pr.price

from products as pr
join types as t
on pr.type_id = t.id 
where pr.price = (select max(products.price) from products)
ORDER BY t.name, pr.name;

-- количество продуктов 
select t.name as type, SUM(1) as prCount 

from products as pr
join types as t
on pr.type_id = t.id 
GROUP by t.name; 

-- СЫР МОЛОКО
select pr.name, t.name as type

from products as pr
join types as t
on pr.type_id = t.id 
where t.name in ('СЫР', 'МОЛОКО')
ORDER BY t.name, pr.name;

-- количество продуктов < 10 
select t.name as type, SUM(1) as prCount 

from products as pr
join types as t
on pr.type_id = t.id 
GROUP by t.name 
having SUM(1) < 10;

-- Продукты и типы
select pr.name as Продукт, t.name as Тип

from products as pr
join types as t
on pr.type_id = t.id 
ORDER BY t.name, pr.name;