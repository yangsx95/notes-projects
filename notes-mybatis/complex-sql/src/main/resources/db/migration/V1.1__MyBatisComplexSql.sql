-- 初始化用户
create user if not exists mybatis password 'mybatis';
alter user mybatis admin true;

-- 初始化SCHEMA
create schema MYBATIS;
grant alter any schema to MYBATIS;

-- 一对一
drop table if exists MYBATIS.wife;
drop table if exists MYBATIS.husband;
drop table if exists MYBATIS.student;

create table MYBATIS.husband
(
    hus_id   number(19) primary key,
    hus_name varchar2(20),
    age      number(3),
    birthday date
);

create table MYBATIS.wife
(
    wife_id   number(19) primary key,
    wife_name varchar2(255),
    age       number(3),
    birthday  date,
    hus_id    number(19),
    foreign key (hus_id) references MYBATIS.husband (hus_id)
);

insert into MYBATIS.husband(hus_id, hus_name, age, birthday)
values (1, '张山', 24, CURRENT_DATE);
insert into MYBATIS.husband(hus_id, hus_name, age, birthday)
values (2, '李斯', 25, CURRENT_DATE);
insert into MYBATIS.wife(wife_id, wife_name, age, birthday, hus_id)
values (1, '小红', 24, CURRENT_DATE, 2);
insert into MYBATIS.wife(wife_id, wife_name, age, birthday, hus_id)
values (2, '丽丽', 23, CURRENT_DATE, 1);

create table MYBATIS.student
(
    id integer primary key,
    name varchar2(32),
    stu_no varchar2(32)
);

insert into MYBATIS.student(id, name, stu_no) values ( 1, '赵丽颖' ,'20180101');
insert into MYBATIS.student(id, name, stu_no) values ( 2, '孙俪' ,'20180102');
insert into MYBATIS.student(id, name, stu_no) values ( 3, '迪丽热巴' ,'20180103');
insert into MYBATIS.student(id, name, stu_no) values ( 4, '杨幂' ,'20180104');
insert into MYBATIS.student(id, name, stu_no) values ( 5, '佟丽娅' ,'20180105');