create table person (
    id serial primary key not null,
    login varchar(2000),
    password varchar(2000)
);

create table employee (
    id serial primary key not null,
    surname varchar(2000),
    INN int,
    hire_date timestamp without time zone not null default now()
);

create table employee_person (
    id serial primary key,
    employee_id INT REFERENCES employee(id),
    person_id INT REFERENCES person(id)
);

insert into person (login, password) values ('parsentev', '123');
insert into person (login, password) values ('ban', '123');
insert into person (login, password) values ('ivan', '123');
insert into person (login, password) values ('ururu@ya.ru.com', '123');
insert into person (login, password) values ('dedilov@ya.ru', '123');

insert into employee (surname, INN, hire_date) values ('Arsentev', 87383998, '2021-12-22 08:05:00');
insert into employee (surname, INN, hire_date) values ('Ivanov', 45654756, '2022-02-24 12:05:00');
insert into employee (surname, INN, hire_date) values ('Gluhov', 74651237, '2020-03-08 09:05:00');
insert into employee (surname, INN, hire_date) values ('Samostrelov', 46505405, '2012-05-30 13:05:00');

insert into employee_person(id, employee_id, person_id) values (1, 1, 8);
insert into employee_person(id, employee_id, person_id) values (2, 2, 9);
insert into employee_person(id, employee_id, person_id) values (3, 3, 10);
insert into employee_person(id, employee_id, person_id) values (4, 4, 11);
insert into employee_person(id, employee_id, person_id) values (5, 4, 12);