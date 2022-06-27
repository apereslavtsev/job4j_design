insert into fauna(name, avg_age, discovery_date)
values ('red frog', 250, date '2022-05-15');

insert into fauna(name, avg_age, discovery_date)
values ('black fish', 350, date '2000-01-01');

insert into fauna(name, avg_age, discovery_date)
values ('black long fish', 11000, date '1940-01-01');

insert into fauna(name, avg_age)
values ('Indian elephant', 20000);

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < date '1950-01-01';


