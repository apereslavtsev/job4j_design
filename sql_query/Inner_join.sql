-- speciality list
select DISTINCT u.name as university,  s.speciality as university_speciality 

from students as s
join universities as u
on s.university_id = u.id 
ORDER BY u.name, s.speciality;


-- budget students
select u.name as university, s.name as budget_student_name, s.course

from students as s
join universities as u
on s.university_id = u.id 
where s.budget
ORDER BY u.name, s.name;


-- студенты, поступившие раньше 2018 г.
select u.name as university, s.name as "Студент", s.course as "Курс", s.enroll_date as "Дата поступления"

from students as s
join universities as u
on s.university_id = u.id 
where s.enroll_date < date '2018-01-01' 
ORDER BY u.name, s.course, s.name;