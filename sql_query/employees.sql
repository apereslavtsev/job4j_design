create table departments(
    id serial primary key,
    name varchar(255),
    price float
);

create table employees(
    id serial primary key,
    name varchar(255),
	department_id int references departments(id)
);


insert into departments(name) values ('Департамент фин. мониторинга');
insert into departments(name) values ('Департамент тех. поддержки');
insert into departments(name) values ('Департамент ОМТС');
insert into departments(name) values ('Департамент поиска новых проектов');

insert into employees(name, department_id) values ('Иван', 	1);
insert into employees(name, department_id) values ('Андрей', 	1);
insert into employees(name, department_id) values ('Михаил', 	1);
insert into employees(name, department_id) values ('Игорь', 	2);
insert into employees(name, department_id) values ('Дмитрий',	3);
insert into employees(name, department_id) values ('Антон', 	3);
insert into employees(name) values ('Олег');

-- left
select departments.name as department, employees.name as employe 

from departments 
left join employees 
on departments.id = employees.department_id; 

-- right
select departments.name as department, employees.name as employe 

from departments 
right join employees 
on departments.id = employees.department_id;


-- full
select departments.name as department, employees.name as employe 

from departments 
full join employees 
on departments.id = employees.department_id; 


-- cross
select departments.name as department, employees.name as employe 

from departments 
cross join employees ;

-- департаменты без работников
select departments.name as department

from departments 
left join employees 
on departments.id = employees.department_id 
where employees.department_id is NULL;

-- left-- right
select departments.name as department, employees.name as employe 

from departments 
left join employees 
on departments.id = employees.department_id; 

-- left-- right
select departments.name as department, employees.name as employe 

from employees  
right join departments 
on employees.department_id = departments.id;

--teens
create table teens(
    id serial primary key,
    name varchar(255),
	is_male BOOLEAN
);
 
insert into teens(name, is_male) values ('Иван', 		true);
insert into teens(name, is_male) values ('Ирина', 		false);
insert into teens(name, is_male) values ('Игорь', 		true);
insert into teens(name, is_male) values ('Александр', 	true);
insert into teens(name, is_male) values ('Ольга', 		false);
insert into teens(name, is_male) values ('Екатерина', 	false);


select t1.name as teen1, t1.is_male as is_maleale1, t2.name as teen2, t2.is_male as is_maleale2 

from teens as t1
cross join teens as t2
WHERE t1.is_male != t2.is_male;