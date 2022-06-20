create table cars (
    id serial primary key, 
    carName text, 
    wheelCount integer,
    weight double precision
);
insert into cars (carName, wheelCount) values ('GAZ 66', 6);
select * from cars