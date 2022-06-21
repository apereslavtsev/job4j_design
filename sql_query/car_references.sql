create table colors
(
	id serial primary key,
    colorDescription text,
    rgbCode text,
    htmlCode text     
);

create table pts
(
	id serial primary key,
    seria text,
    number text,
    date date     
);

create table cars
(
    id serial primary key,
    carname text,
    wheelcount integer,
    weight double precision,
    color_id integer references colors(id),
    pts_id integer references pts(id) unique
);

create table drivers
(
	id serial primary key,
    fullname text,
    birthday date,
    category text     
);

create table car_drivers
(
	id serial primary key,
    car_id int references cars(id),
    driver_id int references drivers(id)    
);

