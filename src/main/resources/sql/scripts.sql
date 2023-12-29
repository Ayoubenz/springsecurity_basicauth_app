Create database springsecurity_db

use springsecurity_db

create table users(
                      id int not null primary key auto_increment,
                      username VARCHAR(50) not null,
                      password VARCHAR(500) not null,
                      enabled boolean not null
);

create table authorities (
                             id int not null primary key auto_increment,
                             username VARCHAR(50) not null,
                             authority VARCHAR(50) not null
);

insert ignore into users values(NULL, 'happy','12345','1');
insert ignore into authorities values(NULL, 'happy','write');

create table customers(
                          id int NOT NULL auto_increment,
                          email varchar(50) NOT NULL,
                          pwd varchar(50) NOT NULL,
                          role varchar(50) NOT NULL,
                          primary key(id));

insert into customers(email,pwd,role)
values("benzo@gmail.com","54321","ADMIN");

Select * from customers;
Select * from users;
Select * from authorities;