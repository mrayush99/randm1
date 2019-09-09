create table login(
username varchar(50) not null,
password varchar(50));


insert into login values("admin", "admin@123"); 
insert into login values("naveen", "testing@123"); 

create table user_account(
first_name varchar(50) not null,
last_name varchar(50) not null,
email_id varchar(50) not null,
telephone_num varchar(10) not null,
fax_num varchar(10),
company varchar(50),
address_1 varchar(50) not null,
address_2 varchar(50),
city varchar(50),
post_code varchar(10) not null,
country varchar(50) not null,
region varchar(50) not null,
password varchar(50) not null,
confirm_password varchar(50) not null,
subscribe varchar(5) not null
);

insert into user_account values("Vinodh", "Francis", "email2001@gmail.com", "9790912000", "9790912000", "IBM", 
"DLF", "Ramapuram", "Chennai", "600015", "India", "Tamil Nadu", "Pass2000", "Pass2000", "No");

