create database if not exists BankDataBase;
use BankDataBase;

create table CustomerLog(
firstName varchar(15),
middleName varchar(15),
lastName varchar(20),
dob date,
mobNo varchar(10),
aadharNo varchar(12),
custPswd varchar(20),
custBalance varchar(15)
);

select * from CustomerLog;



SET SQL_SAFE_UPDATES=0;
delete from CustomerLog;
